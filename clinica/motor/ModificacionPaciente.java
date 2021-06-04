package clinica.motor;

import clinica.logica.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
/**
 * Comando que da modifica un paciente.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class ModificacionPaciente extends Comando
{
    ColeccionPaciente coleccionPaciente;
    /**
     * Constructor for objects of class ModificacionPaciente
     */
    public ModificacionPaciente(ColeccionPaciente coleccionPaciente)
    {
        // initialise instance variables
        super();
        this.parametros.put("dni", new Parametro("dni", "DNI",null, null));
        this.parametros.put("direccionNew", new Parametro("direccionNew", "Nueva dirección", null, null));
        this.coleccionPaciente = coleccionPaciente;
    }

    public ResultadoComando ejecutar()
    {
        String dni = ((Parametro<String>)parametros.get("dni")).getValor();
        String direccionNew = ((Parametro<String>)parametros.get("direccionNew")).getValor();

        if(coleccionPaciente.modificarPaciente(dni, direccionNew))
            return new ResultadoComando(TipoResultadoComando.EXITO, "Paciente modificado correctamente");
        else 
            return new ResultadoComando(TipoResultadoComando.ERROR, "El paciente que intenta modificar, no existe.");
    }

    public String getDescripcion()
    {
        return "Modificar Paciente";
    }

}
