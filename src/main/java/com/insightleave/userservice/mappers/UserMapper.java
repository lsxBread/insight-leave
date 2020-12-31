package com.insightleave.userservice.mappers;

import com.insightleave.userservice.dtos.UserGetDto;
import com.insightleave.userservice.dtos.UserPostDto;
import com.insightleave.userservice.dtos.UserPutDto;
import com.insightleave.userservice.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity toEntity(UserPostDto userPostDto);

    UserEntity toEntity(UserPutDto userPutDto);

    UserGetDto fromEntity(UserEntity userEntity);
}
