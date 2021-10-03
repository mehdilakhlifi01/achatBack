package org.sid.achatproject.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.sid.achatproject.Repository.DemandeRepository;
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
public class DemandeServiceImp implements DemandeService {

    @Autowired
    DemandeRepository demandeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils util;

    @Override
    public List<DemandeDto> getAllDemandes(String email) {

      UserEntity currentUser= userRepository.findByEmail(email);

        List<Demande> demande=currentUser.getAdmin() == true ? (List<Demande>) demandeRepository.findAll() : (List<Demande>) demandeRepository.findByUser(currentUser) ;

        Type listType = new TypeToken<List<DemandeDto>>() {}.getType();
        List<DemandeDto> demandeDtos = new ModelMapper().map(demande,listType);

        return demandeDtos;
    }

    @Override
    public DemandeDto createAddress(DemandeDto demande, String email) {
        UserEntity currentUser=userRepository.findByEmail(email);
        ModelMapper modelMapper=new ModelMapper();
        UserDto userDto=modelMapper.map(currentUser,UserDto.class);

        demande.setDemandeId(util.generateUserId(6));
        demande.setNumDossier(util.generateUserId(6));
        demande.setDate(new Date());
        demande.setApprobationDirection(false);
        demande.setUser(userDto);

        Demande demande1=modelMapper.map(demande,Demande.class);
        Demande newAddress=demandeRepository.save(demande1);
        DemandeDto demandeDto=modelMapper.map(newAddress,DemandeDto.class);
        return demandeDto;
    }


    @Override
    public DemandeDto getDemande(String demandeId) {

         Demande demande=demandeRepository.findByDemandeId(demandeId);

         ModelMapper modelMapper=new ModelMapper();
         DemandeDto demandeDto=modelMapper.map(demande,DemandeDto.class);

        return demandeDto;
    }

    @Override
    public void deleteDemande(String demandeId) {

        Demande demande=demandeRepository.findByDemandeId(demandeId);

        if(demande==null) throw new RuntimeException("demande not  found ");

        demandeRepository.delete(demande);

    }



   /* @Override
    public DemandeDto updateDemande(String id, DemandeDto userDto) {
        Demande demande=demandeRepository.findByDemandeId(id);

        if(demande==null) throw new UsernameNotFoundException(id);

        demande.setArticle(userDto.getArticle());
        demande.setClient(userDto.getClient());



        Demande  userEn= demandeRepository.save(demande);

        DemandeDto  demandeDto=new DemandeDto();

        BeanUtils.copyProperties(userEn, demandeDto);

        return demandeDto;
    }*/

    @Override
    public UserDto getUserByDemande(String email) {
        UserEntity currentUser=userRepository.findByEmail(email);
        ModelMapper modelMapper=new ModelMapper();
        UserDto userDto=modelMapper.map(currentUser,UserDto.class);

        return userDto;


    }

    @Override
    public DemandeDto getNumDemande(String numDossier) {

        Demande demandeDto= demandeRepository.findByNumDossier(numDossier);

        ModelMapper modelMapper=new ModelMapper();
        DemandeDto ddossier=modelMapper.map(demandeDto,DemandeDto.class);

        return ddossier;
    }

    @Override
    public List<DemandeDto> getAllDemandes() {

        List<Demande> demande=(List<Demande>) demandeRepository.findAll()  ;

        Type listType = new TypeToken<List<DemandeDto>>() {}.getType();
        List<DemandeDto> demandeDtos = new ModelMapper().map(demande,listType);

        return demandeDtos;
    }

    @Override
   public DemandeDto updateDemande(String id,DemandeDto demandeDto){

        Demande  demande  =	demandeRepository.findByDemandeId(id);

        if(demande==null) throw new UsernameNotFoundException(id);


        demande.setId(demande.getId());
        demande.setArticle(demandeDto.getArticle());
        demande.setUnite(demandeDto.getUnite());
        demande.setQteDemander(demandeDto.getQteDemander());
        demande.setClient(demandeDto.getClient());
        demande.setPrixAchat(demandeDto.getPrixAchat());
        demande.setExiste(demandeDto.getExiste());
        demande.setPrixVente(demandeDto.getPrixVente());
        demande.setObservation(demandeDto.getObservation());
        demande.setNomDemandeur(demandeDto.getNomDemandeur());
        demande.setApprobationDirection(demandeDto.getApprobationDirection());

        Demande userEn= demandeRepository.save(demande);

        DemandeDto userD=new DemandeDto();

        BeanUtils.copyProperties(demande, userD);

        return userD;

    }
}
