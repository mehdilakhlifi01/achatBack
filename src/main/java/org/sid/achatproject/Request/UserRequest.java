package org.sid.achatproject.Request;

import lombok.Data;

import java.util.List;

@Data
public class UserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private Boolean admin;
    private Boolean commerciale;
    private Boolean achat;
    private String password;
    private List<DemandeRequest> demandes;
    private List<DossierRequest> dossiers;

}
