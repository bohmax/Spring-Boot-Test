package com.Cliente;

import com.Cliente.Cliente;
import com.Cliente.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootApplication
public class ClientiRepositoryTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void ClienteIsSaved () {
        Cliente cliente = new Cliente(4000L, "Simbula", "0709030", "mssmpuddu@gmail.com");
        Cliente savedCliente = clienteRepository.save(cliente);
        assertThat(clienteRepository.findById(savedCliente.getId_clifor())).isNotEmpty();
    }

    @Test
    public void ClienteSalvatoMaNonTrovato () {
        Cliente cliente = new Cliente(4000L, "Simbula", "0709030", "mssmpuddu@gmail.com");
        clienteRepository.save(cliente);
        assertThat(clienteRepository.findById(10L)).isEmpty();
    }

}
