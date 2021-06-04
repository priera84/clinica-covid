package clinica.motor;

import clinica.logica.*;
import java.time.LocalDate;

/**
 * Comando que consulta el inventario de una vacuna.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class ConsultarStockVacuna extends Comando
{
    // instance variables - replace the example below with your own
    private StockVacuna stockVacuna;
    /**
     * Constructor for objects of class RegistrarStockVacuna
     */
    public ConsultarStockVacuna(StockVacuna stockVacuna)
    {
        super();
        this.stockVacuna = stockVacuna;
    }

    public ResultadoComando ejecutar()
    {
      
        String descripcionStock = this.stockVacuna.consultarStock();
        if(descripcionStock != null)
        {
            return new ResultadoComando(TipoResultadoComando.EXITO, "Estado de stock: \n" + descripcionStock);
        }
        else
        {                
            return new ResultadoComando(TipoResultadoComando.ERROR, "Error al obtener el stock.");
        }

    }

    public String getDescripcion()
    {
        return "Consultar inventario de vacunas.";
    }
}
