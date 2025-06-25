package cl.duoc.microservicioTienda.model;

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
public class Tienda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIENDA")
    private int id_tienda;

    @Column(name= "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name= "CIUDAD", nullable = false, length = 50)
    private String ciudad;

    @Column(name= "DIRECCION",  length = 200)
    private String direccion; 

    @Column(name= "TELEFONO", nullable = false, length = 20)
    private String telefono; 



}
