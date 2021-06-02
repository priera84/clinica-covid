package clinica.motor;

import clinica.logica.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
/**
 * Write a description of class BajaEnfermero here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BajaEnfermero extends Comando
{
    ColeccionEnfermero coleccionEnfermero;
    /**
     * Constructor for objects of class AltaEnfermero
     */
    public BajaEnfermero(ColeccionEnfermero coleccionEnfermero)
    {
        // initialise instance variables
        super();
        this.parametros.put("nombreUsuario", new Parametro("nombreUsuario", "Nombre de usuario del enfermero"));      
        this.coleccionEnfermero = coleccionEnfermero;
    }

    public ResultadoComando ejecutar()
    {
        String nombreUsuario =((Parametro<String>)parametros.get("nombreUsuario")).getValor();
        if(coleccionEnfermero.bajaEnfermero(nombreUsuario))
            return new ResultadoComando(TipoResultadoComando.EXITO, "Enfermero dado de baja correctamente");
        else 
            return new ResultadoComando(TipoResultadoComando.ERROR, "El enfermero que intenta dar de baja, no existe.");
    }

    public String getDescripcion()
    {
        return "Baja Enfermero";
    }
}
