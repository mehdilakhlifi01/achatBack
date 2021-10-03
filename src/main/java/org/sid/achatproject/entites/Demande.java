package org.sid.achatproject.entites;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.sid.achatproject.shared.UserDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "demandes")
@Data @AllArgsConstructor @NoArgsConstructor
public class Demande implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = true)
    private String demandeId;
    @Column(nullable = true)
    private String numDossier;
    @Column(nullable = true,length = 50)
    private String article ;
    @Column(nullable = true,length = 50)
    private String unite ;
    @Column(nullable = true,length = 50)
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(nullable = true,length = 50)
    private String qteDemander ;
    @Column(nullable = true,length = 50)
    private String client ;
    @Column(nullable = true,length = 50)
    private String prixAchat ;
    @Column(nullable = false,length = 50)
    private String prixVente ;
    @Column(nullable = true,length = 50)
    private String observation ;
    @Column(nullable = true,length = 50)
    private String nomDemandeur ;
    @Column(nullable = true,length = 50)
    private Boolean approbationDirection=false ;
    @Column(nullable = true,length = 50)
    private Boolean existe=false ;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "users_id")
    private UserEntity user;
}
