package clinica.motor;

import clinica.logica.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Write a description of class AsignarPruebaAEnfermero here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AsignarPruebaAEnfermero extends Comando
{
     private ColeccionEnfermero coleccionEnfermero;
    private ColeccionPaciente coleccionPaciente;

    /**
     * Constructor for objects of class AsignarVacunacionAEnfermero
     */
    public AsignarPruebaAEnfermero(ColeccionEnfermero coleccionEnfermero, ColeccionPaciente coleccionPaciente)
    {
        // initialise instance variables
        super();
        this.parametros.put("fechaHora", new Parametro<LocalDateTime>("fechaHora", "Fecha Hora prueba dd/MM/yyyy HH:mm", LocalDateTime.now(), x ->{
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    return LocalDateTime.parse(x, formatter);
                }));
        this.coleccionEnfermero = coleccionEnfermero;
        this.coleccionPaciente = coleccionPaciente;
    }

    public ResultadoComando ejecutar()
    {
        LocalDateTime dateTime = ((Parametro<LocalDateTime>)parametros.get("fechaHora")).getValor();

        Enfermero enfermero= coleccionEnfermero.obtenerEnfermeroDisponible(dateTime);
        if(enfermero != null)
        {
            return new ResultadoComando(TipoResultadoComando.EXITO, "Enfermero asignado:\n" + enfermero.getDescripcion());
        }
        else 
            return new ResultadoComando(TipoResultadoComando.ERROR, "No hay enfermeros disponibles para pruebas diagnósticas.");
    }

    public String getDescripcion()
    {
        return "Asignar vacunación a enfermero";
    }
}
