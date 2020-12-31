package com.insightleave.userservice.services;

import com.insightleave.userservice.dtos.UserGetDto;
import com.insightleave.userservice.dtos.UserPostDto;
import com.insightleave.userservice.dtos.UserPutDto;
import com.insightleave.userservice.entities.UserEntity;
import com.insightleave.userservice.mappers.UserMapper;
import com.insightleave.userservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserGetDto> findAllUsers(){
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserGetDto> userGetDtos = userEntities.stream().map(userMapper::fromEntity).collect(Collectors.toList());
        return userGetDtos;
    }

    public Optional<UserGetDto> findUserByEmail(String email){
        Optional<UserEntity> userEntity = userRepository.findUserEntityByEmail(email);

        if(userEntity.isPresent()){
            return Optional.of(userMapper.fromEntity(userEntity.get()));
        } else {
            return Optional.ofNullable(null);
        }
    }

    public List<UserGetDto> deleteUserByEmail(String email) {
        List<UserEntity> deleteUserEntities = userRepository.deleteUserEntityByEmail(email);
        List<UserGetDto> deleteUserGetDtos = deleteUserEntities.stream().map(userMapper::fromEntity).collect(Collectors.toList());
       if(deleteUserGetDtos.isEmpty()){
           return List.of();
       }else {
           return deleteUserGetDtos;
       }
    }

    public UserGetDto createNewUser(UserPostDto userPostDto) {
        UserEntity userEntity = userRepository.save(userMapper.toEntity(userPostDto));
        UserGetDto newUserGetDto = userMapper.fromEntity(userEntity);
        return newUserGetDto;
    }

    public UserGetDto updateUser(String email, UserPutDto userPutDto) {
        Optional<UserEntity> userEntity = userRepository.findUserEntityByEmail(email);
        if(userEntity.isPresent()){
        userEntity.get().setTitle(userPutDto.getTitle());
        userEntity.get().setFirstname(userPutDto.getFirstname());
        userEntity.get().setLastname(userPutDto.getLastname());
        userEntity.get().setEndDate(userPutDto.getEndDate());
        userEntity.get().setStartDate(userPutDto.getStartDate());
        UserEntity updatedUserEntity = userRepository.save(userEntity.get());
        UserGetDto updatedUserGetDto = userMapper.fromEntity(updatedUserEntity);
        return  updatedUserGetDto;
        } else{
            return new UserGetDto();
        }
    }
}
