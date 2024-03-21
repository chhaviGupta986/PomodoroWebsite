package Services;

import Entities.Roles;
import Entities.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserInfoService implements UserInfoInterface{


    private final UserRepository userRepository;
    @Autowired
    UserInfoService(UserRepository infoInterface){
            this.userRepository = infoInterface;
    }

    @Override
    public UserInfo saveUser(UserInfo userInfo) {
        return userRepository.save(userInfo);
    }

    @Override
    public List<UserInfo> ListUserInfo() {
        return (List<UserInfo>)userRepository.findAll();
    }

    @Override
    public UserInfo fetchUser(String username) {
        Optional<UserInfo>userInfo = userRepository.findById(username);
        return userInfo.orElse(null);
    }

    @Override
    public UserInfo updateUser(UserInfo userInfo, String username) {
        return null;
    }

    @Override
    public void deleteUser(String username) {

        userRepository.deleteById(username);

    }

    @Override
    public List<Roles> fetchRoles(String username) {
        return userRepository.findAllByUsername(username);
    }
}
