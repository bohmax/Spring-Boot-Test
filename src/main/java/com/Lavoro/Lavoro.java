package com.Lavoro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class  Lavoro {

    @Id
    @SequenceGenerator(name = "lavoro_sequence", sequenceName = "lavoro_sequence", allocationSize = 1 /* Increment value */)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lavoro_sequence")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    protected AStatoLavoro fase;

    public void faseSuccessiva (Lavoro next) {
        fase.next(this, next);
    }

    public void modifica (Lavoro mod) {
        if (!fase.equals(mod.fase)) {
            fase.prev(this, mod);
        }
        setModifiche(mod);
    }

    protected abstract void setModifiche (Lavoro mod);

    public Long getId() {
        return id;
    }

    public AStatoLavoro getFase() {
        return fase;
    }

    public void setFase(AStatoLavoro fase) {
        this.fase = fase;
    }
}
