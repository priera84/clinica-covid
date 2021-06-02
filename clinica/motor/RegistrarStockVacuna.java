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
public class RegistrarStockVacuna extends Comando
{
    // instance variables - replace the example below with your own
    private StockVacuna stockVacuna;
    /**
     * Constructor for objects of class RegistrarStockVacuna
     */
    public RegistrarStockVacuna(StockVacuna stockVacuna)
    {
        super();
        this.stockVacuna = stockVacuna;

        this.parametros.put("fechaRegistro", new Parametro<LocalDate>("fechaRegistro", "Fecha registro", null, x -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            return LocalDate.parse(x, formatter);
            
        }));
        this.parametros.put("unidadesRegistradas", new Parametro<Integer>("unidadesRegistradas", "Indique número de unidades a registrar", null, x ->{
                    return Integer.parseInt(x);
                }));
        this.parametros.put("marcaVacuna", new Parametro<TipoMarcaVacuna>("marcaVacuna", "Tipo vacuna a registrar (PFIZER, MODERNA, JOHNSON_AND_JOHNSON)", null, x ->{
                    return TipoMarcaVacuna.valueOf(x);
                }));        

    }

    public ResultadoComando ejecutar()
    {
        LocalDate fechaRegistro = ((Parametro<LocalDate>)parametros.get("fechaRegistro")).getValor();
        Integer unidadesRegistradas = ((Parametro<Integer>)parametros.get("unidadesRegistradas")).getValor();
        TipoMarcaVacuna marcaVacuna = ((Parametro<TipoMarcaVacuna>)parametros.get("marcaVacuna")).getValor();

        if(this.stockVacuna.registrarStock(new RegistroVacuna(fechaRegistro, unidadesRegistradas, marcaVacuna)))
        {
            return new ResultadoComando(TipoResultadoComando.EXITO, "Vacuna registrada correctamente");
        }
        else
        {                
            return new ResultadoComando(TipoResultadoComando.ERROR, "No ha indicado el número de unidades a registrar.");
        }

    }

    public String getDescripcion()
    {
        return "Registrar vacuna en inventario.";
    }
}
