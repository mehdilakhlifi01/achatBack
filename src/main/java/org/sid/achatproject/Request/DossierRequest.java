package org.sid.achatproject.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.sid.achatproject.entites.UserEntity;

import javax.persistence.*;
import java.util.Date;

@Data
public class DossierRequest {


    private String dossierId;
    private String declarant;
    private String fournisseur;
    private String causeRetardSortie;
    private String causeReatard;
    private String causeReatrdLivraison;
    private Date dteReceptArc;
    private Date dateReception;
    private Date delaiDisponibilite;
    private String type;
    private Date dateMiseDisposition;
    private Date dateOrdreEnlevement;
    private String transporteur;
    private Date dateEnlevement;
    private Date dateEmp;
    private Boolean existe ;
    private String numDossier;
    private Date dateArrivePrevi;
    private Date dateArrive;
    private Date dteTransmisiontDoc;
    private Date dateLancement;
    private String numDeclaration;
    private String numOrder;
    private Date dateBonSortie;
    private Date dateLivraison;
    private String reception;


}


