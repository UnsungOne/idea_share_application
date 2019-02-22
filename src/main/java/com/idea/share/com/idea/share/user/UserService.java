package com.idea.share.com.idea.share.user;

import com.idea.share.com.idea.share.dto.ModelMapper;
import com.idea.share.com.idea.share.exception.UserNotFoundException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkIfUserWithGivenEmailAlreadyExists(String userEmail) {
        if (userRepository.userWithGivenEmail(userEmail) !=null) {
            return true;
        } else {
            return false;
        }
    }

    public void registerUser(UserDTO userDTO) {

        User user = ModelMapper.mapToUserFromUserDTO(userDTO);
        String password = userDTO.getPassword();
        user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        userRepository.save(user);

    }

    public User loginUser(String email, String enteredPassword) {
        User userLoginDTO = userRepository.findUserByEmail(email);
        if (userLoginDTO != null) {
            if (BCrypt.checkpw(enteredPassword, userLoginDTO.getPassword()))
                return userLoginDTO;
        }
        return null;
    }

    public User findUserById(Integer userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("Nie znaleziono użytkownika o id: " + userId));
    }

    public void makeUserAlreadyVoted(Integer userId) {
        userRepository.changeVoteStatusToTrue(userId);
    }

    public Boolean isEligibleToVote(Integer id, HttpServletRequest request) throws Exception {
        return findUserById(id).isVoted() || request.getSession().getAttribute("user") == null;
    }
}