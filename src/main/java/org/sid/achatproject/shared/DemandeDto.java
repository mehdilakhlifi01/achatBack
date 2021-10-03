package org.sid.achatproject.shared;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class DemandeDto {

    private long id;
    private String demandeId;
    private String numDossier;
    private String article ;
    private String unite ;
    private Date date;
    private String qteDemander  ;
    private String client ;
    private String prixAchat ;
    private String prixVente ;
    private String observation ;
    private String nomDemandeur ;
    private Boolean approbationDirection ;
    private Boolean existe ;

    private UserDto user;
}
