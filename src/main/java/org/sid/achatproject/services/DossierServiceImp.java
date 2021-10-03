package org.sid.achatproject.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.sid.achatproject.Repository.DemandeRepository;
import org.sid.achatproject.Repository.DossierRepository;
import org.sid.achatproject.Repository.UserRepository;
import org.sid.achatproject.entites.Demande;
import org.sid.achatproject.entites.DossierImport;
import org.sid.achatproject.entites.UserEntity;
import org.sid.achatproject.shared.DemandeDto;
import org.sid.achatproject.shared.DossierImportDto;
import org.sid.achatproject.shared.UserDto;
import org.sid.achatproject.shared.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

@Service
public class DossierServiceImp implements DossierService{


    @Autowired
    DossierRepository dossierRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils util;



    @Override
    public List<DossierImportDto> getAllDossier(String name) {

        UserEntity currentUser= userRepository.findByEmail(name);

        List<DossierImport> dossier=currentUser.getAdmin() == true ? (List<DossierImport>) dossierRepository.findAll() : (List<DossierImport>) dossierRepository.findByUser(currentUser) ;

        Type listType = new TypeToken<List<DossierImportDto>>() {}.getType();
        List<DossierImportDto> dosiierDto = new ModelMapper().map(dossier,listType);

        return dosiierDto;
    }

    @Override
    public DossierImportDto createDossier(DossierImportDto dossier, String email) {

        UserEntity currentUser=userRepository.findByEmail(email);
        ModelMapper modelMapper=new ModelMapper();
        UserDto userDto=modelMapper.map(currentUser,UserDto.class);
       // dossier.setId(1);
        dossier.setDossierId(util.generateUserId(6));
        dossier.setUser(userDto);
        dossier.setExiste(true);

        DossierImport demande1=modelMapper.map(dossier,DossierImport.class);
        DossierImport newAddress=dossierRepository.save(demande1);
        DossierImportDto demandeDto=modelMapper.map(newAddress,DossierImportDto.class);
        return demandeDto;
    }

    @Override
    public DossierImportDto getDossier(String dossierId) {

        DossierImport dossier= dossierRepository.findByDossierId(dossierId);

        ModelMapper modelMapper=new ModelMapper();
        DossierImportDto ddossier=modelMapper.map(dossier,DossierImportDto.class);

        return ddossier;
    }
    @Override
    public DossierImportDto getNumDossier(String numDossier) {

        DossierImport dossier= dossierRepository.findByNumDossier(numDossier);

        ModelMapper modelMapper=new ModelMapper();
        DossierImportDto ddossier=modelMapper.map(dossier,DossierImportDto.class);

        return ddossier;
    }

    @Override
    public void deleteDossier(String dossierId) {
        DossierImport dossier=dossierRepository.findByDossierId(dossierId);

        if(dossier==null) throw new RuntimeException("dossier not  found ");

        dossierRepository.delete(dossier);

    }

    @Override
    public DossierImportDto updateDossier(String id, DossierImportDto dossierImportDto) {

        DossierImport  dossier  =	dossierRepository.findByDossierId(id);

        if(dossier==null) throw new UsernameNotFoundException(id);


        dossier.setId(dossier.getId());
        dossier.setNumDossier(dossierImportDto.getNumDossier());
        dossier.setDeclarant(dossierImportDto.getDeclarant());
        dossier.setFournisseur(dossierImportDto.getFournisseur());
        dossier.setCauseReatard(dossierImportDto.getCauseReatard());
        dossier.setCauseRetardSortie(dossierImportDto.getCauseRetardSortie());
        dossier.setDteReceptArc(dossierImportDto.getDteReceptArc());
        dossier.setDelaiDisponibilite(dossierImportDto.getDelaiDisponibilite());
        dossier.setType(dossierImportDto.getType());
        dossier.setDateMiseDisposition(dossierImportDto.getDateMiseDisposition());
        dossier.setDateOrdreEnlevement(dossierImportDto.getDateOrdreEnlevement());
        dossier.setTransporteur(dossierImportDto.getTransporteur());
        dossier.setDateEnlevement(dossierImportDto.getDateEnlevement());
        dossier.setDateEmp(dossierImportDto.getDateEmp());
        dossier.setDateArrivePrevi(dossierImportDto.getDateArrivePrevi());
        dossier.setDateArrive(dossierImportDto.getDateArrive());
        dossier.setDteTransmisiontDoc(dossierImportDto.getDteTransmisiontDoc());
        dossier.setDateLancement(dossierImportDto.getDateLancement());
        dossier.setNumDeclaration(dossierImportDto.getNumDeclaration());
        dossier.setNumOrder(dossierImportDto.getNumOrder());
        dossier.setDateBonSortie(dossierImportDto.getDateBonSortie());
        dossier.setDateLivraison(dossierImportDto.getDateLivraison());
        dossier.setReception(dossierImportDto.getReception());


        DossierImport userEn= dossierRepository.save(dossier);

        DossierImportDto userD=new DossierImportDto();

        BeanUtils.copyProperties(dossier, userD);

        return userD;
    }


    @Override
    public UserDto getUserByDossier(String name) {
        UserEntity currentUser=userRepository.findByEmail(name);
        ModelMapper modelMapper=new ModelMapper();
        UserDto userDto=modelMapper.map(currentUser,UserDto.class);

        return userDto;
    }
}
