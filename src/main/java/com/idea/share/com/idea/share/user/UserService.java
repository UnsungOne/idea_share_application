package com.idea.share.com.idea.share.user;

import com.idea.share.com.idea.share.dto.ModelMapper;
import com.idea.share.com.idea.share.idea.Idea;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

    public List<User> findAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User findUserById(int userId) throws Exception {
        return userRepository.findById(userId).orElseThrow(() -> new Exception("Nie znaleziono produktu o id: " + userId));
    }

    public void makeUserAlreadyVoted(Integer userId) {
        userRepository.changeVoteStatusToTrue(userId);
    }

    public boolean isEligibleToVote(Integer id, HttpServletRequest request) throws Exception {
        if (findUserById(id).isVoted() || request.getSession().getAttribute("user") == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean determineIfUserIsAuthorOfAGivenIdea(int ideaId, List<Idea> ideasCreatedByUser) {

        if (ideasCreatedByUser == null) {
            return false;
        }
        List<Integer> ideasIdCreatedByUser = ideasCreatedByUser
                .stream()
                .map(Idea::getId)
                .collect(Collectors.toList());

        if (ideasIdCreatedByUser.contains(ideaId)) {
            return true;
        } else {
            return false;
        }
    }
}