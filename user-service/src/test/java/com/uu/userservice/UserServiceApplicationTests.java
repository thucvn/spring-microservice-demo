package com.uu.userservice;

import com.uu.userservice.feignInteface.ProductInterface;
import com.uu.userservice.repository.UserRepository;
import com.uu.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceApplicationTests {

    @MockBean
    private ProductInterface productInterface;

    @MockBean
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    @Test
    public void testOnErrorFeign() {
//        when(productInterface.testError()).thenAnswer(invocationOnMock -> {
//
//        });
    }

}
