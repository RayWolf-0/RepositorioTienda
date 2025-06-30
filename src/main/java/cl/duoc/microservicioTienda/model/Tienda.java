package cl.duoc.microservicioTienda.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TIENDA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa una tienda")

public class Tienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIENDA")
    @Schema(description = "id de la tienda")

    private int id_tienda;

    @Column(name= "NOMBRE", nullable = false, length = 100)
    @Schema(description = "nombre de la tienda")
    private String nombre;

    @Column(name= "CIUDAD", nullable = false, length = 50)
    @Schema(description = "ciudad de la tienda")
    private String ciudad;

    @Column(name= "DIRECCION",  length = 200)
    @Schema(description = "direccion de la tienda")
    private String direccion; 

    @Column(name= "TELEFONO", nullable = false, length = 20)
    @Schema(description = "telefono")
    private String telefono; 



}
