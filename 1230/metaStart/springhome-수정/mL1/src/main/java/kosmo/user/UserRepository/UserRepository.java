package kosmo.user.UserRepository;

import kosmo.user.UserEntity.UserEntity;  // 올바른 임포트 경로 확인
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUseremail(String useremail);
}
