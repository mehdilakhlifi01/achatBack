package org.sid.achatproject.Repository;

import org.sid.achatproject.entites.Demande;
import org.sid.achatproject.entites.DossierImport;
import org.sid.achatproject.entites.UserEntity;
import org.sid.achatproject.shared.DemandeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
@RepositoryRestResource
public interface DemandeRepository extends JpaRepository<Demande,Long> {
    List<Demande> findByUser(UserEntity currentUser);

    Demande findByDemandeId(String demandeId);

    @RestResource(path = "/productsByKeyword")
    public List<Demande> findByDemandeIdContains(@Param("mc") String mc);

    @RestResource(path = "/clientByKeyword")
    public List<Demande> findByClientContains(@Param("mc") String mc);

    @RestResource(path = "/commercialeByKeyword")
    public List<Demande> findByNomDemandeurContains(@Param("mc") String mc);

    @Query("Select c from demandes c where c.nomDemandeur like :x")
    public Page<Demande> chercher(@Param("x")String mc, Pageable pageable);

    @Query("Select c from demandes c where c.client like :x")
    public Page<Demande> chercher1(@Param("x")String mc, Pageable pageable);

    @Query("Select c from demandes c where c.demandeId like :x")
    public Page<Demande> chercher2(@Param("x")String mc, Pageable pageable);

    Demande findByNumDossier(String numDossier);
}
