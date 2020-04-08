package com.khryniewicki.organizer.registration;


import com.khryniewicki.organizer.registration_login_logout.DTO.Roles;
import com.khryniewicki.organizer.registration_login_logout.DTO.UserDTO;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PasswordConstraintValidatorTest {
//
//        private static ValidatorFactory validatorFactory;
//        private static Validator validator;
//
//@BeforeClass
//public static void setUp() {
//
//        validatorFactory = Validation.buildDefaultValidatorFactory();
//        validator = validatorFactory.getValidator();
//        }
//
//        @AfterClass
//        public static void close() {
//        validatorFactory.close();
//        }
//
//@Test
//public void testInvalidPassword() {
//        UserDTO userDTO = new UserDTO();
//        userDTO.setFirstName("memory");
//        userDTO.setSecondName("not found");
//        userDTO.setEmail("info@memorynotfound.com");
//        userDTO.setPassword("password");
//        userDTO.setMatchingPassword("password");
//        userDTO.setRoles(Roles.ADMIN);
//
//        Set<ConstraintViolation<UserDTO>> constraintViolations = validator.validate(userDTO);
//
//        assertFalse(constraintViolations.isEmpty());
//        }

//@Test
//public void testValidPasswords() {
//        UserDTO userDTO = new UserDTO();
//        userDTO.setFirstName("memory");
//        userDTO.setSecondName("not found");
//        userDTO.setEmail("info@memorynotfound.com");
//        userDTO.setPassword("password");
//        userDTO.setMatchingPassword("password");
//        userDTO.setRoles(Roles.ADMIN);
//
//        Set<ConstraintViolation<UserDTO>> constraintViolations = validator.validate(userDTO);
//
//        ass(constraintViolations.size(),0);
//        }
        }
