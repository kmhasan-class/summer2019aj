package bd.edu.seu.vaadindemo.service;

import java.util.List;

public interface GenericService<T, I> {
    List<T> findAll();
    T findById(I i);
    T save(T t);
    void delete(T t);

    // possibly many more
}
