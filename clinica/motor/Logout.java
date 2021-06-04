package clinica.motor;

import clinica.logica.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
/**
 * Comando que permite cerrar sesión en el sistema.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class Logout extends Comando
{
    // instance variables - replace the example below with your own
    /**
     * Constructor for objects of class Logout
     */
    public Logout()
    {
        super();        
    }

    public ResultadoComando ejecutar()
    {
        Clinica clinica = Clinica.getInstancia();

        if(!clinica.cerrarSesion())
            return new ResultadoComando(TipoResultadoComando.ERROR, "Error al cerrar sesión");
        else
        {
            return new ResultadoComando(TipoResultadoComando.NUEVA_COLECCION_COMANDO, null, new Sesion());            
        }        
    }

    public String getDescripcion()
    {
        return "Cerrar sesión en el sistema";
    }   
}
