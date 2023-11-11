package com.example.test.app.service.user;

import com.example.test.app.exception.DataNotFoundException;
import com.example.test.app.mapper.UserMapper;
import com.example.test.app.model.user.User;
import com.example.test.app.model.user.UserResponseDto;
import com.example.test.app.model.user.UserUpdateDto;
import com.example.test.app.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public UserResponseDto updateUser(UserUpdateDto userUpdateDto, Long userId) {
        User user = getUserExists(userId);
        user = userRepository.save(UserMapper.toModelUpdate(userUpdateDto, user));
        return mapper.map(userRepository.save(user), UserResponseDto.class);
    }

    @Override
    public UserResponseDto getUser(Long userId) {
        return mapper.map(getUserExists(userId), UserResponseDto.class);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();  //Если пользователей нет - возвращается пустой список
        return users.stream().map(user -> (mapper.map(user, UserResponseDto.class))).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long userId) {
        getUserExists(userId);
        userRepository.deleteById(userId);
    }

    private User getUserExists(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("User with id=" + userId + " was not found"));
    }

}
