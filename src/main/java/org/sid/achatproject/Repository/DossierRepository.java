package org.sid.achatproject.Repository;

import org.sid.achatproject.entites.Demande;
import org.sid.achatproject.entites.DossierImport;
import org.sid.achatproject.entites.UserEntity;
import org.sid.achatproject.shared.DossierImportDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DossierRepository extends JpaRepository<DossierImport,Long> {

    List<DossierImport> findByUser(UserEntity currentUser);

    DossierImport findByDossierId(String dossierId);

    DossierImport findByNumDossier(String numDossier);

    @Query("Select c from dossiers c where c.numDossier like :x")
    public Page<DossierImport> chercher(@Param("x")String mc, Pageable pageable);

    @Query("Select c from dossiers c where c.fournisseur like :x")
    public Page<DossierImport> chercher1(@Param("x")String mc, Pageable pageable);

   /* @Query("Select c from demandes c where c.demandeId like :x")
    public Page<DossierImport> chercher2(@Param("x")String mc, Pageable pageable);*/
}
