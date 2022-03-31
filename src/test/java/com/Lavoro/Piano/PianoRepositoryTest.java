package com.Lavoro.Piano;

import com.Carta.Carta;
import com.Carta.CartaRepository;
import com.Cliente.Cliente;
import com.Cliente.ClienteRepository;
import com.Commerciale.CommercialeRepository;
import com.Dipendente.Dipendente;
import com.Dipendente.DipendenteRepository;
import com.Lavoro.Fasi;
import com.Lavoro.Piano.Fasi.Concluso;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PianoRepositoryTest {

    @Autowired private PianoRepository underTest;
    @Autowired private ClienteRepository clienteRepository;
    @Autowired private DipendenteRepository dipendenteRepository;
    @Autowired private CartaRepository cartaRepository;
    @Autowired private CommercialeRepository commercialeRepository;
    private PianoUtility pianoUtility;

    @BeforeEach
    void setUp() {
        pianoUtility = new PianoUtility();
    }

    @AfterEach // esegue questa funzione dopo ogni test eseguito
    void tearDown() { // nel mio caso voglio canellare tutto quello presente nel db
        underTest.deleteAll();
    }

    @Test
    public void savePiano () {
        Piano pianoDaSalvare = getSavablePiano();
        Piano savedPiano = underTest.save(pianoDaSalvare);
        Optional<Piano> readPiano = underTest.findById(savedPiano.getId());
        readPiano.ifPresent(piano -> {
            System.out.println("----------- Mostra la vernice Cal ------------");
            System.out.println(piano.getVernCal());
            System.out.println("----------------------------------------------");
        });
        // Mi assicuro che la fase sia inserita e salvata
        readPiano.ifPresent(piano -> {
            assertThat(piano.getFase()).isNotNull();
            assertThat(piano.getFase().getStato()).isNotNull();
        });
        assertThat(readPiano).isNotEmpty();
    }

    @Test
    public void savePianoAndDelete () {
        Piano pianoDaSalvare = getSavablePiano();
        Piano savedPiano = underTest.save(pianoDaSalvare);
        Optional<Piano> readPiano = underTest.findById(savedPiano.getId());
        assertThat(readPiano).isNotEmpty();
        underTest.delete(savedPiano);
        readPiano.ifPresent(piano -> {
            Long id_cliente = piano.getCliente().getId_clifor();
            Long id_dipendente = piano.getDipendente().getId();
            Long id_commerciale = piano.getCommerciale().getId();
            String carta_abbr = piano.getCarta().getAbbr();
            // Mi assicuro che a cascata non cancello clienti e dipendenti ma commerciale si.
            assertThat(clienteRepository.findById(id_cliente)).isNotEmpty();
            assertThat(dipendenteRepository.findById(id_dipendente)).isNotEmpty();
            assertThat(commercialeRepository.findById(id_commerciale)).isEmpty();
            assertThat(cartaRepository.findById(carta_abbr)).isNotEmpty();
        });
        Optional<Piano> deletedPiano = underTest.findById(savedPiano.getId());
        assertThat(deletedPiano).isEmpty();
    }

    @Test
    public void ottieniTuttiINonCompletati () {
        // given
        Piano pianoDaSalvare1 = getSavablePiano();
        Piano pianoDaSalvare2 = getSavablePiano();
        Piano pianoDaSalvare3 = getSavablePiano();
        pianoUtility.addTimestamp(pianoDaSalvare2, 1);
        pianoUtility.addTimestamp(pianoDaSalvare3, 2);
        pianoDaSalvare2.setFase(new Concluso());
        underTest.save(pianoDaSalvare1);
        underTest.save(pianoDaSalvare2);
        underTest.save(pianoDaSalvare3);
        // when
        List<Piano> piani = underTest.findNotComplitedPiani();
        Boolean expected = piani.stream().noneMatch(element -> element.getFase().getStato().equals(Fasi.Concluso));
        Boolean expectedOrder = IntStream.range(0, piani.size() -1)
                .allMatch(idx -> piani.get(idx).getCodiceLavoro()
                        .after(piani.get(idx+1).getCodiceLavoro()));
        // then
        assertThat(piani.size()).isEqualTo(2);
        assertThat(expected).isTrue();
        assertThat(expectedOrder).isTrue();
    }

    @Test
    public void doesPianoExist () {
        // given
        Piano piano = getSavablePiano();
        Piano pianoSalvato = underTest.save(piano);
        //when
        Optional<Piano> pianoLetto = underTest.findById(pianoSalvato.getId());
        boolean expected = underTest.selectExistPiano(pianoLetto.orElse(piano).getCodiceLavoro());
        // then
        assertThat(expected).isTrue();
    }

    @Test
    public void pianoDoesNotExist () {
        // given
        Piano piano = getSavablePiano();
        //when
        boolean expected = underTest.selectExistPiano(piano.getCodiceLavoro());
        // then
        assertThat(expected).isFalse();
    }

    @Test
    public void canGetPianoByClienteAndArchivio () {
        // given
        Piano piano1 = getSavablePiano();
        Piano piano2 = getSavablePiano();
        Piano piano3 = getSavablePiano();
        pianoUtility.addTimestamp(piano2, 1);
        pianoUtility.addTimestamp(piano3, 2);
        piano3.setArchivio("Non prendere");
        // when
        Piano pianoSaved1 = underTest.save(piano1);
        underTest.save(piano2);
        underTest.save(piano3);
        List<Piano> listaPiani = underTest
                .selectPianoByClienteAndArchivio(pianoSaved1.getCliente().getId_clifor(), pianoSaved1.getArchivio());
        Boolean expected = listaPiani.stream().allMatch(element ->
                element.getCliente().getId_clifor().equals(pianoSaved1.getCliente().getId_clifor())
                && element.getArchivio().equals(pianoSaved1.getArchivio())
        );
        Boolean expectedOrder = IntStream.range(0, listaPiani.size() -1)
                .allMatch(idx -> listaPiani.get(idx).getCodiceLavoro()
                        .after(listaPiani.get(idx+1).getCodiceLavoro()));
        // then
        assertThat(listaPiani.size()).isEqualTo(2);
        assertThat(expected).isTrue();
        assertThat(expectedOrder).isTrue();
    }

    @Test
    void dontGetCartelleByClienteAndCodice () {
        // given
        List<Piano> piani = underTest.selectPianoByClienteAndArchivio
                (4000L, "Inventato");
        // when
        //then
        assertThat(piani).isNotNull();
        assertThat(piani.size()).isEqualTo(0);
    }

    @Test
    void canGetLavoroFromCodiceLavoro () {
        // given
        Piano pianoToSave = getSavablePiano();
        Piano pianoSalvato = underTest.save(pianoToSave);
        // then
        Optional<Piano> piano = underTest.selectByCodiceLavoro(pianoSalvato.getCodiceLavoro());
        // when
        assertThat(piano).isNotEmpty();
    }

    @Test
    void shouldDeletePiano () {
        // given
        Piano pianoSalvato = underTest.save(getSavablePiano());
        // then
        int sizeBeforeDelete = underTest.findAll().size();
        underTest.deleteByCodiceLavoro(pianoSalvato.getCodiceLavoro());
        int sizeAfterDelete = underTest.findAll().size();
        // when
        assertThat(sizeBeforeDelete).isEqualTo(1);
        assertThat(sizeAfterDelete).isEqualTo(0);
    }

    Piano getSavablePiano () {
        Piano piano = pianoUtility.createPiano();
        Cliente savedCliente = clienteRepository.findById(piano.getCliente().getId_clifor())
                .orElse(clienteRepository.save(piano.getCliente()));
        Dipendente savedDipendente = dipendenteRepository.findById(piano.getDipendente().getId())
                .orElse(dipendenteRepository.save(piano.getDipendente()));
        Carta savedCarta = cartaRepository.findById(piano.getCarta().getAbbr())
                .orElse(cartaRepository.save(piano.getCarta()));
        piano.setCliente(savedCliente);
        piano.setDipendente(savedDipendente);
        piano.setCarta(savedCarta);
        return piano;
    }

}
