package org.fasttrackit.onlineshop;

import org.fasttrackit.onlineshop.domain.User;
import org.fasttrackit.onlineshop.domain.UserRole;
import org.fasttrackit.onlineshop.service.UserService;
import org.fasttrackit.onlineshop.steps.UserTestSteps;
import org.fasttrackit.onlineshop.transfer.user.CreateUserRequest;
import org.hibernate.validator.internal.constraintvalidators.bv.NotNullValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@SpringBootTest
public class UserServiceIntegrationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private UserTestSteps userTestSteps;

    @Test
    public void createUser_whenValidRequest_thenReturnCreatedUser() {
        userTestSteps.createUser();
    }

    public void getUser_whenExistingUser_thenReturnUser() {

        User user = userTestSteps.createUser();

        User userResponse = userService.getUser(user.getId());

        assertThat(userResponse, notNullValue());
        assertThat(userResponse.getId(), greaterThan(0L));
        assertThat(userResponse.getRole(), is(user.getRole()));
        assertThat(userResponse.getFirstName(), is(user.getFirstName()));
        assertThat(userResponse.getLastName(), is(user.getLastName()));
    }




}
