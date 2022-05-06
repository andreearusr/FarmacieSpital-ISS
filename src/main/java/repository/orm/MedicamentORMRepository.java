package repository.orm;

import domain.Medicament;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.MedicamentRepository;

import java.util.ArrayList;
import java.util.List;

public class MedicamentORMRepository extends HibernateRepository implements MedicamentRepository {

    public MedicamentORMRepository() {

    }


    @Override
    public void save(Medicament entity) {
    }

    @Override
    public void delete(Long aLong) {
    }

    @Override
    public void update(Long aLong, Medicament entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.update(entity);
                transaction.commit();
            } catch (RuntimeException exception) {
                if (transaction != null)
                    transaction.rollback();
            }
        }

    }

    @Override
    public Medicament findOne(Long aLong) {
        return null;
    }

    @Override
    public List<Medicament> findAll() {
        List<Medicament> medicamente = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                medicamente = session.createQuery("select m from Medicament m", Medicament.class).getResultList();

                transaction.commit();

            } catch (RuntimeException exception) {
                if (transaction != null)
                    transaction.rollback();
            }
        }
        return medicamente;
    }
}

