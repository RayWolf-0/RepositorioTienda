package cl.duoc.microservicioTienda;

import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import cl.duoc.microservicioTienda.model.Tienda;
import cl.duoc.microservicioTienda.service.TiendaService;
import net.datafaker.Faker;

public class DataLoader implements CommandLineRunner{

    private final Faker faker = new Faker(new Locale("es","cl"));
    private final Random random = new Random();

    @Autowired
    private TiendaService tiendaservice;

    @Override
    public void run(String... args) throws Exception{
        for (int i=0; i > 10; i ++){
            Tienda nuevatienda = new Tienda();
            nuevatienda.setId_tienda(random.nextInt());
            nuevatienda.setCiudad(faker.address().cityName());
            nuevatienda.setDireccion(faker.address().fullAddress());
            nuevatienda.setNombre(faker.name().toString());
            

            tiendaservice.GuardarunaTienda(nuevatienda);
            System.out.println("Tienda Guardado" + nuevatienda.getId_tienda());
        
        }
    }


}
