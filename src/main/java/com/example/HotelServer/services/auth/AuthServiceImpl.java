package com.example.HotelServer.services.auth;

import com.example.HotelServer.dto.SignUpRequest;
import com.example.HotelServer.dto.UserDto;
import com.example.HotelServer.entity.User;
import com.example.HotelServer.enums.UserRole;
import com.example.HotelServer.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    @PostConstruct
    public void createAnAdminAccount(){
        Optional<User> adminAccount= userRepository.findByUserRole(UserRole.ADMIN);
        if(adminAccount.isEmpty()){
            User user= new User();
            user.setEmail("admin@test.com");
            user.setName("Admin");
            user.setUserRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
            System.out.println("Admin account created successfully");
        }else {
            System.out.println("Admin account already exist");
        }
    }
    public UserDto createUser(SignUpRequest signUpRequest){
        if (userRepository.findFirstByEmail(signUpRequest.getEmail()).isPresent()){
            throw new EntityExistsException("User Already Present With Email" + signUpRequest.getEmail());

        }
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setName(signUpRequest.getName());
        user.setUserRole(UserRole.CUSTOMER);
        user.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        User createUser = userRepository.save(user);
        return createUser.getUserDto();
    }

}
