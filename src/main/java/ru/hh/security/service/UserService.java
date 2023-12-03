package ru.hh.security.service;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.hh.security.dto.UserRegistrationDto;
import ru.hh.security.model.User;
import ru.hh.security.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository) {
    super();
    this.userRepository = userRepository;
  }

  public void save(UserRegistrationDto registrationDto) {
    User user = new User(registrationDto.getLogin(), passwordEncoder.encode(registrationDto.getPassword()));
    userRepository.save(user);
  }

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = userRepository.findByLogin(username);
    if (user == null) {
      throw new UsernameNotFoundException("Invalid username or password.");
    }
    return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), Collections.EMPTY_LIST);
  }

}