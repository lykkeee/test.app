package com.example.test.app.service.user;

import com.example.test.app.exception.DataNotFoundException;
import com.example.test.app.mapper.UserMapper;
import com.example.test.app.model.user.ApplicationUser;
import com.example.test.app.model.user.UserResponseDto;
import com.example.test.app.model.user.UserUpdateDto;
import com.example.test.app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException("User with username" + username + " was not found"));
    }

    @Override
    public UserResponseDto updateUser(UserUpdateDto userUpdateDto, Long userId) {
        ApplicationUser applicationUser = getUserExists(userId);
        applicationUser = userRepository.save(UserMapper.toModelUpdate(userUpdateDto, applicationUser));
        return mapper.map(userRepository.save(applicationUser), UserResponseDto.class);
    }

    @Override
    public UserResponseDto getUser(Long userId) {
        return mapper.map(getUserExists(userId), UserResponseDto.class);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<ApplicationUser> applicationUsers = userRepository.findAll();  //Если пользователей нет - возвращается пустой список
        return applicationUsers.stream().map(user -> (mapper.map(user, UserResponseDto.class))).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long userId) {
        getUserExists(userId);
        userRepository.deleteById(userId);
    }

    private ApplicationUser getUserExists(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User with id=" + userId + " was not found"));
    }

}
