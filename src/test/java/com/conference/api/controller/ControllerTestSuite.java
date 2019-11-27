//package com.conference.api.controller;
//
//
//import com.conference.api.BookingApiApplication;
//import config.OAuthHelper;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.RequestPostProcessor;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static io.restassured.authentication.FormAuthConfig.springSecurity;
//import static java.util.Collections.get;
//import static org.junit.Assert.assertEquals;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static sun.misc.Version.print;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = BookingApiApplication.class)
//public class ControllerTestSuite {
//
//    @Autowired
//    private WebApplicationContext   webapp;
//
//    private MockMvc                 mvc;
//
//    @Before
//    public void before() {
//        mvc = MockMvcBuilders.webAppContextSetup(webapp)
//                .apply(springSecurity())
//                .alwaysDo(print())
//                .build();
//    }
//
//    @Autowired
//    private OAuthHelper helper;
//
//    @Test
//    public void testHelloWithRole() throws Exception {
//        RequestPostProcessor bearerToken = helper.bearerToken("myclientwith");
//        mvc.perform(get("/hello").with(bearerToken)).andExpect(status().isOk());
//    }
//
//    @Test
//    public void testHelloWithoutRole() throws Exception {
//        RequestPostProcessor bearerToken = helper.bearerToken("myclientwithout");
//        mvc.perform(get("/hello").with(bearerToken)).andExpect(status().isForbidden());
//    }
//}
//
//}
