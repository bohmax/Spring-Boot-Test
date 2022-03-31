package com.Dipendente;

import com.Cliente.Cliente;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DipendenteRepositoryTest {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Test
    public void dipendenteIsSaved () {
        Dipendente dip = Dipendente.builder()
                .nome("Massimo")
                .cognome("Puddu")
                .mansione1("Manz")
                .mansione2("Hello")
                .macchina1("macc")
                .macchina2("macc2")
                .stato(1)
                .build();
        Dipendente savedDip = dipendenteRepository.save(dip);
        assertThat(dipendenteRepository.findById(savedDip.getId())).isNotEmpty();
    }

}
