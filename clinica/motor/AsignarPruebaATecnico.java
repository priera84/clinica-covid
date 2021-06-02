package clinica.motor;

import clinica.logica.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Write a description of class AsignarPruebaATecnico here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AsignarPruebaATecnico extends Comando
{
    // instance variables - replace the example below with your own
    private ColeccionTecnico coleccionTecnico;
    private ColeccionPaciente coleccionPaciente;

    /**
     * Constructor for objects of class AsignarPruebaATecnico
     */
    public AsignarPruebaATecnico(ColeccionTecnico coleccionTecnico, ColeccionPaciente coleccionPaciente)
    {
        // initialise instance variables
        super();
        this.parametros.put("fechaHora", new Parametro<LocalDateTime>("fechaHora", "Fecha Hora prueba dd/MM/yyyy HH:mm", LocalDateTime.now(), x ->{
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    return LocalDateTime.parse(x, formatter);
                }));
        this.coleccionTecnico = coleccionTecnico;
        this.coleccionPaciente = coleccionPaciente;
    }

    public ResultadoComando ejecutar()
    {
        LocalDateTime dateTime = ((Parametro<LocalDateTime>)parametros.get("fechaHora")).getValor();

        Tecnico tecnico = coleccionTecnico.obtenerTecnicoDisponible(dateTime);
        if(tecnico != null)
        {

            return new ResultadoComando(TipoResultadoComando.EXITO, "Técnico asignado:\n" + tecnico.getDescripcion());
        }
        else 
            return new ResultadoComando(TipoResultadoComando.ERROR, "No hay técnicos disponibles.");
    }

    public String getDescripcion()
    {
        return "Asignar prueba a técnico";
    }    
}
