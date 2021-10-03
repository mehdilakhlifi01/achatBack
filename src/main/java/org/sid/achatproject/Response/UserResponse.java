package org.sid.achatproject.Response;

import lombok.Data;
import org.sid.achatproject.Request.DossierRequest;

import java.util.List;

@Data
public class UserResponse {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean admin;
    private Boolean achat;
    private Boolean commerciale;
    private List<DemandeResponse> demandes;
    private List<DossierRequest> dossiers;
}
