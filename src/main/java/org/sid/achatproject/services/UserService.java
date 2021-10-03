package org.sid.achatproject.services;

import org.sid.achatproject.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(String id);

    UserDto getUser(String email);

    UserDto updateUser(String id, UserDto userDto);

    void deleteUser(String id);

    List<UserDto> getAllUsers();
}
