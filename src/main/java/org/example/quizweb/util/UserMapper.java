package org.example.quizweb.util;

import org.example.quizweb.dto.UserDto;
import org.example.quizweb.model.User;

public class UserMapper {
    public static UserDto userToDto(User user) {
        UserDto dto = new UserDto();

        dto.setUsername(user.getUsername());
        return dto;
    }
    public static User DtoToUser(UserDto userDto) {
        User user = new User();

        user.setUsername(userDto.getUsername());
        return user;
    }
}
