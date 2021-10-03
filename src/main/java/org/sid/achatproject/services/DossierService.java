package org.sid.achatproject.services;

import org.sid.achatproject.shared.DemandeDto;
import org.sid.achatproject.shared.DossierImportDto;
import org.sid.achatproject.shared.UserDto;

import java.util.List;

public interface DossierService {

    List<DossierImportDto> getAllDossier(String name);

    DossierImportDto createDossier(DossierImportDto dossierImportDto, String name);

    DossierImportDto getDossier(String demandeId);

    DossierImportDto getNumDossier(String numDossier);

    void deleteDossier(String demandeId);

    DossierImportDto updateDossier(String id, DossierImportDto dossierImportDto);

    UserDto getUserByDossier(String name);
}
