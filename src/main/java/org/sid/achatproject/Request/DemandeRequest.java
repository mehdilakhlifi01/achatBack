package org.sid.achatproject.Request;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class DemandeRequest {

    private String article ;
    private String unite ;
    private String qteDemander  ;
    private String client ;
    private String prixAchat ;
    private String prixVente ;
    private String observation ;
    private String nomDemandeur ;
    private Boolean approbationDirection;
    private Boolean existe;
    private String numDossier;

}


