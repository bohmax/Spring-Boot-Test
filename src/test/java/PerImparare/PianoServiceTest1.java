package PerImparare;

import com.Lavoro.Piano.Piano;
import com.Lavoro.Piano.PianoRepository;
import com.Lavoro.Piano.PianoServiceProva;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PianoServiceTest1 {

    /*
     Mock objects is a unit testing technique in which a code chunk is replaced by dummy implementations that emulate real code.
     This helps one to write unit tests targeting the functionality provided by the class under test.
    */
    @Mock private PianoRepository pianoRepository;
    private PianoServiceProva underTest;

    @BeforeEach
    void setUp () {
        underTest = new PianoServiceProva(pianoRepository);
    }

//    @Test
//    void getAllPiani() {
//         when
//         underTest.getAllPiani(); era un esempio
//         then
//         Mockito Verify methods are used to check that certain behavior happened.
//         We can use Mockito verify methods at the end of the testing method code to make sure that specified methods are called.
//        verify(pianoRepository).findAll();
//    }

    @Test
    @Disabled
    void addPiano() {
        // given
        Piano piano = null;

        // when
        underTest.addPiano(piano);

        // then
        // Serve per catturare il piano utilizzato
        ArgumentCaptor<Piano> pianoArgumentCaptor =
                ArgumentCaptor.forClass(Piano.class);

        // Qua possiamo catturarlo per testarlo successivamente
        verify(pianoRepository)
                .save(pianoArgumentCaptor.capture());

        Piano capturedPiano = pianoArgumentCaptor.getValue();

        assertThat(capturedPiano).isEqualTo(piano);
    }


//    @Test
//    void willThrowWhenEmailIsTaken() {
//        // given
//        Student student = new Student(
//                "Jamila",
//                "jamila@gmail.com",
//                Gender.FEMALE
//        );
//
            // Faccio in modo che la mock function ritorni true al posto di false
//        given(studentRepository.selectExistsEmail(anyString()))
//                .willReturn(true);
//
//        // when
//        // then
            // Testo il valore di ritorno che dovrà essere un exception
//        assertThatThrownBy(() -> underTest.addStudent(student))
//                .isInstanceOf(BadRequestException.class)
//                .hasMessageContaining("Email " + student.getEmail() + " taken");
//
            // Significa che verificando student repository save non dovrà mai essere chiamata
//        verify(studentRepository, never()).save(any());
//
//    }

    @Test
    @Disabled
    void deletePiano() {
    }
}