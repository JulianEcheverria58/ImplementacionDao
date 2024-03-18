package dao;

import java.util.List;

public interface DAO<T> {
    List<T> listar();
    void insertar(T objeto);
    void eliminar(T objeto);
}
