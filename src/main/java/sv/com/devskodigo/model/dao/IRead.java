package sv.com.devskodigo.model.dao;

import java.util.List;

public interface IRead<T, Id> {
    List<T> getList();

    T read(Id id);
}
