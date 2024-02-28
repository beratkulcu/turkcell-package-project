package com.turkcell.Mapper;

import com.turkcell.dto.UserDTO;
import com.turkcell.entity.User;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    public UserDTO userDtoChange (User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setSurname(user.getSurname());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setEmail(user.getEmail());
        userDTO.setBalance(user.getBalance());
        userDTO.setPackages(user.getPackages());
        return userDTO;
    }
}
