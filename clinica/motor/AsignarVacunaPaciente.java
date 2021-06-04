package clinica.motor;

import clinica.logica.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * Clase que representa un comando que permite programar una vacuna para un paciente. Asignando un enfermero disponible.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class AsignarVacunaPaciente extends Comando
{
    private ColeccionPaciente coleccionPaciente;
    private Planificador planificador;

    /**
     * Constructor for objects of class AsignarVacunacionAEnfermero
     */
    public AsignarVacunaPaciente(ColeccionPaciente coleccionPaciente, Planificador planificador)
    {
        // initialise instance variables
        super();
        this.parametros.put("dni", new Parametro<String>("dni", "DNI del paciente"));              
        this.parametros.put("fecha", new Parametro<LocalDate>("fecha", "Fecha", null, x -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                    return LocalDate.parse(x, formatter);
                }));
        this.coleccionPaciente = coleccionPaciente;
        this.planificador = planificador;
    }

    public ResultadoComando ejecutar()
    {
        String dni = ((Parametro<String>)parametros.get("dni")).getValor();
        LocalDate fecha = ((Parametro<LocalDate>)parametros.get("fecha")).getValor();        

        Paciente paciente = (Paciente)coleccionPaciente.getByDni(dni);

        if (paciente != null)
        {
            if(planificador.programarVacunacion(fecha, paciente))
            {
                return new ResultadoComando(TipoResultadoComando.EXITO, "Vacunación programada:\n" + paciente.getDescripcionVacuna());
            }
            else
            {                
                return new ResultadoComando(TipoResultadoComando.ERROR, "No hay enfermeros disponibles para realizar la vacunación");
            }
        }
        else
        {
            return new ResultadoComando(TipoResultadoComando.ERROR, "No existe el paciente con dni: " + dni);
        }

    }

    public String getDescripcion()
    {
        return "Programar vacuna a paciente";
    }
}
