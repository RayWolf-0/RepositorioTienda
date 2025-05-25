package cl.duoc.microservicioTienda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.microservicioTienda.model.Tienda;
import cl.duoc.microservicioTienda.service.TiendaService;

@RestController
@RequestMapping("/api/v1/Tienda")

public class TiendaController {

    @Autowired
    private TiendaService tiendaservice;

    @GetMapping
    public ResponseEntity<?> ListarTienda(){
        List<Tienda> Tienda = tiendaservice.BuscarTienda();
        if (Tienda.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran Datos");
        } else {
            return ResponseEntity.ok(Tienda);
        }
    }

    @GetMapping("/{id_tienda}")
    public ResponseEntity<?> BuscarUnaTienda(@PathVariable Long id_tienda){
        try {
            Tienda tiendabuscada = tiendaservice.BuscarunaTienda(id_tienda);
            return ResponseEntity.ok(tiendabuscada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("tienda no encontrada");
        }
    }

    @PostMapping
    public ResponseEntity<?> GuardarTienda(@RequestBody Tienda guadarTienda){
        try {
            Tienda registrartienda = tiendaservice.GuardarunaTienda(guadarTienda);
            return ResponseEntity.ok(registrartienda);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tienda no registrada");
        }
    }

    @DeleteMapping("/{id_tienda}")
    public ResponseEntity<String> EliminarunaTienda(@PathVariable Long id_tienda){
        try {
            Tienda tiendabuscada = tiendaservice.BuscarunaTienda(id_tienda);
            tiendaservice.EliminarTienda(id_tienda);
            return ResponseEntity.status(HttpStatus.OK).body("Tienda Eliminado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tienda no encontrada");
        }
    }

    @PutMapping("/{id_tienda}")
    public ResponseEntity<?> ActualizarTienda(@PathVariable Long id_tienda, @RequestBody Tienda actualizartienda){
        try {
            Tienda tiendaactualizada = tiendaservice.BuscarunaTienda(id_tienda);
            tiendaactualizada.setCiudad(actualizartienda.getCiudad());
            tiendaactualizada.setDireccion(actualizartienda.getDireccion());
            tiendaactualizada.setId_tienda(actualizartienda.getId_tienda());
            tiendaactualizada.setNombre(actualizartienda.getNombre());
            tiendaactualizada.setTelefono(actualizartienda.getTelefono());
            return ResponseEntity.ok(tiendaactualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tienda no registrado");
        }
    }


}
