package com.nseit.Fantasy11.controller;

import com.nseit.Fantasy11.model.FantasyUser;
import com.nseit.Fantasy11.response.APIResponse;
import com.nseit.Fantasy11.service.FantasyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:3001")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private FantasyUserService fantasyUserService;

   @Autowired
    private APIResponse apiResponse;

    @PostMapping("/register")
    public ResponseEntity<APIResponse> register(@RequestBody FantasyUser fantasyUser) {
        FantasyUser registeredUser = fantasyUserService.registerAsCustomer(fantasyUser);
        apiResponse.setStatus(HttpStatus.CREATED.value());
        apiResponse.setData(registeredUser);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@RequestBody FantasyUser fantasyUser) {
        FantasyUser loggedInUser = fantasyUserService.loginAsCustomer(fantasyUser);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(loggedInUser);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
