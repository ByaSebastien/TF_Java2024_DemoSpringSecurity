package be.bstorm.tf_java2024_demospringsecurity.dal.repositories;

import be.bstorm.tf_java2024_demospringsecurity.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where u.username like :username")
    Optional<User> getUserByUsername(String username);
}
