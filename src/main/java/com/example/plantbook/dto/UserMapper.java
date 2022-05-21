package com.example.plantbook.dto;


import com.example.plantbook.entity.User;

public class UserMapper {
    private static volatile UserMapper userMapper = null;
    private UserMapper() {}
    public static UserMapper getInstance() {
        if(userMapper == null) {
            userMapper = new UserMapper();
        }
        return userMapper;
    }

    public User convertFromRegisterDTO(RegisterDTO registerDTO){
        return User.builder()
                .address(registerDTO.getAddress())
                .email(registerDTO.getEmail())
                .password(registerDTO.getPassword1())
                .username(registerDTO.getUsername())
                .role("USER")
                .posts(null)
                .profilePicture(null)
                .plants(null)
                .balance(0.0)
                .build();
    }

    public RegisterDTO convertToRegisterDTO(User user){
        return RegisterDTO.builder()
                .address(user.getAddress())
                .email(user.getEmail())
                .password1(user.getPassword())
                .password2(user.getPassword())
                .username(user.getUsername())
                .build();
    }

    public UserDTO convUserToDTO(User user){
        return UserDTO.builder()
                .address(user.getAddress())
                .email(user.getEmail())
                .username(user.getUsername())
                .balance(user.getBalance())
                .build();
    }
}