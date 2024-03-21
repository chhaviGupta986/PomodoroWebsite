package repositories;

import Entities.Roles;
import Entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, String> {

    @Query("select t from UserInfo t join Roles r where t.username = :username")
     List<Roles> findAllByUsername(@Param("username")String username);

    Optional<UserInfo> findByEmail(String email);

}
