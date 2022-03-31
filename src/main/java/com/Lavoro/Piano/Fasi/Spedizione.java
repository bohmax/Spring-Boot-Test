package com.Lavoro.Piano.Fasi;

import com.Lavoro.Fasi;
import com.Lavoro.Lavoro;
import com.Lavoro.AStatoLavoro;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "6")
public class Spedizione extends AStatoLavoro {

    public Spedizione() {
        super(Fasi.Spedizione);
    }

    @Override
    public void next(Lavoro lvr, Lavoro next) {
        new Concluso();
    }

    @Override
    public void prev(Lavoro lvr, Lavoro mod) {
        new Stampa();
    }

}
