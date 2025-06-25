package cl.duoc.microservicioTienda.TestService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import cl.duoc.microservicioTienda.model.Tienda;
import cl.duoc.microservicioTienda.repository.TiendaRespository;
import cl.duoc.microservicioTienda.service.TiendaService;

public class TestTiendaService {

    @Mock
    private TiendaRespository tiendaRespository;

    @InjectMocks
    private TiendaService tiendaService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBuscarTodo(){

        List<Tienda> lista = new ArrayList<>();

        Tienda Tienda1 = new Tienda();
        Tienda Tienda2 = new Tienda();

        Tienda1.setCiudad("Santiago");
        Tienda1.setDireccion("San joaquin 1234");
        Tienda1.setId_tienda(1);
        Tienda1.setNombre("EcoVerde");
        Tienda1.setTelefono("123456789");

        Tienda2.setCiudad("Valparaiso");
        Tienda2.setDireccion("Concepcion 245");
        Tienda2.setId_tienda(2);
        Tienda2.setNombre("EcoMarket friends");
        Tienda2.setTelefono("987654321");

        lista.add(Tienda1);
        lista.add(Tienda2);

        when(tiendaRespository.findAll()).thenReturn(lista);

        List<Tienda> resultadoBusqueda = tiendaService.BuscarTienda();

        assertEquals(2, resultadoBusqueda.size());
        verify(tiendaRespository, times(1)).findAll();
    }

    @Test
    public void testBuscarUnTienda(){
        Tienda Tienda1 = new Tienda();
        Tienda1.setCiudad("Santiago");
        Tienda1.setDireccion("San joaquin 1234");
        Tienda1.setId_tienda(1);
        Tienda1.setNombre("EcoVerde");
        Tienda1.setTelefono("123456789");


        when(tiendaRespository.findById(1)).thenReturn(Optional.of(Tienda1));

        Tienda Tiendabuscado = tiendaService.BuscarunaTienda(1);
        assertEquals(1, Tiendabuscado.getId_tienda());
        verify(tiendaRespository, times(1)).findById(1);
    }

    @Test
    public void testGuardarTienda(){
        Tienda Tienda1 = new Tienda();
        Tienda1.setCiudad("Santiago");
        Tienda1.setDireccion("San joaquin 1234");
        Tienda1.setId_tienda(1);
        Tienda1.setNombre("EcoVerde");
        Tienda1.setTelefono("123456789");

        when(tiendaRespository.save(Tienda1)).thenReturn(Tienda1);

        Tienda TiendaGuardado = tiendaService.GuardarunaTienda(Tienda1);

        assertEquals(1, TiendaGuardado.getId_tienda());
        verify(tiendaRespository, times(1)).save(Tienda1);

    }

    @Test
    public void testeliminarTienda(){
        int Id_Tienda = 1;
        doNothing().when(tiendaRespository).deleteById(Id_Tienda);

        tiendaService.EliminarTienda(Id_Tienda);

        verify(tiendaRespository,times(1)).deleteById(Id_Tienda);
    }
}
