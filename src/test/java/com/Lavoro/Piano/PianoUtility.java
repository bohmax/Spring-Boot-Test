package com.Lavoro.Piano;

import com.Carta.Carta;
import com.Carta.TipoCarta;
import com.Cliente.Cliente;
import com.Commerciale.Commerciale;
import com.Dipendente.Dipendente;
import com.Lavoro.Piano.Fasi.Preparazione;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class PianoUtility {

    Piano createPiano () {
        Cliente cliente = Cliente.builder()
                .id_clifor(4000L)
                .denominazione("Simbula")
                .telefono1("0709030")
                .email("mssmpuddu@gmail.com")
                .build();
        Dipendente dipendente = new Dipendente(1L, "Massimo", "Puddu", "mansione", "mansione2", "macc", "macc2", 1);
        Carta carta = Carta.builder()
                .abbr("Abbr")
                .nomeCarta("Carta nuova")
                .tipo(TipoCarta.Bobina)
                .quantita(10)
                .build();
        return createPiano(cliente, dipendente, carta);
    }

    Piano createPiano (Cliente cliente, Dipendente dip, Carta carta) {
        Commerciale commerciale = Commerciale.builder()
                .suffix(1)
                .data("lol")
                .val("ah")
                .build();
        List<String> vernici = new ArrayList<>();
        vernici.add("metallo");
        vernici.add("metallo1");
        Timestamp codiceLavoro = new Timestamp(System.currentTimeMillis());
        codiceLavoro.setNanos(0); // quando viene inserita una cartella resetto i nanosecondi perché non vengono inviati al client
        return Piano.builder()
                // inserire la fase
                .fase(new Preparazione())
                .codiceLavoro(codiceLavoro)
                .blocca(false)
                .cliente(cliente)
                .dipendente(dip)
                .archivio("404")
                .mailBozza("mssmpuddu@gmail.com")
                .commerciale(commerciale)
                .noteCorr("Ask your son")
                .email(1)
                .busta(false)
                .assiste(true)
                .interno(false)
                .esatta(false)
                .descrizione("lol")
                .tipoImba("eh dai")
                .tipoSped("ahah")
                .colori("blue")
                .nColori("10...9...8")
                .vernCal(vernici)
                .vernAcr("Alfa")
                .CTP("love")
                .formChiu("chiuso")
                .formApe("Apert")
                .nPagInt("nPagInt")
                .taglio("Meglio")
                .dorso("Dorse")
                .macch(0)
                .maccOff(0)
                .carta(carta)
                .grammi("Grammi")
                .fogli("Fogli")
                .formati("Formati")
                .dimCarta("DimCarta")
                .dimBusta("DimBusta")
                .tipoBusta("TipoBusta")
                .colBusta("ColBusta")
                .noteBusta("NoteBusta")
                .build();
    }

    void addTimestamp (Piano piano, int tempo) {
        Timestamp codiceLavoro = new Timestamp(System.currentTimeMillis() + tempo* 1000L);
        codiceLavoro.setNanos(0); // quando viene inserita una cartella resetto i nanosecondi perché non vengono inviati al client
        piano.setCodiceLavoro(codiceLavoro);
    }

}
