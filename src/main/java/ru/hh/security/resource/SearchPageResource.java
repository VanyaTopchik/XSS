package ru.hh.security.resource;

import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.hh.security.repository.VacancyRepository;

@Controller
public class SearchPageResource {

  private final VacancyRepository vacancyRepository;

  public SearchPageResource(VacancyRepository vacancyRepository) {
    this.vacancyRepository = vacancyRepository;
  }

  @GetMapping
  @RequestMapping("search")
  public String getSearchPage(@RequestParam(required = false, defaultValue = "") String title,
                              Model model,
                              HttpServletResponse response) {
    model.addAttribute("title", title);
    model.addAttribute("vacancies", vacancyRepository.getVacanciesByTitle(title));
    response.addCookie(new Cookie("auth", UUID.randomUUID().toString()));
    return "searchPage";
  }

  @PostMapping("delete/{id}")
  @Transactional
  public String getSearchPage(@PathVariable Integer id) {
    vacancyRepository.deleteById(id);
    return "redirect:/";
  }
}
