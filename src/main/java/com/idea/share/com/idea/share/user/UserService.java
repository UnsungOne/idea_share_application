package com.idea.share.com.idea.share.user;

import com.idea.share.com.idea.share.dto.ModelMapper;
import com.idea.share.com.idea.share.exception.UserException;
import com.idea.share.com.idea.share.exception.UserNotFoundException;
import com.idea.share.com.idea.share.exception.WrongHTTPVerb;
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
        if (findUserById(id).isVoted() == true || request.getSession().getAttribute("user") == null) {
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