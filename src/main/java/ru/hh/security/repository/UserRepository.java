package ru.hh.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hh.security.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findByLogin(String email);
}