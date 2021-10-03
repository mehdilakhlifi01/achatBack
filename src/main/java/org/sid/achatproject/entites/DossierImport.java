package org.sid.achatproject.entites;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "dossiers")
@Data @AllArgsConstructor @NoArgsConstructor
public class DossierImport implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = true)
    private String dossierId;
    @Column(nullable = true)
    private String numDossier;
    @Column(nullable = true,length = 50)
    private Boolean existe=false ;
    @Column(nullable = true,length = 50)
    private String declarant;
    @Column(nullable = true,length = 50)
    private String fournisseur;
    @Column(nullable = true,length = 50)
    private String causeRetardSortie;
    @Column(nullable = true,length = 50)
    private String causeReatard;
    @Column(nullable = true,length = 50)
    private String causeReatrdLivraison;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dteReceptArc;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateReception;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date delaiDisponibilite;
    @Column(nullable = true,length = 50)
    private String type;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateMiseDisposition;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateOrdreEnlevement;
    @Column(nullable = true,length = 50)
    private String transporteur;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateEnlevement;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateEmp;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateArrivePrevi;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateArrive;
    @Column(nullable = true,length = 50)
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dteTransmisiontDoc;
    @Column(nullable = true,length = 50)
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateLancement;
    @Column(nullable = true,length = 50)
    private String numDeclaration;
    @Column(nullable = true,length = 50)
    private String numOrder;
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateBonSortie;
    @Column(nullable = true,length = 50)
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateLivraison;
    @Column(nullable = true,length = 50)
    private String reception;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "users_id")
    private UserEntity user;










}
