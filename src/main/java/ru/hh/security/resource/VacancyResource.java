package ru.hh.security.resource;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hh.security.model.Vacancy;
import ru.hh.security.repository.VacancyRepository;

@RestController
@RequestMapping("vacancy")
public class VacancyResource {

  private final VacancyRepository vacancyRepository;

  public VacancyResource(VacancyRepository vacancyRepository) {
    this.vacancyRepository = vacancyRepository;
  }

  @GetMapping
  public List<Vacancy> getVacanciesBy(@RequestParam(required = false) String title) {
    title = title == null ? "" : title;
    return vacancyRepository.getVacanciesByTitle(title);
  }
}
