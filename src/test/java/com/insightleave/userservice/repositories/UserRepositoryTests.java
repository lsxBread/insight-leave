package com.insightleave.userservice.repositories;

import com.insightleave.userservice.entities.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@DataJpaTest
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    public void insertMock() {
        UserEntity entity = UserEntity.builder().email("abc@a.com").firstname("a").lastname("b")
                .password("123").startDate(new Date().toString()).endDate("").title("Mr").role("user")
                .build();
        entityManager.persist(entity);
        entityManager.flush();
    }

    @Test
    public void findUserByEmail_ShouldNotReturnNull() {
        Optional<UserEntity> userEntityByEmail = userRepository.findUserEntityByEmail("abc@a.com");
        if(userEntityByEmail.isPresent()) {
            log.info(String.format("%s:%s", "found user", userEntityByEmail.get().getFirstname()));
        } else {
            log.error("user not found");
        }
        assertTrue(userEntityByEmail.isPresent());
    }

    @Test
    public void updateUserName_ShouldReturnNewName() {
        Optional<UserEntity> userEntity = userRepository.findUserEntityByEmail("abc@a.com");
        assertTrue(userEntity.isPresent());
        UserEntity user = userEntity.get();
        user.setFirstname("eric");
        UserEntity save = userRepository.save(user);
        assertEquals(save.getFirstname(), "eric");
    }

    @Test
    public void deleteUserByEmail_ShouldReturnNull() {
        List<UserEntity> userEntities = userRepository.deleteUserEntityByEmail("abc@a.com");
        assertEquals(userEntities.get(0).getEmail(),"abc@a.com");
        Optional<UserEntity> userEntity = userRepository.findUserEntityByEmail("abc@a.com");
        assertFalse(userEntity.isPresent());
    }

    @Test
    public void saveUser_ShouldNotReturnNull() {
        UserEntity entity = UserEntity.builder().email("xxxx@a.com").firstname("a").lastname("b")
                .password("123").startDate(new Date().toString()).endDate("").title("Mr").role("user")
                .build();
        userRepository.save(entity);
        Optional<UserEntity> userEntity = userRepository.findUserEntityByEmail("xxxx@a.com");
        assertTrue(userEntity.isPresent());
    }
}
