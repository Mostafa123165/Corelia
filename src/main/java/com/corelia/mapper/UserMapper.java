package com.corelia.mapper;

import com.corelia.dto.UserDto;
import com.corelia.model.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring",uses = {UserContactMapper.class})
public interface UserMapper {

    UserDto map(User t);

    User unMap(UserDto dto);

    List<UserDto> map(List<User> t);

    Set<UserDto> map (Set<User> t);

    List<User> unMap(List<UserDto> dto);

    Set<User> unMap(Set<UserDto> dto);
}
