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
public class ObtenerPruebaPaciente extends Comando
{
    private ColeccionPaciente coleccionPaciente;    

    /**
     * Constructor for objects of class RegistroVacunacion
     */
    public ObtenerPruebaPaciente(ColeccionPaciente coleccionPaciente)
    {
        // initialise instance variables
        super();
        this.parametros.put("dni", new Parametro<String>("dni", "DNI del paciente"));         
        this.coleccionPaciente = coleccionPaciente;
        
    }

    public ResultadoComando ejecutar()
    {
        String dni = ((Parametro<String>)parametros.get("dni")).getValor();
                
        Paciente paciente = (Paciente)coleccionPaciente.getByDni(dni);

        if (paciente != null)
        {
            Prueba prueba = paciente.getPruebaPendienteValidacion();
            if(prueba != null)
            {
                return new ResultadoComando(TipoResultadoComando.NUEVO_COMANDO, null, new RegistroPruebaTecnico(prueba));
            }
            else
            {                
                return new ResultadoComando(TipoResultadoComando.ERROR, "No hay pruebas pendientes de validar para este paciente");
            }
        }
        else
        {
            return new ResultadoComando(TipoResultadoComando.ERROR, "Paciente con dni: " + dni + " no lo tienes asignado para realizar prueba.");
        }

    }

    public String getDescripcion()
    {
        return "Registrar prueba realizada a paciente";
    }
}
