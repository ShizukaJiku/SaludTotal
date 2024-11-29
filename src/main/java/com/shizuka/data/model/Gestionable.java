package com.shizuka.data.model;

import java.util.List;

public interface Gestionable<T> {
    void agregar(T item);
    void eliminar(int id);
    T findById(int id);
    List<T> listar();
}