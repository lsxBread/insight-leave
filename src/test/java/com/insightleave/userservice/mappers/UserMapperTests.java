package com.insightleave.userservice.mappers;

import com.insightleave.userservice.dtos.UserGetDto;
import com.insightleave.userservice.dtos.UserPostDto;
import com.insightleave.userservice.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void ableToConvertUserEntityToUserGetDto() {
        UserEntity entity = UserEntity.builder().email("xxxx@a.com").firstname("a").lastname("b")
                .password("123").startDate(new Date().toString()).endDate("").title("Mr").role("user")
                .build();
        UserGetDto userGetDto = userMapper.fromEntity(entity);
        assertNotNull(userGetDto);
        assertEquals(userGetDto.getEmail(),entity.getEmail());
    }

    @Test
    public void ableToConvertPostDtoToUserEntity(){
        UserPostDto userPostDto = UserPostDto.builder().email("xxxx@a.com").password("124556").build();
        UserEntity userEntity = userMapper.toEntity(userPostDto);
        assertNotNull(userEntity);
        assertEquals(userEntity.getEmail(),userPostDto.getEmail());
        assertEquals(userEntity.getPassword(),userPostDto.getPassword());
        assertNull(userEntity.getEndDate());
        assertNull(userEntity.getStartDate());
        assertNull(userEntity.getTitle());
        assertNull(userEntity.getRole());
        assertNull(userEntity.getFirstname());
        assertNull(userEntity.getLastname());
    }
}
