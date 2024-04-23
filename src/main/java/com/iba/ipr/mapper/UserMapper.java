package com.iba.ipr.mapper;

import com.iba.ipr.dto.request.UserRequestDto;
import com.iba.ipr.dto.response.UserDto;
import com.iba.ipr.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto entityToDto(User user);

    User dtoToEntity(UserRequestDto userDto);
}
