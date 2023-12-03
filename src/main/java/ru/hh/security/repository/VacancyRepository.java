package ru.hh.security.repository;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import ru.hh.security.model.Vacancy;

@Repository
public class VacancyRepository {

  private final EntityManager entityManager;

  public VacancyRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public List<Vacancy> getVacanciesByTitle(String title) {
    return entityManager.createQuery("FROM Vacancy v WHERE lower(v.title) LIKE ?1", Vacancy.class)
        .setParameter(1, '%' + title.toLowerCase() + '%')
        .getResultList();
  }

  public void create(Vacancy vacancy) {
    entityManager.persist(vacancy);
  }

  public void deleteById(Integer id) {
    var vacancy = entityManager.find(Vacancy.class, id);
    entityManager.remove(vacancy);
  }
}
