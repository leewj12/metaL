package kosmo.user.UserRepository;

import kosmo.user.UserEntity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUseremail(String useremail);
}
