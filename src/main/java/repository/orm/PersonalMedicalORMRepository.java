package repository.orm;

import domain.PersonalMedical;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.PersonalMedicalRepository;

import java.util.List;

public class PersonalMedicalORMRepository extends HibernateRepository implements PersonalMedicalRepository {

    public PersonalMedicalORMRepository() {

    }

    @Override
    public void save(PersonalMedical entity) {

    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update(Long aLong, PersonalMedical entity) {

    }

    @Override
    public PersonalMedical findOne(Long aLong) {
        PersonalMedical personalMedical = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();

                personalMedical = session.createQuery("from PersonalMedical where id=:idx", PersonalMedical.class)
                        .setParameter("idx", aLong)
                        .getSingleResult();

                transaction.commit();

                return personalMedical;
            } catch (RuntimeException exception) {
                if (transaction != null)
                    transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public List<PersonalMedical> findAll() {
        return null;
    }
}
