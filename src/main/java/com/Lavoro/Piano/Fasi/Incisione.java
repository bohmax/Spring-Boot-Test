package com.Lavoro.Piano.Fasi;

import com.Lavoro.Fasi;
import com.Lavoro.Lavoro;
import com.Lavoro.AStatoLavoro;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "3")
public class Incisione extends AStatoLavoro {

    public Incisione() {
        super(Fasi.Incisione);
    }

    @Override
    public void next(Lavoro lvr, Lavoro next) {
        new Stampa();
    }

    @Override
    public void prev(Lavoro lvr, Lavoro mod) {
        new Prestampa();
    }

}
