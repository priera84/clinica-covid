package clinica.motor;

import clinica.logica.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

/**
 * Comando que modifica un enfermero.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class ModificacionEnfermero extends Comando
{
    private ColeccionEnfermero coleccionEnfermero;  

    /**
     * Constructor for objects of class ModificacionEnfermero
     */
    public ModificacionEnfermero(ColeccionEnfermero coleccionEnfermero)
    {
        // initialise instance variables
        super();
        this.parametros.put("nombreUsuario", new Parametro("nombreUsuario", "Nombre de usuario",null, null));
        this.parametros.put("direccionNew", new Parametro("direccionNew", "Nueva dirección", null, null));
        this.coleccionEnfermero = coleccionEnfermero;
    }

    public ResultadoComando ejecutar()
    {
        String nombreUsuario = ((Parametro<String>)parametros.get("nombreUsuario")).getValor();
        String direccionNew = ((Parametro<String>)parametros.get("direccionNew")).getValor();

        if(coleccionEnfermero.actualizarEnfermero(nombreUsuario, direccionNew))
            return new ResultadoComando(TipoResultadoComando.EXITO, "Enfermero modificado correctamente");
        else 
            return new ResultadoComando(TipoResultadoComando.ERROR, "El enfermero que intenta modificar, no existe.");
    }

    public String getDescripcion()
    {
        return "Modificar Enfermero";
    }    
}
