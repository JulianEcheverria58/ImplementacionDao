package dao;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<T> implements DAO<T> {
    protected List<T> lista = new ArrayList<>();

    @Override
    public List<T> listar() {
        return lista;
    }

    @Override
    public void insertar(T objeto) {
        lista.add(objeto);
    }

    @Override
    public void eliminar(T objeto) {
        lista.remove(objeto);
    }
}
