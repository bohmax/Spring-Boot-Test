package com.Lavoro;

public interface StatoLavoro {

    void next(Lavoro lvr, Lavoro next);
    void prev(Lavoro lvr, Lavoro mod);
}
