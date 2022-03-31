package com.Lavoro.Piano;

//import Commons.Gestisci.Carta;
//import Commons.Gestisci.Dipendente;
//import Commons.Piano.*;
//import Lavoro.Lavoro;
//import Lavoro.Piano.Fasi.Prestampa;
//import Serializers.CustomDipSerializer;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.databind.BeanDescription;
//import com.fasterxml.jackson.databind.JavaType;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//
//import javax.persistence.*;
//import java.sql.Timestamp;
//import java.util.List;

//@Entity
//@AllArgsConstructor
//@Builder
//@Table(name = "Tirature")
//public class PianoJson extends Lavoro {
//
//    @JsonIgnore private static List<BeanPropertyDefinition> properties; // get e setter di Preparazione
//    @JsonIgnore private static final ObjectMapper mapper = new ObjectMapper();
//
//    @JsonIgnore
//    @Id
//    @SequenceGenerator(
//            name = "piano_sequence",
//            sequenceName = "piano_sequence",
//            allocationSize = 1 // Increment value
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "piano_sequence"
//    )
//    private Long id;
//    @Column(
//            name = "CodiceLavoro",
//            nullable = false
//    )
//    @JsonProperty("CodiceLavoro")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Europe/Rome")
//    private Timestamp CodiceLavoro;
//    @Column(name = "Blocca", nullable = false)
//    @JsonProperty("Blocca")
//    private Boolean Blocca;
//
//    // Cliente
//    @Column(name = "CodiceCliente", nullable = false) @JsonProperty("CodiceCliente") private Integer CodiceCliente;
//    @JsonProperty("NomeCliente") private String NomeCliente;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "Operatore", referencedColumnName = "id")
//    @JsonProperty("CodiceOperatore")
//    @JsonSerialize(using = CustomDipSerializer.class)
//    private Dipendente Operatore;
//    @Column(name = "MailBozza")
//    @JsonProperty("MailBozza")
//    private String MailBozza;
//    @Column(name = "Tel") @JsonProperty("Tel") private String Tel;
//    @Column(name = "Archivio") @JsonProperty("Archivio") private String Archivio;
//    @Column(name = "Commerciale") @JsonProperty("Comm") private Commerciale Commerciale;
//
//    // Pianificazione Lavoro
//    @Column(name = "NoteCorr") @JsonProperty("NoteCorr") private String NoteCorr;
//    @Column(name = "Email") @JsonProperty("Email") private Integer Email;
//    @Column(name = "Busta") @JsonProperty("Busta") private Boolean Busta;
//    @Column(name = "Assiste") @JsonProperty("Assiste") private Boolean Assiste;
//    @Column(name = "Interno") @JsonProperty("Interno") private Boolean Interno;
//    @Column(name = "Esatta") @JsonProperty("Esatta") private Boolean Esatta;
//
//    // Descrizione
//    @Column(name = "Descrizione") @JsonProperty("Descrizione") private String Descrizione;
//    @Column(name = "TipoImba") @JsonProperty("TipoImba") private String TipoImba;
//    @Column(name = "TipoSped") @JsonProperty("TipoSped") private String TipoSped;
//    @Column(name = "Colori") @JsonProperty("Colori") private String Colori;
//    @Column(name = "NColori") @JsonProperty("NColori") private String NColori;
//    @Column(name = "VernCal") @JsonProperty("VernCal") private String[] VernCal;
//    @Column(name = "VernAcr") @JsonProperty("VernAcr") private String VernAcr;
//    @Column(name = "CTP") @JsonProperty("CTP") private String CTP;
//    @Column(name = "FormChiu") @JsonProperty("FormChiu") private String FormChiu;
//    @Column(name = "FormApe") @JsonProperty("FormApe") private String FormApe;
//    @Column(name = "NPagInt") @JsonProperty("NPagInt") private String NPagInt;
//    @Column(name = "Taglio") @JsonProperty("Taglio") private String Taglio;
//    @Column(name = "Dorso") @JsonProperty("Dorso") private String Dorso;
//    @Column(name = "Macch") @JsonProperty("Macch") private Integer Macch;
//    @Column(name = "MaccOff") @JsonProperty("MaccOff") private Integer MaccOff;
//
//    /* Carta/Busta */
//    @Column(name = "TipoCarta") @JsonProperty("TipoCarta") private Carta TipoCarta;
//    @Column(name = "Grammi") @JsonProperty("Grammi") private String Grammi;
//    @Column(name = "Fogli") @JsonProperty("Fogli") private String Fogli;
//    @Column(name = "Formati") @JsonProperty("Formati") private String Formati;
//    @Column(name = "DimCarta") @JsonProperty("DimCarta") private String DimCarta;
//
//    @Column(name = "DimBusta") @JsonProperty("DimBusta") private String DimBusta;
//    @Column(name = "TipoBusta") @JsonProperty("TipoBusta") private String TipoBusta;
//    @Column(name = "ColBusta") @JsonProperty("ColBusta") private String ColBusta;
//    @Column(name = "NoteBusta") @JsonProperty("NoteBusta") private String NoteBusta;
//
//    /* Prestampa */
//    @Column(name = "Segnature") @JsonProperty("Segnature") private Segnature[] Segnature;
//
//    // Dietro
//    @Column(name = "VQuantita") @JsonProperty("VQuantita") private String VQuantita;
//    @Column(name = "Stampa") @JsonProperty("Stampa") private OperatoreStampa[] Stampa;
//    @Column(name = "Legatoria") @JsonProperty("Legatoria") private OperatoreLegatoria[] Legatoria;
//    @Column(name = "Ciano") @JsonProperty("Ciano") private PantoniPiano Ciano;
//    @Column(name = "Giallo") @JsonProperty("Giallo") private PantoniPiano Giallo;
//    @Column(name = "Magenta") @JsonProperty("Magenta") private PantoniPiano Magenta;
//    @Column(name = "Nero") @JsonProperty("Nero") private PantoniPiano Nero;
//    @Column(name = "Vernice") @JsonProperty("Vernice") private PantoniPiano Vernice;
//    @Column(name = "NoteInch") @JsonProperty("NoteInch") private String NoteInch;
//    @Column(name = "NumSegn") @JsonProperty("NumSegn") private NumeriSegnature[] NumSegn;
//    @Column(name = "Ncolli") @JsonProperty("Ncolli") private String Ncolli;
//    @Column(name = "Peso") @JsonProperty("Peso") private String Peso;
//    @Column(name = "Consegna") @JsonProperty("Consegna") private String Consegna;
//    @Column(name = "Giacenza") @JsonProperty("Giacenza") private String Giacenza;
//
//    private void instatiateClass () {
//        if (properties == null) {
//            JavaType userType;
//            BeanDescription introspection;
//
//            userType = mapper.getTypeFactory().constructType(PianoJson.class);
//            introspection = mapper.getSerializationConfig().introspect(userType);
//            properties = introspection.findProperties();
//
//        }
//    }
//
//    public PianoJson() {
//        instatiateClass();
//        CodiceLavoro = new Timestamp(System.currentTimeMillis());
//        CodiceLavoro.setNanos(0); // quando viene inserita una cartella resetto i nanosecondi perché non vengono inviati al client
//        fase = new Prestampa();
//        Blocca = false;
//    }
//
//    @Override
//    protected void saveInDB() {
//
//    }
//
//    @Override
//    protected void setModifiche(Lavoro mod) {
//
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Timestamp getCodiceLavoro() {
//        return CodiceLavoro;
//    }
//
//    public void setCodiceLavoro(Timestamp codiceLavoro) {
//        CodiceLavoro = codiceLavoro;
//    }
//
//    public Boolean getBlocca() {
//        return Blocca;
//    }
//
//    public void setBlocca(Boolean blocca) {
//        Blocca = blocca;
//    }
//
//    public Integer getCodiceCliente() {
//        return CodiceCliente;
//    }
//
//    public void setCodiceCliente(Integer codiceCliente) {
//        CodiceCliente = codiceCliente;
//    }
//
//    public Dipendente getOperatore() {
//        return Operatore;
//    }
//
//    public String getNomeCliente() {
//        return NomeCliente;
//    }
//
//    public void setNomeCliente(String nomeCliente) {
//        NomeCliente = nomeCliente;
//    }
//
//    public void setOperatore(Dipendente operatore) {
//        Operatore = operatore;
//    }
//
//    public String getMailBozza() {
//        return MailBozza;
//    }
//
//    public void setMailBozza(String mailBozza) {
//        MailBozza = mailBozza;
//    }
//
//    public String getTel() {
//        return Tel;
//    }
//
//    public void setTel(String tel) {
//        Tel = tel;
//    }
//
//    public String getArchivio() {
//        return Archivio;
//    }
//
//    public void setArchivio(String archivio) {
//        Archivio = archivio;
//    }
//
//    public Commons.Piano.Commerciale getCommerciale() {
//        return Commerciale;
//    }
//
//    public void setCommerciale(Commons.Piano.Commerciale commerciale) {
//        Commerciale = commerciale;
//    }
//
//    public String getNoteCorr() {
//        return NoteCorr;
//    }
//
//    public void setNoteCorr(String noteCorr) {
//        NoteCorr = noteCorr;
//    }
//
//    public Integer getEmail() {
//        return Email;
//    }
//
//    public void setEmail(Integer email) {
//        Email = email;
//    }
//
//    public Boolean getBusta() {
//        return Busta;
//    }
//
//    public void setBusta(Boolean busta) {
//        Busta = busta;
//    }
//
//    public Boolean getAssiste() {
//        return Assiste;
//    }
//
//    public void setAssiste(Boolean assiste) {
//        Assiste = assiste;
//    }
//
//    public Boolean getInterno() {
//        return Interno;
//    }
//
//    public void setInterno(Boolean interno) {
//        Interno = interno;
//    }
//
//    public Boolean getEsatta() {
//        return Esatta;
//    }
//
//    public void setEsatta(Boolean esatta) {
//        Esatta = esatta;
//    }
//
//    public String getDescrizione() {
//        return Descrizione;
//    }
//
//    public void setDescrizione(String descrizione) {
//        Descrizione = descrizione;
//    }
//
//    public String getTipoImba() {
//        return TipoImba;
//    }
//
//    public void setTipoImba(String tipoImba) {
//        TipoImba = tipoImba;
//    }
//
//    public String getTipoSped() {
//        return TipoSped;
//    }
//
//    public void setTipoSped(String tipoSped) {
//        TipoSped = tipoSped;
//    }
//
//    public String getColori() {
//        return Colori;
//    }
//
//    public void setColori(String colori) {
//        Colori = colori;
//    }
//
//    public String getNColori() {
//        return NColori;
//    }
//
//    public void setNColori(String NColori) {
//        this.NColori = NColori;
//    }
//
//    public String[] getVernCal() {
//        return VernCal;
//    }
//
//    public void setVernCal(String[] vernCal) {
//        VernCal = vernCal;
//    }
//
//    public String getVernAcr() {
//        return VernAcr;
//    }
//
//    public void setVernAcr(String vernAcr) {
//        VernAcr = vernAcr;
//    }
//
//    public String getCTP() {
//        return CTP;
//    }
//
//    public void setCTP(String CTP) {
//        this.CTP = CTP;
//    }
//
//    public String getFormChiu() {
//        return FormChiu;
//    }
//
//    public void setFormChiu(String formChiu) {
//        FormChiu = formChiu;
//    }
//
//    public String getFormApe() {
//        return FormApe;
//    }
//
//    public void setFormApe(String formApe) {
//        FormApe = formApe;
//    }
//
//    public String getNPagInt() {
//        return NPagInt;
//    }
//
//    public void setNPagInt(String NPagInt) {
//        this.NPagInt = NPagInt;
//    }
//
//    public String getTaglio() {
//        return Taglio;
//    }
//
//    public void setTaglio(String taglio) {
//        Taglio = taglio;
//    }
//
//    public String getDorso() {
//        return Dorso;
//    }
//
//    public void setDorso(String dorso) {
//        Dorso = dorso;
//    }
//
//    public Integer getMacch() {
//        return Macch;
//    }
//
//    public void setMacch(Integer macch) {
//        Macch = macch;
//    }
//
//    public Integer getMaccOff() {
//        return MaccOff;
//    }
//
//    public void setMaccOff(Integer maccOff) {
//        MaccOff = maccOff;
//    }
//
//    public Carta getTipoCarta() {
//        return TipoCarta;
//    }
//
//    public void setTipoCarta(Carta tipoCarta) {
//        TipoCarta = tipoCarta;
//    }
//
//    public String getGrammi() {
//        return Grammi;
//    }
//
//    public void setGrammi(String grammi) {
//        Grammi = grammi;
//    }
//
//    public String getFogli() {
//        return Fogli;
//    }
//
//    public void setFogli(String fogli) {
//        Fogli = fogli;
//    }
//
//    public String getFormati() {
//        return Formati;
//    }
//
//    public void setFormati(String formati) {
//        Formati = formati;
//    }
//
//    public String getDimCarta() {
//        return DimCarta;
//    }
//
//    public void setDimCarta(String dimCarta) {
//        DimCarta = dimCarta;
//    }
//
//    public String getDimBusta() {
//        return DimBusta;
//    }
//
//    public void setDimBusta(String dimBusta) {
//        DimBusta = dimBusta;
//    }
//
//    public String getTipoBusta() {
//        return TipoBusta;
//    }
//
//    public void setTipoBusta(String tipoBusta) {
//        TipoBusta = tipoBusta;
//    }
//
//    public String getColBusta() {
//        return ColBusta;
//    }
//
//    public void setColBusta(String colBusta) {
//        ColBusta = colBusta;
//    }
//
//    public String getNoteBusta() {
//        return NoteBusta;
//    }
//
//    public void setNoteBusta(String noteBusta) {
//        NoteBusta = noteBusta;
//    }
//
//    public Commons.Piano.Segnature[] getSegnature() {
//        return Segnature;
//    }
//
//    public void setSegnature(Commons.Piano.Segnature[] segnature) {
//        Segnature = segnature;
//    }
//
//    public String getVQuantita() {
//        return VQuantita;
//    }
//
//    public void setVQuantita(String VQuantita) {
//        this.VQuantita = VQuantita;
//    }
//
//    public OperatoreStampa[] getStampa() {
//        return Stampa;
//    }
//
//    public void setStampa(OperatoreStampa[] stampa) {
//        Stampa = stampa;
//    }
//
//    public OperatoreLegatoria[] getLegatoria() {
//        return Legatoria;
//    }
//
//    public void setLegatoria(OperatoreLegatoria[] legatoria) {
//        Legatoria = legatoria;
//    }
//
//    public PantoniPiano getCiano() {
//        return Ciano;
//    }
//
//    public void setCiano(PantoniPiano ciano) {
//        Ciano = ciano;
//    }
//
//    public PantoniPiano getMagenta() {
//        return Magenta;
//    }
//
//    public void setMagenta(PantoniPiano magenta) {
//        Magenta = magenta;
//    }
//
//    public PantoniPiano getGiallo() {
//        return Giallo;
//    }
//
//    public void setGiallo(PantoniPiano giallo) {
//        Giallo = giallo;
//    }
//
//    public PantoniPiano getNero() {
//        return Nero;
//    }
//
//    public void setNero(PantoniPiano nero) {
//        Nero = nero;
//    }
//
//    public PantoniPiano getVernice() {
//        return Vernice;
//    }
//
//    public void setVernice(PantoniPiano vernice) {
//        Vernice = vernice;
//    }
//
//    public String getNoteInch() {
//        return NoteInch;
//    }
//
//    public void setNoteInch(String noteInch) {
//        NoteInch = noteInch;
//    }
//
//    @JsonProperty("NumSegn")
//    public NumeriSegnature[] getNumSegn() {
//        return NumSegn;
//    }
//
//    @JsonProperty("NumSegn")
//    public void setNumSegn(NumeriSegnature[] numSegn) {
//        NumSegn = numSegn;
//    }
//
//    public String getNcolli() {
//        return Ncolli;
//    }
//
//    public void setNcolli(String ncolli) {
//        Ncolli = ncolli;
//    }
//
//    public String getPeso() {
//        return Peso;
//    }
//
//    public void setPeso(String peso) {
//        Peso = peso;
//    }
//
//    public String getConsegna() {
//        return Consegna;
//    }
//
//    public void setConsegna(String consegna) {
//        Consegna = consegna;
//    }
//
//    public String getGiacenza() {
//        return Giacenza;
//    }
//
//    public void setGiacenza(String giacenza) {
//        Giacenza = giacenza;
//    }
//
//    public static List<BeanPropertyDefinition> getProperties() {
//        return properties;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        /* Controlla che si tratti della stessa classe */
//        if (obj == null || getClass() != obj.getClass()) {
//            return false;
//        }
//        PianoJson lav = (PianoJson) obj;
//        return CodiceLavoro.equals(lav.CodiceLavoro);
//    }
//}
