package clinica.motor;

import clinica.logica.*;
import java.time.LocalDate;

/**
 * Comando que consulta el inventario de prueba.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class ConsultarStockPrueba extends Comando
{
    // instance variables - replace the example below with your own
    private StockPrueba stockPrueba;
    /**
     * Constructor for objects of class RegistrarStockVacuna
     */
    public ConsultarStockPrueba(StockPrueba stockPrueba)
    {
        super();
        this.stockPrueba = stockPrueba;
    }

    public ResultadoComando ejecutar()
    {
      
        String descripcionStock = this.stockPrueba.consultarStock();
        if(descripcionStock != null)
        {
            return new ResultadoComando(TipoResultadoComando.EXITO, "Estado de inventario de pruebas: \n" + descripcionStock);
        }
        else
        {                
            return new ResultadoComando(TipoResultadoComando.ERROR, "Error al obtener la descripción del inventario de pruebas.");
        }

    }

    public String getDescripcion()
    {
        return "Consultar inventario de pruebas.";
    }
}
