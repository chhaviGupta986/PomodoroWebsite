package Services;

import Entities.Roles;
import Entities.UserInfo;

import java.util.List;

public interface UserInfoInterface {
    UserInfo saveUser(UserInfo userInfo);
    List<UserInfo>ListUserInfo();

    UserInfo fetchUser(String username);

    UserInfo updateUser(UserInfo userInfo,String username);

    void deleteUser(String username);

    List<Roles> fetchRoles(String username);

}
