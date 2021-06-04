package clinica.motor;

import clinica.logica.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

/**
 * Comando que da de baja un paciente.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class BajaPaciente extends Comando
{
    ColeccionPaciente coleccionPaciente;
    /**
     * Constructor for objects of class BajaPaciente
     */
    public BajaPaciente(ColeccionPaciente coleccionPaciente)
    {
        // initialise instance variables
        super();
        this.parametros.put("dni", new Parametro<String>("dni", "DNI"));
        this.coleccionPaciente = coleccionPaciente;
    }

    public ResultadoComando ejecutar()
    {
        String dni = ((Parametro<String>)parametros.get("dni")).getValor();
        if(coleccionPaciente.bajaPaciente(dni))
            return new ResultadoComando(TipoResultadoComando.EXITO, "Paciente dado de baja correctamente");
        else 
            return new ResultadoComando(TipoResultadoComando.ERROR, "El paciente que intenta dar de baja, no existe.");
    }

    public String getDescripcion()
    {
        return "Baja Paciente";
    }    
}

