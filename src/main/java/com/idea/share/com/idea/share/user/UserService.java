package com.idea.share.com.idea.share.user;

import com.idea.share.com.idea.share.dto.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
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

    public void voteUpOrDown(Integer id) {
        userRepository.changeVoteStatusToTrue(id);
    }

    public boolean isEligibleToVote(Integer id) throws Exception {
        if (findUserById(id).isVoted() == true) {
            System.out.println("Użytkownik nie może głosować");
            return true;
        } else {
            System.out.println("Użytkownik może głosować");
            return false;
        }
    }
}