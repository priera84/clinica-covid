package clinica.motor;

import clinica.logica.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
/**
 * Comando que lista personas.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class VisualizarDatosPersonas extends Comando
{
    // instance variables - replace the example below with your own
    private ColeccionPersona coleccionPersona;
    private String descripcion;
    /**
     * Constructor for objects of class VisualizarDatosPersonas
     */
    public VisualizarDatosPersonas(ColeccionPersona coleccionPersona, String descripcion)
    {
        // initialise instance variables
        super();
        this.descripcion = descripcion;        
        this.coleccionPersona = coleccionPersona;
    }

    public ResultadoComando ejecutar()
    {
        String datosPersonas = coleccionPersona.getDescripcionPersonas();
        if(datosPersonas != null)
            return new ResultadoComando(TipoResultadoComando.EXITO, datosPersonas);
        else 
            return new ResultadoComando(TipoResultadoComando.ERROR, "No hay datos de personas registradas en el sistema");
    }

    public String getDescripcion()
    {
        return descripcion;
    } 
}
