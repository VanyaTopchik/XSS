package ru.hh.security.resource;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.hh.security.dto.UserRegistrationDto;
import ru.hh.security.service.UserService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationResource {
  private final UserService userService;

  public UserRegistrationResource(UserService userService) {
    this.userService = userService;
  }

  @ModelAttribute("user")
  public UserRegistrationDto userRegistrationDto() {
    return new UserRegistrationDto();
  }

  @GetMapping
  public String showRegistrationForm() {
    return "registration";
  }

  @PostMapping
  public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
    try {
      userService.loadUserByUsername(registrationDto.getLogin());
      return "redirect:/registration?error";
    } catch (UsernameNotFoundException e) {
      userService.save(registrationDto);
      return "redirect:/registration?success";
    }
  }
}
