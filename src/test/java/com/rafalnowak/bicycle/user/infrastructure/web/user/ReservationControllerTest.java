package com.rafalnowak.bicycle.user.infrastructure.web.user;

import com.rafalnowak.bicycle.BaseIT;
import com.rafalnowak.bicycle.TestUserFactory;
import com.rafalnowak.bicycle.user.api.ErrorResponse;
import com.rafalnowak.bicycle.user.api.PageUserDto;
import com.rafalnowak.bicycle.user.api.UserDto;
import com.rafalnowak.bicycle.user.domain.User;
import com.rafalnowak.bicycle.user.domain.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReservationControllerTest extends BaseIT {

    @Autowired
    UserService service;

    @Test
    void admin_should_get_information_about_any_user() {
        //given
        User admin = TestUserFactory.createAdmin();
        service.save(admin);
        User user = TestUserFactory.createUser();
        service.save(user);
        String token = getAccessTokenForUser(admin);

        //when
        var response = callHttpMethod(HttpMethod.GET,
                "/api/v1/users/" + service.findByEmail(user.getEmail()).getId(),
                token,
                null,
                UserDto.class);

        //then
        UserDto body = response.getBody();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(body);
        assertEquals(user.getEmail(), body.email());
        assertEquals(user.getName(), body.name());
        assertEquals("######", body.password());
        assertEquals(user.getRole().toString(), body.role().toString());
    }

    @Test
    void admin_should_get_response_code_404_when_user_not_exits_in_db() {
        //given
        User admin = TestUserFactory.createAdmin();
        service.save(admin);
        String token = getAccessTokenForUser(admin);

        //when
        var response = callHttpMethod(HttpMethod.GET,
                "/api/v1/users/10",
                token,
                null,
                ErrorResponse.class);

        //then
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void user_should_not_get_information_about_other_user() {
        //given
        User user1 = TestUserFactory.createUser();
        User user2 = TestUserFactory.createVIP();
        service.save(user1);
        service.save(user2);
        String token = getAccessTokenForUser(user1);

        //when
        var response = callHttpMethod(HttpMethod.GET,
                "/api/v1/users/" + service.findByEmail(user2.getEmail()).getId(),
                token,
                null,
                ErrorResponse.class);

        //then
        assertEquals(response.getStatusCode(), HttpStatus.FORBIDDEN);
    }

    @Test
    void admin_should_get_response_code_conflict_when_user_is_in_db() {
        //given
        User user = TestUserFactory.createUser();
        service.save(user);
        User admin = TestUserFactory.createAdmin();
        service.save(admin);
        String token = getAccessTokenForUser(admin);

        //when
        var response = callHttpMethod(HttpMethod.POST,
                "/api/v1/users",
                token,
                user,
                ErrorResponse.class);

        //then
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }


    @Test
    void admin_should_be_able_to_save_new_user() {
        //given
        User user = TestUserFactory.createUser();
        User admin = TestUserFactory.createAdmin();
        service.save(admin);
        String token = getAccessTokenForUser(admin);

        //when
        var response = callHttpMethod(HttpMethod.POST,
                "/api/v1/users",
                token,
                user,
                UserDto.class);

        //then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        //and
        UserDto body = response.getBody();
        assertNotNull(body);
        assertEquals(body.email(), user.getEmail());
        assertEquals(body.name(), user.getName());
        assertEquals(body.password(), "######");
        assertEquals(body.role().toString(), user.getRole().toString());
    }

    @Test
    void user_should_get_information_about_himself() {
        //given
        User user = TestUserFactory.createUser();
        service.save(user);
        String token = getAccessTokenForUser(user);

        //when
        var response = callHttpMethod(HttpMethod.GET,
                "/api/v1/auth/me",
                token,
                null,
                UserDto.class);

        //then
        UserDto body = response.getBody();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        //and
        assertNotNull(body);
        assertEquals(body.email(), user.getEmail());
        assertEquals(body.name(), user.getName());
        assertEquals(body.password(), "######");
        assertEquals(body.role().toString(), user.getRole().toString());
    }

    @Test
    void admin_should_be_able_to_update_user() {
        //given
        User user = TestUserFactory.createUser();
        userService.save(user);
        User toUpdate = new User(
                user.getId(),
                "email@email.com",
                "newPerson",
                "newpassword",
                user.getRole()
        );
        User admin = TestUserFactory.createAdmin();
        service.save(admin);
        String token = getAccessTokenForUser(admin);

        //when
        var response = callHttpMethod(HttpMethod.PUT,
                "/api/v1/users",
                token,
                toUpdate,
                UserDto.class);

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void admin_should_be_get_response_code_200_when_update_user_not_exits() {
        //given
        User admin = TestUserFactory.createAdmin();
        service.save(admin);
        String token = getAccessTokenForUser(admin);
        User fakeUser = TestUserFactory.createUser();

        //when
        var response = callHttpMethod(HttpMethod.PUT,
                "/api/v1/users",
                token,
                fakeUser,
                ErrorResponse.class);

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void user_should_be_not_able_to_update_user() {
        //given
        User user = TestUserFactory.createUser();
        userService.save(user);
        User userToUpdate = new User(
                user.getId(),
                "otherUser@email.com",
                "Person",
                "password",
                user.getRole()
        );
        String token = getAccessTokenForUser(user);

        //when
        var response = callHttpMethod(HttpMethod.PUT,
                "/api/v1/users",
                token,
                userToUpdate,
                ErrorResponse.class);

        //then
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void admin_should_be_able_to_delete_user() {
        //given
        User user = TestUserFactory.createUser();
        User admin = TestUserFactory.createAdmin();
        service.save(admin);
        String token = getAccessTokenForUser(admin);
        userService.save(user);

        //when
        var response = callHttpMethod(
                HttpMethod.DELETE,
                "/api/v1/users/" + service.findByEmail(user.getEmail()).getId(),
                token,
                null,
                UserDto.class);

        //then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void admin_should_get_response_code_204_when_user_not_exits() {
        //given
        User user = TestUserFactory.createUser();
        User admin = TestUserFactory.createAdmin();
        service.save(admin);
        String token = getAccessTokenForUser(admin);

        //when
        var response = callHttpMethod(
                HttpMethod.DELETE,
                "/api/v1/users/" + user.getId(),
                token,
                null,
                ErrorResponse.class);

        //then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void user_should_not_be_able_to_delete_user() {
        //given
        User firstUser = TestUserFactory.createUser();
        User secondUser = TestUserFactory.createVIP();
        userService.save(firstUser);
        userService.save(secondUser);
        String token = getAccessTokenForUser(firstUser);

        //when
        var response = callHttpMethod(
                HttpMethod.DELETE,
                "/api/v1/users/" + secondUser.getId(),
                token,
                null,
                ErrorResponse.class);

        //then
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void admin_should_get_pageable_list_of_users() {
        //give
        User user = TestUserFactory.createUser();
        User admin = TestUserFactory.createAdmin();
        service.save(admin);
        String token = getAccessTokenForUser(admin);
        userService.save(user);

        //when
        var response = callHttpMethod(
                HttpMethod.GET,
                "/api/v1/users",
                token,
                null,
                PageUserDto.class
        );

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        PageUserDto body = response.getBody();
        //and
        assertNotNull(body);
        assertEquals(2, body.users().size());
        assertEquals(2, body.totalElements());
        assertEquals(1, body.totalPages());
        assertEquals(1, body.currentPage());
        //and users passwords should be hashed
        assertTrue(
                body.users().stream()
                        .allMatch(userDto -> userDto.password().equals("######"))
        );
    }
}