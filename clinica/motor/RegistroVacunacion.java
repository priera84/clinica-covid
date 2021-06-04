package clinica.motor;

import clinica.logica.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * Clase que representa un comando que permite registrar la vacunación de un paciente.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class RegistroVacunacion extends Comando
{
    private ColeccionPaciente coleccionPaciente;    

    /**
     * Constructor for objects of class RegistroVacunacion
     */
    public RegistroVacunacion(ColeccionPaciente coleccionPaciente)
    {
        // initialise instance variables
        super();
        this.parametros.put("dni", new Parametro<String>("dni", "DNI del paciente")); 
        this.parametros.put("fecha", new Parametro<LocalDate>("fecha", "Fecha vacunación", LocalDate.now(), x -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            return LocalDate.parse(x, formatter);            
        }));
        this.coleccionPaciente = coleccionPaciente;
        
    }

    public ResultadoComando ejecutar()
    {
        String dni = ((Parametro<String>)parametros.get("dni")).getValor();
        LocalDate fecha = ((Parametro<LocalDate>)parametros.get("fecha")).getValor();
        
        Paciente paciente = (Paciente)coleccionPaciente.getByDni(dni);

        if (paciente != null)
        {
            if(paciente.marcarDosisAplicada(fecha))
            {
                return new ResultadoComando(TipoResultadoComando.EXITO, "Dosis aplicada correctamente:\n" + paciente.getDescripcionVacuna());
            }
            else
            {                
                return new ResultadoComando(TipoResultadoComando.ERROR, "No hay dosis pendientes de aplicar para este paciente en la fecha indicada");
            }
        }
        else
        {
            return new ResultadoComando(TipoResultadoComando.ERROR, "Paciente con dni: " + dni + " no lo tienes asignada para vacunación.");
        }

    }

    public String getDescripcion()
    {
        return "Registrar vacunación paciente";
    }
}
