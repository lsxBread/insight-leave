package com.insightleave.userservice.services;

import com.insightleave.userservice.dtos.UserGetDto;
import com.insightleave.userservice.dtos.UserPostDto;
import com.insightleave.userservice.dtos.UserPutDto;
import com.insightleave.userservice.entities.UserEntity;
import com.insightleave.userservice.mappers.UserMapper;
import com.insightleave.userservice.repositories.UserRepository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {
    @Mock
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    private UserService service;

    @BeforeEach
    void init(){
        service = new UserService(repository, mapper);
    }

    @Test
    public void findAllUsers_ShouldReturnUserList() {
        UserEntity mockUser = UserEntity.builder().email("12345@a.com").firstname("a").lastname("b")
                .password("123").startDate(new Date().toString()).endDate("").title("Mr").role("user")
                .build();
        when(repository.findAll()).thenReturn(List.of(mockUser));

        List<UserGetDto> userGetDtos = service.findAllUsers();
        assertEquals(1, userGetDtos.size());
        assertEquals(userGetDtos.get(0).getEmail(), mockUser.getEmail());
    }

    @Test
    public void findUserByEmail_EmailExists_ShouldReturnUserGetDto() {
        UserEntity mockUser = UserEntity.builder().email("12345@a.com").firstname("a").lastname("b")
                .password("123").startDate(new Date().toString()).endDate("").title("Mr").role("user")
                .build();
        when(repository.findUserEntityByEmail(anyString())).thenReturn(Optional.ofNullable(mockUser));
        Optional<UserGetDto> userGetDto = service.findUserByEmail(mockUser.getEmail());

        assertTrue(userGetDto.isPresent());
        assertEquals(mockUser.getEmail(), userGetDto.get().getEmail());
    }

    @Test
    public void findUserByEmail_EmailNotExists_ShouldReturnNull() {
        UserEntity mockUser = UserEntity.builder().email("12345@a.com").firstname("a").lastname("b")
                .password("123").startDate(new Date().toString()).endDate("").title("Mr").role("user")
                .build();
        when(repository.findUserEntityByEmail(anyString())).thenReturn(Optional.ofNullable(null));
        Optional<UserGetDto> userGetDto = service.findUserByEmail("fakeEmail");

        assertFalse(userGetDto.isPresent());
    }

    @Test
    public void deleteUserByEmail_EmailExists_ShouldReturnUserGetDto() {
        UserEntity mockUser = UserEntity.builder().email("12345@a.com").firstname("a").lastname("b")
                .password("123").startDate(new Date().toString()).endDate("").title("Mr").role("user")
                .build();
        when(repository.deleteUserEntityByEmail(anyString())).thenReturn(List.of(mockUser));
        List<UserGetDto> deleteUserGetDtos = service.deleteUserByEmail(mockUser.getEmail());
        assertEquals(1,deleteUserGetDtos.size());
        assertEquals(mockUser.getEmail(), deleteUserGetDtos.get(0).getEmail());
    }

    @Test
    public void deleteUserByEmail_EmailNotExists_ShouldReturnNull() {
        when(repository.deleteUserEntityByEmail(anyString())).thenReturn(List.of());
        List<UserGetDto> deleteUserGetDtos = service.deleteUserByEmail("feakEmail");
        assertTrue(deleteUserGetDtos.isEmpty());
    }

    @Test
    public void createNewUser_ShouldReturnUserGetDto() {
        UserPostDto mockUserPostDto = UserPostDto.builder().email("12345@a.com").password("123").build();
        when(repository.save(any())).thenReturn(mapper.toEntity(mockUserPostDto));
        UserGetDto createdNewUser = service.createNewUser(mockUserPostDto);
        assertNotNull(createdNewUser);
    }

    @Test
    public void UpdateUserDetailByEmail_ShouldReturnUserGetDto(){
        UserEntity mockUserEntity = UserEntity.builder().email("12345@a.com").firstname("a").lastname("b")
                .password("123").startDate(new Date().toString()).endDate("").title("Mr").role("user")
                .build();
        when(repository.findUserEntityByEmail(anyString())).thenReturn(Optional.ofNullable(mockUserEntity));
        assertNotNull(mockUserEntity);

        UserPutDto mockUserPutDto = UserPutDto.builder().firstname("c").lastname("r")
                .startDate(new Date().toString()).endDate("").title("Mr").build();

        UserEntity updatedUserEntity = mapper.toEntity(mockUserPutDto);
        updatedUserEntity.setEmail(mockUserEntity.getEmail());
        updatedUserEntity.setPassword(mockUserEntity.getPassword());
        updatedUserEntity.setRole(mockUserEntity.getRole());

        when(repository.save(any())).thenReturn(updatedUserEntity);

        UserGetDto userGetDto = service.updateUser(mockUserEntity.getEmail(), mockUserPutDto);

        assertNotNull(userGetDto);
        assertEquals(updatedUserEntity.getEmail(), userGetDto.getEmail());
        assertEquals(updatedUserEntity.getFirstname(), mockUserPutDto.getFirstname());
    }
}


