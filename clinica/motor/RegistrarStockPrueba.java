package clinica.motor;

import clinica.logica.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Comando que registra una prueba en el inventario.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class RegistrarStockPrueba extends Comando
{
    // instance variables - replace the example below with your own
    private StockPrueba stockPrueba;
    /**
     * Constructor for objects of class RegistrarStockVacuna
     */
    public RegistrarStockPrueba(StockPrueba stockPrueba)
    {
        super();
        this.stockPrueba = stockPrueba;

        this.parametros.put("fechaRegistro", new Parametro<LocalDate>("fechaRegistro", "Fecha registro", null, x -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            return LocalDate.parse(x, formatter);
            
        }));
        this.parametros.put("unidadesRegistradas", new Parametro<Integer>("unidadesRegistradas", "Indique número de unidades a registrar", null, x ->{
                    return Integer.parseInt(x);
                }));
        this.parametros.put("marcaPrueba", new Parametro<TipoPrueba>("marcaPrueba", "Tipo prueba a registrar (PCR, SEROLOGICA, ANTIGENOS)", null, x ->{
                    return TipoPrueba.valueOf(x);
                }));        

    }

    public ResultadoComando ejecutar()
    {
        LocalDate fechaRegistro = ((Parametro<LocalDate>)parametros.get("fechaRegistro")).getValor();
        Integer unidadesRegistradas = ((Parametro<Integer>)parametros.get("unidadesRegistradas")).getValor();
        TipoPrueba marcaPrueba = ((Parametro<TipoPrueba>)parametros.get("marcaPrueba")).getValor();

        if(this.stockPrueba.registrarStock(new RegistroPrueba(fechaRegistro, unidadesRegistradas, marcaPrueba)))
        {
            return new ResultadoComando(TipoResultadoComando.EXITO, "Prueba registrada correctamente");
        }
        else
        {                
            return new ResultadoComando(TipoResultadoComando.ERROR, "No ha indicado el número de unidades a registrar.");
        }

    }

    public String getDescripcion()
    {
        return "Registrar prueba en inventario.";
    }
}
