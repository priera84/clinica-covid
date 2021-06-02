package clinica.motor;

import clinica.logica.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

/**
 * Write a description of class BajaPaciente here.
 *
 * @author (your name)
 * @version (a version number or a date)
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

