package clinica.motor;

import clinica.logica.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Comando que modifica el inventario de  una vacuna.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class ModificacionStockVacuna extends Comando
{
    // instance variables - replace the example below with your own
    private StockVacuna stockVacuna;
    /**
     * Constructor for objects of class RegistrarStockVacuna
     */
    public ModificacionStockVacuna(StockVacuna stockVacuna)
    {
        super();
        this.stockVacuna = stockVacuna;

        this.parametros.put("fechaModificacion", new Parametro<LocalDate>("fechaModificacion", "Fecha modificación", null, x -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            return LocalDate.parse(x, formatter);
            
        }));
        this.parametros.put("unidadesRegistradas", new Parametro<Integer>("unidadesRegistradas", "Indique número total de unidades en stock", null, x ->{
                    return Integer.parseInt(x);
                }));
        this.parametros.put("marcaVacuna", new Parametro<TipoMarcaVacuna>("marcaVacuna", "Tipo vacuna a modificar (PFIZER, MODERNA, JOHNSON_AND_JOHNSON)", null, x ->{
                    return TipoMarcaVacuna.valueOf(x);
                }));        

    }

    public ResultadoComando ejecutar()
    {
        LocalDate fechaModificacion = ((Parametro<LocalDate>)parametros.get("fechaModificacion")).getValor();
        Integer unidadesRegistradas = ((Parametro<Integer>)parametros.get("unidadesRegistradas")).getValor();
        TipoMarcaVacuna marcaVacuna = ((Parametro<TipoMarcaVacuna>)parametros.get("marcaVacuna")).getValor();

        if(this.stockVacuna.modificarVacuna(fechaModificacion, unidadesRegistradas, marcaVacuna))
        {
            return new ResultadoComando(TipoResultadoComando.EXITO, "Vacuna modificada correctamente");
        }
        else
        {                
            return new ResultadoComando(TipoResultadoComando.ERROR, "Ha ocurrido un error al intentar modificar la vacuna.");
        }

    }

    public String getDescripcion()
    {
        return "Modificar vacuna inventario.";
    }
}
