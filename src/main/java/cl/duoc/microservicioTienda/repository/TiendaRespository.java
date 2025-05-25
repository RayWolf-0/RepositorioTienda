package cl.duoc.microservicioTienda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.microservicioTienda.model.Tienda;

public interface TiendaRespository extends JpaRepository<Tienda, Long>{

}
