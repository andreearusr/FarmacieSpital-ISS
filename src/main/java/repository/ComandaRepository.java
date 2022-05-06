package repository;

import domain.Comanda;

import java.util.List;

public interface ComandaRepository extends Repository<Long, Comanda> {
    List<Comanda> getComenziSectie(Long idSectie);

    List<Comanda> getComenziInAsteptare();

    List<Comanda> getComenziOnorate();
}
