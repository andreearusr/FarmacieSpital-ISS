package repository.orm;

import domain.Farmacist;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.FarmacistRepository;

import java.util.List;

public class FarmacistORMRepository extends HibernateRepository implements FarmacistRepository {

    public FarmacistORMRepository() {

    }

    @Override
    public void save(Farmacist entity) {
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void update(Long aLong, Farmacist entity) {

    }

    @Override
    public Farmacist findOne(Long aLong) {
        Farmacist pharmacist = null;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();

                pharmacist = session.createQuery("from Farmacist where id=:idx", Farmacist.class)
                        .setParameter("idx", aLong)
                        .getSingleResult();

                transaction.commit();

                return pharmacist;
            } catch (RuntimeException exception) {
                if (transaction != null)
                    transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public List<Farmacist> findAll() {
        return null;
    }
}
