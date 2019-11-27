package com.conference.api.controller;


import com.conference.api.BookingApiApplication;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookingApiApplication.class)
public class UserControllerTestSuite {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;
    private String tokenValue = null;
    private static final String URL_PREFIX = "http://localhost:8088/";

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .addFilter(springSecurityFilterChain).build();
    }

//TODO: !!!!!!!!!!!!!!!!!!!!!!!!!!!
//    Finish tests
//

    @Before
    public void obtainAccessToken() {
        final Map<String, String> params = new HashMap<String, String>();
        params.put("grant_type", "password");
        params.put("client_id", "testjwtclientid");
        params.put("username", "admin");
        params.put("password", "q1w2e3r4");
        final Response response = RestAssured.given().auth().preemptive().basic("XY7kmzoNzl100", "testjwtresourceid").and().with().params(params).when().post("http://localhost:8080/oauth/token");
        System.out.printf(response.toString());
        tokenValue = response.jsonPath().getString("access_token");
    }

    @Test
    public void testObtainToken(){
        obtainAccessToken();
        System.out.println(tokenValue);
    }

    @Test
    public void whenVerifySwaggerDocIsWorking_thenOK() {
        Response response = RestAssured.get(URL_PREFIX + "/v2/api-docs");
        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatusCode());

        response = RestAssured.given().header("Authorization", "Bearer " + tokenValue).get(URL_PREFIX + "/v2/api-docs");
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenVerifySwaggerUIIsWorking_thenOK() {
        Response response = RestAssured.get(URL_PREFIX + "/swagger-ui.html");
        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatusCode());

        response = RestAssured.given().header("Authorization", "Bearer " + tokenValue).get(URL_PREFIX + "/swagger-ui.html");
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

}
