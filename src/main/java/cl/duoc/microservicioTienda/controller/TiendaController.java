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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/v1/Tienda")

public class TiendaController {

    @Autowired
    private TiendaService tiendaservice;

    //endpoint para listar las tiendas
    @GetMapping
    @Operation(summary = "Tienda", description = "Operación que lista el Tienda")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se listó correctamente la Tienda",
            content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Tienda.class))),
        @ApiResponse(responseCode = "404", description = "No se encontro nada en Tienda",
                content = @Content(mediaType = "application/json",
                schema = @Schema(type = "string", example = "no se encuentran Datos")))
    })
    public ResponseEntity<?> ListarTienda(){
        List<Tienda> Tienda = tiendaservice.BuscarTienda();
        if (Tienda.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encuentran Datos");
        } else {
            return ResponseEntity.ok(Tienda);
        }
    }

    //endpoint para buscar una tienda
    @GetMapping("/{id_tienda}")
    @Operation(summary = "Endpoint que busca un Tienda", description = "Operación que busca y lista una Tienda")
    @Parameters(value = {
        @Parameter(name = "id_Tienda", description = "Id de la Tienda que se va a buscar", in = ParameterIn.PATH, required = true)
    })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se lista correctamente la Tienda",
            content = @Content(mediaType = "application/json",
                                schema = @Schema(implementation = Tienda.class))),
        @ApiResponse(responseCode = "404", description = "No se encuentra la Tienda",
                content = @Content(mediaType = "application/json",
                schema = @Schema(type = "string", example = "No se encuentran Tiendas")))
    })
    public ResponseEntity<?> BuscarUnaTienda(@PathVariable int id_tienda){
        try {
            Tienda tiendabuscada = tiendaservice.BuscarunaTienda(id_tienda);
            return ResponseEntity.ok(tiendabuscada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("tienda no encontrada");
        }
    }

    //endpoint para guardar una tienda
    @PostMapping
    @Operation(summary = "Endpoint que registra una Tienda", description = "Endpoint que registra un Tienda", 
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto Tienda que se va a registrar", required = true,
    content = @Content(schema = @Schema(implementation = Tienda.class))
    ))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Indica que se registro correctamente la Tienda", 
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Tienda.class))),
        @ApiResponse(responseCode = "500", description = "Indica que no se logro registrar la Tienda",
        content = @Content(schema = @Schema(type = "String", example = "No se puede registar la Tienda")))
    })
    public ResponseEntity<?> GuardarTienda(@RequestBody Tienda guadarTienda){
        try {
            Tienda registrartienda = tiendaservice.GuardarunaTienda(guadarTienda);
            return ResponseEntity.ok(registrartienda);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Tienda no registrada");
        }
    }

    //endpoint para eliminar una tienda
    @DeleteMapping("/{id_tienda}")
    @Operation(summary = "Endpoint que busca y elimina una Tienda", description = "Operación que busca y elimina una Tienda")
    @Parameters(value = {
        @Parameter(name = "id_Tienda", description = "Id de la Tienda que se va a eliminar", in = ParameterIn.PATH, required = true)
    })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Se elimina Tienda",
        content = @Content(mediaType = "application/json",
        schema = @Schema(type = "string", example = "Se elimina Tienda"))),
        @ApiResponse(responseCode = "404", description = "Tienda no esta registrada",
                content = @Content(mediaType = "application/json",
                schema = @Schema(type = "string", example = "Tienda no esta registrada")))
    })
    public ResponseEntity<String> EliminarunaTienda(@PathVariable int id_tienda){
        try {
            Tienda tiendabuscada = tiendaservice.BuscarunaTienda(id_tienda);
            tiendaservice.EliminarTienda(id_tienda);
            return ResponseEntity.status(HttpStatus.OK).body("Tienda Eliminado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tienda no encontrada");
        }
    }

    //endpoint que edita una tienda
    @PutMapping("/{id_tienda}")
    @Operation(summary = "Endpoint que edita una Tienda", description = "Endpoint que edita una Tienda", 
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto Tienda que se va a editar", required = true,
    content = @Content(schema = @Schema(implementation = Tienda.class))
    ))
    @Parameters(value = {
        @Parameter(name = "idTienda", description = "Id de la Tienda que se va a editar", in = ParameterIn.PATH, required = true)
    })
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",description = "Indica que se registro correctamente la Tienda", 
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Tienda.class))),
        @ApiResponse(responseCode = "500", description = "Tienda no esta registrada",
        content = @Content(schema = @Schema(type = "String", example = "Tienda no esta registrada")))
    })
    public ResponseEntity<?> ActualizarTienda(@PathVariable int id_tienda, @RequestBody Tienda actualizartienda){
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
