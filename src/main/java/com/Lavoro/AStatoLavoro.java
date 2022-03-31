package com.Lavoro;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "FASE_TYPE",
        discriminatorType = DiscriminatorType.INTEGER
)
@Table(name = "stato")
public abstract class AStatoLavoro implements StatoLavoro {

    @Id
    @SequenceGenerator(name = "stato_sequence", sequenceName = "stato_sequence", allocationSize = 1 /* Increment value */)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stato_sequence")
    private Long id;

    @Enumerated(EnumType.STRING)
    protected final Fasi stato;

    protected AStatoLavoro(Fasi stato) {
        this.stato = stato;
    }

    public AStatoLavoro() {
        this.stato = getStato();
    }

    public Fasi getStato() {
        return stato;
    }
}
