package org.sid.achatproject.controllers;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.sid.achatproject.Request.UserRequest;
import org.sid.achatproject.Response.DemandeResponse;
import org.sid.achatproject.Response.UserResponse;
import org.sid.achatproject.services.UserService;
import org.sid.achatproject.shared.DemandeDto;
import org.sid.achatproject.shared.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponse>  getUser(@PathVariable String id) {

      UserDto userDto = userService.getUserById(id);


      ModelMapper modelMapper = new ModelMapper();
      UserResponse userResponse =  modelMapper.map(userDto, UserResponse.class);

    return new ResponseEntity<UserResponse>(userResponse,HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<UserResponse>> getUsers(){

    List<UserDto> user=  userService.getAllUsers();


    Type listType = new TypeToken<List<UserResponse>>() {}.getType();
    List<UserResponse> userResponses = new ModelMapper().map(user,listType);


    return new ResponseEntity<List<UserResponse>>(userResponses,HttpStatus.OK);
  }


  @PostMapping
  public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) throws Exception{

   /* UserDto userDto = new UserDto();
    BeanUtils.copyProperties(userRequest, userDto);*/
    ModelMapper modelMapper = new ModelMapper();

    UserDto userDto = modelMapper.map(userRequest, UserDto.class);;

    UserDto createUser = userService.createUser(userDto);

    /*	UserResponse userRes = new UserResponse();
    BeanUtils.copyProperties(createUser, userRes);*/

    UserResponse userRes=modelMapper.map(createUser, UserResponse.class);

    return new ResponseEntity<UserResponse>(userRes,HttpStatus.CREATED);

  }

  @PatchMapping(path = "/{id}")
  public  ResponseEntity<UserResponse> updateUser(@PathVariable String id, @RequestBody UserRequest userRequest) {

    UserDto userDto = new UserDto();

    BeanUtils.copyProperties(userRequest, userDto);

    UserDto updateUser = userService.updateUser(id, userDto);

    UserResponse userRes = new UserResponse();

    BeanUtils.copyProperties(updateUser, userRes);

    return new ResponseEntity<UserResponse>(userRes,HttpStatus.ACCEPTED);


  }
  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Object> deleteUser(@PathVariable String id) {

    userService.deleteUser(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);

  }


}
