package ru.javawebinar.topjava.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        User authorizedUser = em.getReference(User.class, userId);
        meal.setUser(authorizedUser);

        if (meal.isNew()) {
            em.persist(meal);
            return meal;
        } else if (get(meal.getId(), userId) != null) {
            return em.merge(meal);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        Query query = em.createNamedQuery(Meal.DELETE);
        return query.setParameter(1, userId).setParameter(2, id).executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        List<Meal> mealList = em.createNamedQuery(Meal.GET, Meal.class).setParameter(1, userId).setParameter(2, id).getResultList();
        return DataAccessUtils.singleResult(mealList);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class).setParameter("userId", userId).getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(Meal.GET_BETWEEN, Meal.class)
                .setParameter(1, userId)
                .setParameter(2, startDate)
                .setParameter(3, endDate)
                .getResultList();
    }
}