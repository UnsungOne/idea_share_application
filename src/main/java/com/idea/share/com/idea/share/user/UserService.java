package com.idea.share.com.idea.share.user;

import com.idea.share.com.idea.share.dto.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(UserDTO userDTO) {
        userRepository.save(ModelMapper.mapToUser(userDTO));

    }

    public User findUserByEmailAndPassword(String email, String password) {
        return userRepository.findUserByEmailAndPassword(email, password);
    }

    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User findUserById(int userId) throws Exception {
        return userRepository.findById(userId).orElseThrow(() -> new Exception("Nie znaleziono produktu o id: " + userId));
    }

}