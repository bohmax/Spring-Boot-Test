package com.Lavoro.Piano.Fasi;

import com.Lavoro.Fasi;
import com.Lavoro.Lavoro;
import com.Lavoro.AStatoLavoro;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "1")
public class Preparazione extends AStatoLavoro {

    public Preparazione() {
        super(Fasi.Preparazione);
    }

    @Override
    public void next(Lavoro lvr, Lavoro next) {
        lvr.setFase(new Prestampa());
    }

    @Override
    public void prev(Lavoro lvr, Lavoro mod) {
    }
}
