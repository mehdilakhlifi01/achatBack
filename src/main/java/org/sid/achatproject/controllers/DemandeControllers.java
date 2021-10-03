package org.sid.achatproject.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.sid.achatproject.Repository.DemandeRepository;
import org.sid.achatproject.Request.DemandeRequest;
import org.sid.achatproject.Response.DemandeResponse;
import org.sid.achatproject.Response.DossierResponse;
import org.sid.achatproject.Response.UserResponse;
import org.sid.achatproject.entites.Demande;
import org.sid.achatproject.entites.UserEntity;
import org.sid.achatproject.services.DemandeService;
import org.sid.achatproject.services.UserService;
import org.sid.achatproject.shared.DemandeDto;
import org.sid.achatproject.shared.DossierImportDto;
import org.sid.achatproject.shared.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/demandes")
public class DemandeControllers {

    @Autowired
    DemandeService demandeService;
    @Autowired
    UserService userService;
    @Autowired
    DemandeRepository demandeRepository;

    @GetMapping("/all")
    public ResponseEntity<List<DemandeResponse>> getDemandess(){

        List<DemandeDto> demande=  demandeService.getAllDemandes();


        Type listType = new TypeToken<List<DemandeResponse>>() {}.getType();
        List<DemandeResponse> demandeResponses = new ModelMapper().map(demande,listType);


        return new ResponseEntity<List<DemandeResponse>>(demandeResponses,HttpStatus.OK);
    }

     @GetMapping
     public ResponseEntity<List<DemandeResponse>> getDemandes(Principal principal){

         List<DemandeDto> demande=  demandeService.getAllDemandes(principal.getName());


         Type listType = new TypeToken<List<DemandeResponse>>() {}.getType();
         List<DemandeResponse> demandeResponses = new ModelMapper().map(demande,listType);


         return new ResponseEntity<List<DemandeResponse>>(demandeResponses,HttpStatus.OK);
     }

     @GetMapping("/user")
     public ResponseEntity<UserResponse> getUserByDemande(Principal principal){

         UserDto user=demandeService.getUserByDemande(principal.getName());

         ModelMapper modelMapper=new ModelMapper();

         UserResponse user1=modelMapper.map(user,UserResponse.class);


         return new ResponseEntity(user1,HttpStatus.CREATED);

     }
    @GetMapping("/d/{idNum}")
    public ResponseEntity<DemandeResponse> getNumDoc(@PathVariable(name = "idNum") String numDossier){

        DemandeDto demande=demandeService.getNumDemande(numDossier);

        ModelMapper modelMapper=new ModelMapper();
        DemandeResponse dossierResponse=modelMapper.map(demande,DemandeResponse.class);

        return new ResponseEntity<DemandeResponse>(dossierResponse,HttpStatus.OK);

    }


    @PostMapping
    public ResponseEntity storeDemande(@RequestBody DemandeRequest demandeRequest, Principal principal){

        ModelMapper modelMapper=new ModelMapper();

        DemandeDto addressDto=modelMapper.map(demandeRequest,DemandeDto.class);
        DemandeDto createAddress=demandeService.createAddress(addressDto,principal.getName());

        DemandeResponse newAddress=modelMapper.map(createAddress,DemandeResponse.class);

        return new ResponseEntity(newAddress,HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<DemandeResponse> getOneDemande(@PathVariable(name = "id") String demandeId){

         DemandeDto demandeDto=demandeService.getDemande(demandeId);

         ModelMapper modelMapper=new ModelMapper();
         DemandeResponse demandeResponse=modelMapper.map(demandeDto,DemandeResponse.class);

         return new ResponseEntity<DemandeResponse>(demandeResponse,HttpStatus.OK);

    }

  /*  @PutMapping("/{id}")
    public ResponseEntity<DemandeResponse>  update(@PathVariable String id, @RequestBody Demande demande) {
        DemandeDto demandeDto = new DemandeDto();

        BeanUtils.copyProperties(demande,demandeDto);

        DemandeDto updateDemande = demandeService.updateDemande(id, demandeDto);

        DemandeResponse demanRes = new DemandeResponse();

        BeanUtils.copyProperties(updateDemande, demanRes);

        return new ResponseEntity<DemandeResponse>(demanRes,HttpStatus.ACCEPTED);

    }*/

    @PatchMapping(path = "/{id}")
    public ResponseEntity<DemandeResponse> updateUser(@PathVariable String id, @RequestBody DemandeRequest demandeRequest) {

        DemandeDto demandeDto=new DemandeDto();

        BeanUtils.copyProperties(demandeRequest, demandeDto);

        DemandeDto updateDemande = demandeService.updateDemande(id, demandeDto);

        DemandeResponse demanRes = new DemandeResponse();

        BeanUtils.copyProperties(updateDemande, demanRes);

        return new ResponseEntity<DemandeResponse>(demanRes,HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDenamde(@PathVariable(name = "id") String demandeId){

         demandeService.deleteDemande(demandeId);

         return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/chercherCommerciale")
    public Page<Demande> chercherContact(@RequestParam(name="mc",defaultValue="")String mc,
                                         @RequestParam(name="page",defaultValue="0")int page,
                                         @RequestParam(name="size",defaultValue="5")int size){
        return demandeRepository.chercher("%"+mc+"%", PageRequest.of(page, size));

    }

    @GetMapping("/chercherClient")
    public Page<Demande> chercherClient(@RequestParam(name="mc",defaultValue="")String mc,
                                         @RequestParam(name="page",defaultValue="0")int page,
                                         @RequestParam(name="size",defaultValue="5")int size){
        return demandeRepository.chercher1("%"+mc+"%", PageRequest.of(page, size));

    }

    @GetMapping("/chercherId")
    public Page<Demande> chercherId(@RequestParam(name="mc",defaultValue="")String mc,
                                         @RequestParam(name="page",defaultValue="0")int page,
                                         @RequestParam(name="size",defaultValue="5")int size){
        return demandeRepository.chercher2("%"+mc+"%", PageRequest.of(page, size));

    }




}
