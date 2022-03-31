package com.Lavoro.Piano;

import com.Carta.Carta;
import com.Cliente.Cliente;
import com.Commerciale.Commerciale;
import com.Dipendente.Dipendente;
import com.Lavoro.Lavoro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@Jacksonized
@Table(name = "tirature")
public class Piano extends Lavoro {

    @Column(name = "data_lavoro", unique = true, updatable = false, nullable = false)
    private Timestamp codiceLavoro;
    @Column(name = "blocca", nullable = false)
    private Boolean blocca;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", referencedColumnName = "id_clifor", updatable = false, nullable = false)
    private Cliente cliente;
    @ManyToOne(optional = false)
    @JoinColumn(name = "dipendente_id", referencedColumnName = "id", nullable = false)
    private Dipendente dipendente;

    @Column(name = "archivio", columnDefinition = "varchar(127)") private String archivio;
    @Column(name = "mail_bozza", columnDefinition = "varchar(63)", nullable = false)
    private String mailBozza;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "commerciale_id", referencedColumnName = "id")
    private Commerciale commerciale;

    // Pianificazione Lavoro
    @Column(name = "note_corr", columnDefinition = "varchar(511)") private String noteCorr;
    @Column(name = "email") private Integer email;
    @Column(name = "busta") private Boolean busta;
    @Column(name = "assiste") private Boolean assiste;
    @Column(name = "interno") private Boolean interno;
    @Column(name = "esatta") private Boolean esatta;

    // Descrizione
    @Column(name = "descrizione", columnDefinition = "varchar(255)") private String descrizione;
    @Column(name = "tipo_imba", columnDefinition = "varchar(63)") private String tipoImba;
    @Column(name = "tipo_sped", columnDefinition = "varchar(63)") private String tipoSped;
    @Column(name = "colori", columnDefinition = "varchar(63)") private String colori;
    @Column(name = "n_colori", columnDefinition = "varchar(63)") private String nColori;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> vernCal;

    @Column(name = "vern_acr", columnDefinition = "varchar(63)") private String vernAcr;
    @Column(name = "CTP", columnDefinition = "varchar(63)") private String CTP;
    @Column(name = "form_chiuso", columnDefinition = "varchar(63)") private String formChiu;
    @Column(name = "form_aperto", columnDefinition = "varchar(63)") private String formApe;
    @Column(name = "n_pag_int", columnDefinition = "varchar(63)") private String nPagInt;
    @Column(name = "taglio", columnDefinition = "varchar(63)") private String taglio;
    @Column(name = "dorso", columnDefinition = "varchar(63)") private String dorso;
    @Column(name = "macch") private Integer macch;
    @Column(name = "macch_off") private Integer maccOff;

    /* Carta/Busta */
    @ManyToOne(optional = false)
    @JoinColumn(name = "carta_id", referencedColumnName = "id", nullable = false)
    private Carta carta;
    @Column(name = "grammi", columnDefinition = "varchar(63)") private String grammi;
    @Column(name = "fogli", columnDefinition = "varchar(63)") private String fogli;
    @Column(name = "formati", columnDefinition = "varchar(63)") private String formati;
    @Column(name = "dim_carta", columnDefinition = "varchar(63)") private String dimCarta;

    @Column(name = "dim_busta", columnDefinition = "varchar(63)") private String dimBusta;
    @Column(name = "tipo_busta", columnDefinition = "varchar(63)") private String tipoBusta;
    @Column(name = "col_busta", columnDefinition = "varchar(63)") private String colBusta;
    @Column(name = "note_busta", columnDefinition = "varchar(63)") private String noteBusta;

    @Override
    protected void setModifiche(Lavoro mod) {

    }

    public Timestamp getCodiceLavoro() {
        return codiceLavoro;
    }

//    private void setCodiceLavoro(Timestamp codiceLavoro) {
//        if (codiceLavoro == null) {
//            codiceLavoro = new Timestamp(System.currentTimeMillis());
//            codiceLavoro.setNanos(0); // quando viene inserita una cartella resetto i nanosecondi perché non vengono inviati al client
//        }
//        CodiceLavoro = codiceLavoro;
//    }

    @Override
    public boolean equals(Object obj) {
        /* Controlla che si tratti della stessa classe */
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Piano lav = (Piano) obj;
        return codiceLavoro.equals(lav.getCodiceLavoro());
    }
}
