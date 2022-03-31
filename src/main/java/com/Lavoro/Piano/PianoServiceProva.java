package com.Lavoro.Piano;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PianoServiceProva { // Con shift command T creo un test

    private final PianoRepository pianoRepository;

//    public List<Piano> getAllNotCompletedPiani() {
//        return pianoRepository.findAllNotComplited();
//    }

    public void addPiano(Piano piano) {
        // Fare vari check per controllare se
        // il piano esiste e poi salvare
        pianoRepository.save(piano);
    }

    void deletePiano (Piano piano) {
        pianoRepository.delete(piano);
    }

}
