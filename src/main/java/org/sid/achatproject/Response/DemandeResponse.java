package org.sid.achatproject.Response;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class DemandeResponse {

    private String demandeId;
    private String article ;
    private String unite ;
    private String qteDemander  ;
    private Date date;
    private String client ;
    private String numDossier;
    private String prixAchat ;
    private String prixVente ;
    private String observation ;
    private String nomDemandeur ;
    private Boolean approbationDirection ;
    private Boolean existe ;

}
