package com.Cliente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "cliente")
public class Cliente {

    @Id
    @Column(name = "id_clifor")
    private Long id_clifor;
    @Column(name = "denominazione", columnDefinition = "varchar(127)")
    private String denominazione;
    @Column(name = "telefono1")
    private String telefono1;
    @Column(name = "email")
    private String email;

}
