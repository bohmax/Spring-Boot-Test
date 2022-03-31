package com.Dipendente;

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
@Table(name = "dipendente")
public class Dipendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "cognome")
    private String cognome;
    @Column(name = "mansione1")
    private String mansione1;
    @Column(name = "mansione2")
    private String mansione2;
    @Column(name = "macchina1")
    private String macchina1;
    @Column(name = "macchina2")
    private String macchina2;
    @Column(name = "stato")
    private Integer stato; // -1 licenziato, 0 non visibile, 1 ok

}
