package clinica.motor;

import clinica.logica.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
/**
 * Write a description of class AsignarVacunacionAEnfermero here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AsignarVacunacionAEnfermero extends Comando
{
    // instance variables - replace the example below with your own
    private ColeccionEnfermero coleccionEnfermero;
    private ColeccionPaciente coleccionPaciente;

    /**
     * Constructor for objects of class AsignarVacunacionAEnfermero
     */
    public AsignarVacunacionAEnfermero(ColeccionEnfermero coleccionEnfermero, ColeccionPaciente coleccionPaciente)
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

        Enfermero enfermero= coleccionEnfermero.obtenerEnfermeroDisponibleVacunacion(dateTime);
        if(enfermero != null)
        {
            return new ResultadoComando(TipoResultadoComando.EXITO, "Enfermero asignado:\n" + enfermero.getDescripcion());
        }
        else 
            return new ResultadoComando(TipoResultadoComando.ERROR, "No hay enfermeros disponibles para vacunación.");
    }

    public String getDescripcion()
    {
        return "Asignar vacunación a enfermero";
    }
}
