package org.zerock.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.board.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
