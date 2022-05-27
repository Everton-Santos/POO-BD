package DAO;

import Entity.Evento;

import java.util.List;

public interface EventosDAO {
    void inserir(Evento e);
    List<Evento> consultar(String nome);
}
