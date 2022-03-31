package com.Lavoro.Piano;

import com.Lavoro.Exception.BadRequestException;
import com.Lavoro.Exception.PianoNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PianoServiceTest {

    // Con il mock non creo nuovamente tutto il db e riesco a testarlo più velocemente
    @Mock private PianoRepository pianoRepository;
    private PianoService underTest;
    private PianoUtility pianoUtility;

    @BeforeEach
    void setUp() {
        pianoUtility = new PianoUtility();
        underTest = new PianoService(pianoRepository);
    }

    @Test
    void canGetAllNotCompletedPiani() {
        // when
        underTest.getAllNotCompletedPiani();
        // then
        // Controlla che il metodo findNotComplitedPiani di piano repository sia stato invocato
        // il mio piano repository è un mock perché lo ho già testato precedentemente in pianoRepository
        // quindi non ho bisogno di testarlo nuovamente
        // per capire meglio posso provare a sostiuire findNotComplitedPiani con deleteAll e eseguire
        verify(pianoRepository).findNotComplitedPiani();
    }

    @Test
    void canAddPiano() {
        // given
        Piano piano = pianoUtility.createPiano();
        // when
        underTest.addPiano(piano);
        // Devo controllare che in add piano il save sia stato invocato usando il nostro piano.
        // then
        ArgumentCaptor<Piano> pianoArgomentoCatturato =
                ArgumentCaptor.forClass(Piano.class);
        // Verifica che è stata chiamata la save di repository, ma vogliamo catturare il piano che è stato invocato
        verify(pianoRepository).save(pianoArgomentoCatturato.capture());

        Piano pianoCatturato = pianoArgomentoCatturato.getValue();
        // Controlliamo che il piano catturato sia lo stesso piano che abbiamo chiamato
        assertThat(pianoCatturato).isEqualTo(piano);
    }

    @Test
    void willThrowWhenCodiceLavoroIsUsed () {
        // given
        Piano piano = pianoUtility.createPiano();
        // Seleziona un valore di ritorno dato un input.
        given(pianoRepository.selectExistPiano(piano.getCodiceLavoro()))
                .willReturn(true);
        // when
        // then
        assertThatThrownBy(() -> underTest.addPiano(piano))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Il codice della cartella: " + piano.getCodiceLavoro() +" è già stato utilizzato, riprova");

        // Verifica che il save di pianoRepository non viene mai chiamato
        verify(pianoRepository, never()).save(any());
    }

    @Test
    void canDeletePiano() {
        // given
        Piano piano = pianoUtility.createPiano();
        given(pianoRepository.selectExistPiano(piano.getCodiceLavoro()))
                .willReturn(true);
        // when
        underTest.deletePiano(piano);
        // Devo controllare che in add piano il save sia stato invocato usando il nostro piano.
        // then
        ArgumentCaptor<Timestamp> pianoArgomentoCatturato =
                ArgumentCaptor.forClass(Timestamp.class);
        // Verifica che è stata chiamata la save di repository, ma vogliamo catturare il piano che è stato invocato
        verify(pianoRepository).deleteByCodiceLavoro(pianoArgomentoCatturato.capture());

        Timestamp timestampCatturato = pianoArgomentoCatturato.getValue();
        // Controlliamo che il piano catturato sia lo stesso piano che abbiamo chiamato
        assertThat(timestampCatturato).isEqualTo(piano.getCodiceLavoro());
    }

    @Test
    void willThrowWhenCodiceLavoroIsNotUsed () {
        // given
        Piano piano = pianoUtility.createPiano();
        // when
        // then
        assertThatThrownBy(() -> underTest.deletePiano(piano))
                .isInstanceOf(PianoNotFoundException.class)
                .hasMessageContaining("Il lavoro in piano del: " + piano.getCodiceLavoro() + " non è stato trovato!");

        // Verifica che il save di pianoRepository non viene mai chiamato
        verify(pianoRepository, never()).deleteByCodiceLavoro(any());
    }

    @Test
    void canUpdateArchivio () {
        // given
        Piano pianoToUpdate = pianoUtility.createPiano();
        Piano updatedPiano =  pianoUtility.createPiano();
        updatedPiano.setArchivio("winrar");
        Mockito.lenient().when(pianoRepository.selectByCodiceLavoro(updatedPiano.getCodiceLavoro()))
                .thenReturn(Optional.of(pianoToUpdate));
        // when
        underTest.updatePiano(updatedPiano);
        // then
        assertThat(updatedPiano.getArchivio()).isEqualTo(pianoToUpdate.getArchivio());
    }

    @Test
    void canNotUpdateArchivio () {
        // given
        Piano pianoToUpdate = pianoUtility.createPiano();
        Piano updatedPiano =  pianoUtility.createPiano();
        updatedPiano.setArchivio("winrar");
        // when
        // then
        assertThatThrownBy(() -> underTest.updatePiano(updatedPiano))
                .isInstanceOf(PianoNotFoundException.class)
                .hasMessageContaining("Il lavoro in piano del: " + updatedPiano.getCodiceLavoro() + " non è stato trovato!");

        assertThat(updatedPiano.getArchivio()).isNotEqualTo(pianoToUpdate.getArchivio());
    }

}