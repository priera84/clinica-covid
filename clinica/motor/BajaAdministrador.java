package clinica.motor;

import clinica.logica.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
/**
 * Comando que da de baja un administrador.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class BajaAdministrador extends Comando
{
    // instance variables - replace the example below with your own
    private ColeccionAdministrador coleccionAdministrador;

    /**
     * Constructor for objects of class BajaAdministrador
     */
    public BajaAdministrador(ColeccionAdministrador coleccionAdministrador)
    {
        // initialise instance variables
        super();
        this.parametros.put("nombreUsuario", new Parametro("nombreUsuario", "Nombre usuario del administrador"));
        this.coleccionAdministrador = coleccionAdministrador;
    }

    public ResultadoComando ejecutar()
    {
        if(coleccionAdministrador.bajaAdministrador(((Parametro<String>)parametros.get("nombreUsuario")).getValor()))
            return new ResultadoComando(TipoResultadoComando.EXITO, "Administrador dado de baja correctamente");
        else 
            return new ResultadoComando(TipoResultadoComando.ERROR, "El administrador que intenta dar de baja, no existe.");
    }

    public String getDescripcion()
    {
        return "Baja Administrador";
    }    
}
