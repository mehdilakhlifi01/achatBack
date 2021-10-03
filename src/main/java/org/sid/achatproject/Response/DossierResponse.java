package org.sid.achatproject.Response;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class DossierResponse {

    private String dossierId;
    private String declarant;
    private String fournisseur;
    private String causeRetardSortie;
    private String causeReatard;
    private String numDossier;
    private Boolean existe;
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
