package com.Lavoro.Piano.Fasi;

import com.Lavoro.Fasi;
import com.Lavoro.Lavoro;
import com.Lavoro.AStatoLavoro;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "2")
public class Prestampa extends AStatoLavoro {

    public Prestampa() {
        super(Fasi.Prestampa);
    }

    @Override
    public void next(Lavoro lvr, Lavoro next) {
        new Incisione();
    }

    @Override
    public void prev(Lavoro lvr, Lavoro mod) {
        // Non posso andare in preparazione se sono in prestampa
    }

}
