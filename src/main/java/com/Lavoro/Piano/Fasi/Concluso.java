package com.Lavoro.Piano.Fasi;

import com.Lavoro.Fasi;
import com.Lavoro.Lavoro;
import com.Lavoro.AStatoLavoro;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "7")
public class Concluso extends AStatoLavoro {

    public Concluso() {
        super(Fasi.Concluso);
    }

    @Override
    public void next(Lavoro lvr, Lavoro next) {
        // finish
    }

    @Override
    public void prev(Lavoro lvr, Lavoro mod) {
        // Probabilmente non potrà tornare indietro
        new Spedizione();
    }

}
