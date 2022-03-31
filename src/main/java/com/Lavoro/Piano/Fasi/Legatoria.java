package com.Lavoro.Piano.Fasi;

import com.Lavoro.Fasi;
import com.Lavoro.Lavoro;
import com.Lavoro.AStatoLavoro;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "5")
public class Legatoria  extends AStatoLavoro {

    public Legatoria() {
        super(Fasi.Legatora);
    }

    @Override
    public void next(Lavoro lvr, Lavoro next) {

    }

    @Override
    public void prev(Lavoro lvr, Lavoro mod) {
        new Stampa();
    }

}
