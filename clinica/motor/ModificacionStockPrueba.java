package clinica.motor;

import clinica.logica.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Comando que agrega una vacuna en el inventario.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class ModificacionStockPrueba extends Comando
{
    // instance variables - replace the example below with your own
    private StockPrueba stockPrueba;
    /**
     * Constructor for objects of class RegistrarStockVacuna
     */
    public ModificacionStockPrueba(StockPrueba stockPrueba)
    {
        super();
        this.stockPrueba = stockPrueba;

        this.parametros.put("fechaModificacion", new Parametro<LocalDate>("fechaModificacion", "Fecha modificación", null, x -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                    return LocalDate.parse(x, formatter);

                }));
        this.parametros.put("unidadesRegistradas", new Parametro<Integer>("unidadesRegistradas", "Indique número total de unidades en stock", null, x ->{
                    return Integer.parseInt(x);
                }));
        this.parametros.put("marcaPrueba", new Parametro<TipoPrueba>("marcaPrueba", "Tipo prueba a registrar (PCR, SEROLOGICA, ANTIGENOS)", null, x ->{
                    return TipoPrueba.valueOf(x);
                }));        

    }

    public ResultadoComando ejecutar()
    {
        LocalDate fechaModificacion = ((Parametro<LocalDate>)parametros.get("fechaModificacion")).getValor();
        Integer unidadesRegistradas = ((Parametro<Integer>)parametros.get("unidadesRegistradas")).getValor();
        TipoPrueba marcaPrueba = ((Parametro<TipoPrueba>)parametros.get("marcaPrueba")).getValor();

        if(this.stockPrueba.modificarPrueba(fechaModificacion, unidadesRegistradas, marcaPrueba))
        {
            return new ResultadoComando(TipoResultadoComando.EXITO, "Stock de prueba modificada correctamente");
        }
        else
        {                
            return new ResultadoComando(TipoResultadoComando.ERROR, "Ha ocurrido un error al intentar modificar el stock de la prueba.");
        }

    }

    public String getDescripcion()
    {
        return "Modificar inventario de prueba.";
    }
}
