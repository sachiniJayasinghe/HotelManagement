package com.example.HotelServer.services.auth;

import com.example.HotelServer.dto.SignUpRequest;
import com.example.HotelServer.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    UserDto createUser(SignUpRequest signUpRequest);
}
