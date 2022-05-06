package repository;


import java.util.List;

public interface Repository<ID, E> {

    void save(E entity);

    void delete(ID id);

    void update(ID id, E entity);

    E findOne(ID id);

    List<E> findAll();
}