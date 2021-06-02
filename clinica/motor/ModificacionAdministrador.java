package clinica.motor;

import clinica.logica.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
/**
 * Write a description of class ModificacionAdministrador here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ModificacionAdministrador extends Comando
{
    // instance variables - replace the example below with your own
    private ColeccionAdministrador coleccionAdministrador;
    /**
     * Constructor for objects of class ModificacionTecnico
     */
    public ModificacionAdministrador(ColeccionAdministrador coleccionAdministrador)
    {
        // initialise instance variables
        super();
        this.parametros.put("nombreUsuario", new Parametro("nombreUsuario", "Nombre de usuario",null, null));
        this.parametros.put("direccionNew", new Parametro("direccionNew", "Nueva dirección", null, null));
        this.coleccionAdministrador = coleccionAdministrador;
    }

    public ResultadoComando ejecutar()
    {
        String nombreUsuario = ((Parametro<String>)parametros.get("nombreUsuario")).getValor();
        String direccionNew = ((Parametro<String>)parametros.get("direccionNew")).getValor();

        if(coleccionAdministrador.actualizarAdministrador(nombreUsuario,direccionNew))
            return new ResultadoComando(TipoResultadoComando.EXITO, "Administrador modificado correctamente");
        else 
            return new ResultadoComando(TipoResultadoComando.ERROR, "El administrador que intenta modificar, no existe.");
    }

    public String getDescripcion()
    {
        return "Modificar Administrador";
    }  
}
