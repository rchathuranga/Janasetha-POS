package lk.janasetha.thogakade.repository;

import java.util.List;

public interface CrudDAO<T,Id> extends SuperDAO{
    Id add(T t) throws Exception;

    boolean update(T t) throws Exception;

    boolean delete(Id id) throws Exception;

    T search(Id id) throws Exception;

    List<T> getAll() throws Exception;
}
