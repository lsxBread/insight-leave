package com.insightleave.userservice.mappers;

import com.insightleave.userservice.dtos.UserGetDto;
import com.insightleave.userservice.dtos.UserPostDto;
import com.insightleave.userservice.dtos.UserPutDto;
import com.insightleave.userservice.entities.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-31T19:12:51+1100",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 14.0.2 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity toEntity(UserPostDto userPostDto) {
        if ( userPostDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setEmail( userPostDto.getEmail() );
        userEntity.setPassword( userPostDto.getPassword() );

        return userEntity;
    }

    @Override
    public UserEntity toEntity(UserPutDto userPutDto) {
        if ( userPutDto == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setTitle( userPutDto.getTitle() );
        userEntity.setFirstname( userPutDto.getFirstname() );
        userEntity.setLastname( userPutDto.getLastname() );
        userEntity.setStartDate( userPutDto.getStartDate() );
        userEntity.setEndDate( userPutDto.getEndDate() );

        return userEntity;
    }

    @Override
    public UserGetDto fromEntity(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserGetDto userGetDto = new UserGetDto();

        userGetDto.setTitle( userEntity.getTitle() );
        userGetDto.setEmail( userEntity.getEmail() );
        userGetDto.setFirstname( userEntity.getFirstname() );
        userGetDto.setLastname( userEntity.getLastname() );
        userGetDto.setStartDate( userEntity.getStartDate() );
        userGetDto.setEndDate( userEntity.getEndDate() );

        return userGetDto;
    }
}
