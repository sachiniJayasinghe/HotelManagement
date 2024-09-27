package com.example.HotelServer.controller;

import com.example.HotelServer.dto.SignUpRequest;
import com.example.HotelServer.dto.UserDto;
import com.example.HotelServer.services.auth.AuthService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {
    private final AuthService  authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignUpRequest signUpRequest){
        try{
            UserDto createdUser = authService.createUser(signUpRequest);
            return  new ResponseEntity<>(createdUser, HttpStatus.OK);
        }catch (EntityExistsException entityExistsException){
            return  new ResponseEntity<>("User Already exists" , HttpStatus.NOT_ACCEPTABLE);
        }catch (Exception e){
            return new ResponseEntity<>(" User not created , come again later ", HttpStatus.BAD_REQUEST);
        }
    }


}
