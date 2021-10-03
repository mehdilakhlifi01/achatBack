package org.sid.achatproject.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.sid.achatproject.Repository.DemandeRepository;
import org.sid.achatproject.Repository.DossierRepository;
import org.sid.achatproject.Request.DemandeRequest;
import org.sid.achatproject.Request.DossierRequest;
import org.sid.achatproject.Response.DemandeResponse;
import org.sid.achatproject.Response.DossierResponse;
import org.sid.achatproject.Response.UserResponse;
import org.sid.achatproject.entites.Demande;
import org.sid.achatproject.entites.DossierImport;
import org.sid.achatproject.services.DemandeService;
import org.sid.achatproject.services.DossierService;
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
@RequestMapping("/dossier")
public class DossierControllers {


    DossierService dossierService;
    UserService userService;
    DossierRepository dossierRepository;

    public DossierControllers(DossierService dossierService, UserService userService, DossierRepository dossierRepository) {
        this.dossierService = dossierService;
        this.userService = userService;
        this.dossierRepository = dossierRepository;
    }


    @GetMapping
    public ResponseEntity<List<DossierResponse>> getDossier(Principal principal){

        List<DossierImportDto> dossier=  dossierService.getAllDossier(principal.getName());


        Type listType = new TypeToken<List<DossierResponse>>() {}.getType();
        List<DossierResponse> dossierResponses = new ModelMapper().map(dossier,listType);


        return new ResponseEntity<List<DossierResponse>>(dossierResponses, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<UserResponse> getUserByDossier(Principal principal){

        UserDto user=dossierService.getUserByDossier(principal.getName());

        ModelMapper modelMapper=new ModelMapper();

        UserResponse user1=modelMapper.map(user,UserResponse.class);


        return new ResponseEntity(user1,HttpStatus.CREATED);

    }

    @PostMapping
    public ResponseEntity storeDossier(@RequestBody DossierRequest dossierRequest, Principal principal){

        ModelMapper modelMapper=new ModelMapper();

        DossierImportDto dossier=modelMapper.map(dossierRequest,DossierImportDto.class);
        DossierImportDto createDossier=dossierService.createDossier(dossier,principal.getName());

        DossierResponse newDossier=modelMapper.map(createDossier,DossierResponse.class);

        return new ResponseEntity(newDossier,HttpStatus.CREATED);

    }


    @GetMapping("/{id}")
    public ResponseEntity<DossierResponse> getOneDoc(@PathVariable(name = "id") String dossierId){

        DossierImportDto dossier=dossierService.getDossier(dossierId);

        ModelMapper modelMapper=new ModelMapper();
        DossierResponse dossierResponse=modelMapper.map(dossier,DossierResponse.class);

        return new ResponseEntity<DossierResponse>(dossierResponse,HttpStatus.OK);

    }

    @GetMapping("/d/{idNum}")
    public ResponseEntity<DossierResponse> getNumDoc(@PathVariable(name = "idNum") String numDossier){

        DossierImportDto dossier=dossierService.getNumDossier(numDossier);

        ModelMapper modelMapper=new ModelMapper();
        DossierResponse dossierResponse=modelMapper.map(dossier,DossierResponse.class);

        return new ResponseEntity<DossierResponse>(dossierResponse,HttpStatus.OK);

    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<DossierResponse> updateUser(@PathVariable String id, @RequestBody DossierRequest dossierRequest ) {

        DossierImportDto dossierImportDto=new DossierImportDto();

        BeanUtils.copyProperties(dossierRequest, dossierImportDto);

        DossierImportDto updateDemande = dossierService.updateDossier(id, dossierImportDto);

        DossierResponse demanRes = new DossierResponse();

        BeanUtils.copyProperties(updateDemande, demanRes);

        return new ResponseEntity<DossierResponse>(demanRes,HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDossier(@PathVariable(name = "id") String dossierId){

        dossierService.deleteDossier(dossierId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/chercherId")
    public Page<DossierImport> chercherId(@RequestParam(name="mc",defaultValue="")String mc,
                                          @RequestParam(name="page",defaultValue="0")int page,
                                          @RequestParam(name="size",defaultValue="5")int size){
        return dossierRepository.chercher("%"+mc+"%", PageRequest.of(page, size));

    }

    @GetMapping("/chercherfour")
    public Page<DossierImport> chercherfour(@RequestParam(name="mc",defaultValue="")String mc,
                                        @RequestParam(name="page",defaultValue="0")int page,
                                        @RequestParam(name="size",defaultValue="5")int size){
        return dossierRepository.chercher1("%"+mc+"%", PageRequest.of(page, size));

    }


}
