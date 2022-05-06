package repository.orm;

import domain.Comanda;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.ComandaRepository;

import java.util.ArrayList;
import java.util.List;

public class ComandaORMRepository extends HibernateRepository implements ComandaRepository {


    public ComandaORMRepository() {

    }

    @Override
    public void save(Comanda entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(entity);
                transaction.commit();

            } catch (RuntimeException exception) {
                if (transaction != null)
                    transaction.rollback();
            }
        }

    }

    @Override
    public void delete(Long aLong) {
        Comanda comanda = findOne(aLong);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.delete(comanda);
                transaction.commit();
            } catch (RuntimeException exception) {
                if (transaction != null)
                    transaction.rollback();
            }
        }
    }

    @Override
    public void update(Long aLong, Comanda entity) {
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
    public Comanda findOne(Long aLong) {
        return null;
    }

    @Override
    public List<Comanda> findAll() {
        return null;
    }


    @Override
    public List<Comanda> getComenziSectie(Long idSectie) {
        List<Comanda> comenzi = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                comenzi = session.createQuery("select c from Comanda c where idSectie=:idx", Comanda.class)
                        .setParameter("idx", idSectie)
                        .getResultList();

                transaction.commit();

            } catch (RuntimeException exception) {
                if (transaction != null)
                    transaction.rollback();
            }
        }
        return comenzi;
    }

    @Override
    public List<Comanda> getComenziInAsteptare() {
        List<Comanda> comenzi = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                comenzi = session.createQuery("select c from Comanda c where status=:st", Comanda.class)
                        .setParameter("st", "IN_ASTEPTARE")
                        .getResultList();

                transaction.commit();

            } catch (RuntimeException exception) {
                if (transaction != null)
                    transaction.rollback();
            }
        }
        return comenzi;
    }

    @Override
    public List<Comanda> getComenziOnorate() {
        List<Comanda> comenzi = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                comenzi = session.createQuery("select c from Comanda c where status=:st", Comanda.class)
                        .setParameter("st", "ONORATA")
                        .getResultList();

                transaction.commit();

            } catch (RuntimeException exception) {
                if (transaction != null)
                    transaction.rollback();
            }
        }
        return comenzi;
    }
}
