package com.Lavoro.Piano.Fasi;

import com.Lavoro.Fasi;
import com.Lavoro.Lavoro;
import com.Lavoro.AStatoLavoro;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "4")
public class Stampa extends AStatoLavoro {

    public Stampa() {
        super(Fasi.Stampa);
    }

    @Override
    public void next(Lavoro lvr, Lavoro next) {
        new Legatoria();
    }

    @Override
    public void prev(Lavoro lvr, Lavoro mod) {
        new Incisione();
    }

}
