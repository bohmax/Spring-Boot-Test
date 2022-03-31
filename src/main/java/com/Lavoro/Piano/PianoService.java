package com.Lavoro.Piano;

import com.Lavoro.Exception.BadRequestException;
import com.Lavoro.Exception.PianoNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Service
@AllArgsConstructor
public class PianoService { // Con shift command T creo un test

    private final PianoRepository pianoRepository;

    public List<Piano> getAllNotCompletedPiani () {
        return pianoRepository.findNotComplitedPiani();
    }

    public void addPiano(Piano piano) {
        boolean existPiano = pianoRepository.selectExistPiano(piano.getCodiceLavoro());
        if (existPiano) {
            throw new BadRequestException("Il codice della cartella: " + piano.getCodiceLavoro() + " è già stato utilizzato, riprova");
        }
        pianoRepository.save(piano);
    }

    public void deletePiano (Piano piano) {
        Timestamp codiceLavoro = piano.getCodiceLavoro();
        if(!pianoRepository.selectExistPiano(codiceLavoro)) {
            throw new PianoNotFoundException(piano);
        }
        pianoRepository.deleteByCodiceLavoro(codiceLavoro);
    }

    public Piano getPianoByCodiceLavoro (Timestamp codiceLavoro) {
        return pianoRepository.selectByCodiceLavoro(codiceLavoro)
                .orElseThrow(() -> new PianoNotFoundException("Il lavoro in piano del: " + codiceLavoro + " non è stato trovato!"));
    }

    public List<Piano> getCartelleByClienteAndArchivio (Piano piano) {
        return pianoRepository.selectPianoByClienteAndArchivio
                (piano.getCliente().getId_clifor(), piano.getArchivio());
    }

    @Transactional
    public void updatePiano (Piano updatedPiano) {
        Piano pianoSaved = pianoRepository.selectByCodiceLavoro(updatedPiano.getCodiceLavoro())
                .orElseThrow(() -> new PianoNotFoundException(updatedPiano));
        pianoSaved.setArchivio(updatedPiano.getArchivio());
    }

}
