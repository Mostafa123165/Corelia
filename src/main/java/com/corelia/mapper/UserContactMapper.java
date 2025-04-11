package com.corelia.mapper;

import com.corelia.dto.UserContactDto;
import com.corelia.model.UserContact;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserContactMapper {

    @Mappings({
            @Mapping(target ="userId" ,source = "t.user.id")
    })
    UserContactDto map(UserContact t);

    @Mappings({
            @Mapping(target = "user.id",source = "userId")
    })
    UserContact unMap(UserContactDto dto);

    List<UserContactDto> map(List<UserContact> t);

    Set<UserContactDto> map (Set<UserContact> t);

    List<UserContact> unMap(List<UserContactDto> dto);

    Set<UserContact> unMap(Set<UserContactDto> dto);
}
