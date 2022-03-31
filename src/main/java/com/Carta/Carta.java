package com.Carta;

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
@Table(name = "carte")
public class Carta {

    @Id
    @Column(name = "id")
    private String abbr;
    @Column(name = "nome")
    private String nomeCarta;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "tipo")
    private TipoCarta tipo;
    @Column(name = "quantita")
    private int quantita;

}
