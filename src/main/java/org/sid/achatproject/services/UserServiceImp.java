package org.sid.achatproject.services;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.sid.achatproject.entites.Demande;
import org.sid.achatproject.shared.DemandeDto;
import org.sid.achatproject.shared.DossierImportDto;
import org.sid.achatproject.shared.Utils;
import org.sid.achatproject.Repository.UserRepository;
import org.sid.achatproject.entites.UserEntity;
import org.sid.achatproject.shared.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    Utils util;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public UserDto createUser(UserDto userDto) {

        UserEntity checkUser= userRepository.findByEmail(userDto.getEmail());

        if(checkUser != null ) throw new RuntimeException("User Exist Deja ");

       /* UserEntity userEntity=new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);*/


        for(int i=0;i<userDto.getDemandes().size();i++){

            DemandeDto demandes=userDto.getDemandes().get(i);
            demandes.setUser(userDto);
            demandes.setDemandeId(util.generateUserId(6));
            demandes.setNumDossier(util.generateUserId(6));
            demandes.setDate(new Date());
            demandes.setApprobationDirection(false);
            demandes.setExiste(false);
            userDto.getDemandes().set(i,demandes);

        }
        if(userDto.getAchat()){
            for(int i=0;i<userDto.getDossiers().size();i++){

                DossierImportDto dossier=userDto.getDossiers().get(i);
                dossier.setUser(userDto);
                dossier.setNumDossier(util.generateUserId(6));

                dossier.setDossierId(util.generateUserId(6));

                userDto.getDossiers().set(i,dossier);

            }
        }

        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity=modelMapper.map(userDto,UserEntity.class);


        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userEntity.setUserId(util.generateUserId(6));
        userEntity.setCommerciale(true);


        UserEntity newUser= userRepository.save(userEntity);

        /*UserDto UserDt =new UserDto();
        BeanUtils.copyProperties(newUser, UserDt);*/

        UserDto UserDt=modelMapper.map(newUser,UserDto.class);

        return UserDt;
    }

    @Override
    public UserDto getUserById(String id) {

        UserEntity userEntity=	userRepository.findByUserId(id);

        if(userEntity==null) throw new RuntimeException("error");

        UserDto userDto=new UserDto();

        BeanUtils.copyProperties(userEntity, userDto);

        return userDto;
    }

    @Override
    public UserDto updateUser(String id, UserDto userDto) {
        UserEntity userEntity=	userRepository.findByUserId(id);

        if(userEntity==null) throw new RuntimeException("null");

        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userEntity.setAdmin(userDto.getAdmin());
        userEntity.setAchat(userDto.getAchat());
        userEntity.setCommerciale(userDto.getCommerciale());

        UserEntity userEn= userRepository.save(userEntity);

        UserDto userD=new UserDto();

        BeanUtils.copyProperties(userEn, userD);

        return userD;
    }

    @Override
    public void deleteUser(String id) {
        UserEntity userEntity=	userRepository.findByUserId(id);

        if(userEntity==null) throw new RuntimeException("null");

        userRepository.delete(userEntity);

    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> userr=(List<UserEntity>) userRepository.findAll()  ;

        Type listType = new TypeToken<List<UserDto>>() {}.getType();
        List<UserDto> userDtos = new ModelMapper().map(userr,listType);

        return userDtos;
    }

    //récuprer utilisateur aythentifier BD  by email
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity=	userRepository.findByEmail(email);

        if(userEntity==null) throw new UsernameNotFoundException(email);

        //
        return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(), new ArrayList<>() );
    }

    @Override
    public UserDto getUser(String email) {

        UserEntity userEntity=	userRepository.findByEmail(email);

        if(userEntity==null) throw new UsernameNotFoundException(email);

        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(userEntity, userDto);
        return userDto;
    }

}
