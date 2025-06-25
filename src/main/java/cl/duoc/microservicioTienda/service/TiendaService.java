package cl.duoc.microservicioTienda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.microservicioTienda.model.Tienda;
import cl.duoc.microservicioTienda.repository.TiendaRespository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class TiendaService {

    @Autowired
    private TiendaRespository tiendarespository;

    public List<Tienda> BuscarTienda(){
        return tiendarespository.findAll();
    }

    public Tienda BuscarunaTienda(int id_tienda){
        return tiendarespository.findById(id_tienda).get();
    }

    public Tienda GuardarunaTienda(Tienda nombre){
        return tiendarespository.save(nombre);
    }

    public void EliminarTienda(int id_tienda){
        tiendarespository.deleteById(id_tienda);
    }

}
