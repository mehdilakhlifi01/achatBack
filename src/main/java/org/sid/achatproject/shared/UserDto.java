package org.sid.achatproject.shared;

import lombok.Data;
import org.sid.achatproject.Request.DemandeRequest;

import java.io.Serializable;
import java.util.List;

@Data
public class UserDto implements Serializable {

    private long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean admin;
    private Boolean achat=false;
    private Boolean commerciale;
    private String encryptedPassword;
    private String emailVerificationToken;
    private Boolean emailVerificationStatus=false;
    private List<DemandeDto> demandes;
    private List<DossierImportDto> dossiers;


}
