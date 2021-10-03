package org.sid.achatproject.Repository;

import org.sid.achatproject.entites.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByEmail(String email);

    UserEntity findByUserId(String id);
}
