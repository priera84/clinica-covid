package clinica.motor;

import clinica.logica.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
/**
 * Write a description of class ModificacionTecnico here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ModificacionTecnico extends Comando
{
    // instance variables - replace the example below with your own
    private ColeccionTecnico coleccionTecnico;
    /**
     * Constructor for objects of class ModificacionTecnico
     */
    public ModificacionTecnico(ColeccionTecnico coleccionTecnico)
    {
        // initialise instance variables
        super();
        this.parametros.put("nombreUsuario", new Parametro("nombreUsuario", "Nombre de usuario",null, null));
        this.parametros.put("direccionNew", new Parametro("direccionNew", "Nueva dirección", null, null));
        this.coleccionTecnico = coleccionTecnico;
    }

    public ResultadoComando ejecutar()
    {
        String nombreUsuario = ((Parametro<String>)parametros.get("nombreUsuario")).getValor();
        String direccionNew = ((Parametro<String>)parametros.get("direccionNew")).getValor();

        if(coleccionTecnico.actualizarTecnico(nombreUsuario,direccionNew))
            return new ResultadoComando(TipoResultadoComando.EXITO, "Técnico modificado correctamente");
        else 
            return new ResultadoComando(TipoResultadoComando.ERROR, "El técnico que intenta modificar, no existe.");
    }

    public String getDescripcion()
    {
        return "Modificar Técnico";
    }  
}
