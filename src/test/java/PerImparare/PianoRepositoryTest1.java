package PerImparare;

import com.Lavoro.Piano.PianoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@DataJpaTest
@SpringBootTest
public class PianoRepositoryTest1 {

    @Autowired
    private PianoRepository pianoRepository;

    @AfterEach // esegue questa funzione dopo ogni test eseguito
    void tearDown() { // nel mio caso voglio canellare tutto quello presente nel db
        pianoRepository.deleteAll();
    }

    @Test
    void isInserted() {
        //given: Inserisce nel database l'input da testare
        //when: return value dell'operazione da testare
        //then: Serve per fare l'assert, il test vero e proprio
        pianoRepository.save(null);
    }


}
