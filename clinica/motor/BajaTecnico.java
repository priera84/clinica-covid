package clinica.motor;

import clinica.logica.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
/**
 * Comando que da de baja un tecnico.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class BajaTecnico extends Comando
{
    // instance variables - replace the example below with your own
    private ColeccionTecnico coleccionTecnico;

    /**
     * Constructor for objects of class BajaTecnico
     */
    public BajaTecnico(ColeccionTecnico coleccionTecnico)
    {
        // initialise instance variables
        super();
        this.parametros.put("nombreUsuario", new Parametro("nombreUsuario", "Nombre usuario del técnico"));
        this.coleccionTecnico = coleccionTecnico;
    }

    public ResultadoComando ejecutar()
    {
        if(coleccionTecnico.bajaTecnico(((Parametro<String>)parametros.get("nombreUsuario")).getValor()))
            return new ResultadoComando(TipoResultadoComando.EXITO, "Técnico dado de baja correctamente");
        else 
            return new ResultadoComando(TipoResultadoComando.ERROR, "El técnico que intenta dar de baja, no existe.");
    }

    public String getDescripcion()
    {
        return "Baja Técnico";
    }    
}
