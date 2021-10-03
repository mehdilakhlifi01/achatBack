package org.sid.achatproject.services;

import org.sid.achatproject.entites.UserEntity;
import org.sid.achatproject.shared.DemandeDto;
import org.sid.achatproject.shared.DossierImportDto;
import org.sid.achatproject.shared.UserDto;

import java.util.List;

public interface DemandeService {

    List<DemandeDto> getAllDemandes(String name);

    DemandeDto createAddress(DemandeDto addressDto, String name);

    DemandeDto getDemande(String demandeId);

    void deleteDemande(String demandeId);

    DemandeDto updateDemande(String id, DemandeDto userDto);

    UserDto getUserByDemande(String name);

    DemandeDto getNumDemande(String numDossier);

    List<DemandeDto> getAllDemandes();
}
