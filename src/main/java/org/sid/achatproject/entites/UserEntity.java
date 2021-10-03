package org.sid.achatproject.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "users")
@Data @AllArgsConstructor @NoArgsConstructor
public class UserEntity implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false,length = 50)
    private String firstName;
    @Column(nullable = false,length = 50)
    private String lastName;
    @Column(nullable = false,length = 120,unique = true)
    private String email;
    @Column(nullable = true)
    private Boolean admin=false;
    @Column(nullable = true)
    private Boolean commerciale=true;
    @Column(nullable = true)
    private Boolean achat=false;
    @Column(nullable = false)
    private String encryptedPassword;
    @Column(nullable = true)
    private String emailVerificationToken;
    @Column(nullable = false)
    private Boolean emailVerificationStatus=false;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Demande> demandes;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @Column(nullable = true)
    private List<DossierImport> dossiers;

}
