package com.Carta;

public enum TipoCarta {

    Bobina,
    Piano;

    private static TipoCarta[] values = null;
    public static final int size = TipoCarta.values().length;
    // attenzione se un giorno dovessero modificarsi le fasi questo va cambiato
    // altrimenti i valori passati non corrisponderebbero
    public static TipoCarta fromInt(int i) {
        if(TipoCarta.values == null) {
            TipoCarta.values = TipoCarta.values();
        }
        return TipoCarta.values[i];
    }
}
