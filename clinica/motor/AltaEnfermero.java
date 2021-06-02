package clinica.motor;

import clinica.logica.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Write a description of class AltaEnfermero here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AltaEnfermero extends Comando
{
    ColeccionEnfermero coleccionEnfermero;
    /**
     * Constructor for objects of class AltaEnfermero
     */
    public AltaEnfermero(ColeccionEnfermero coleccionEnfermero)
    {
        // initialise instance variables
        super();
        
        this.parametros.put("nombreUsuario", new Parametro<String>("nombreUsuario", "Nombre de usuario"));
        this.parametros.put("clave",new Parametro<String>("clave", "clave"));
        this.parametros.put("nombre",new Parametro<String>("nombre", "Nombre"));
        this.parametros.put("apellidos",new Parametro<String>("apellidos", "Apellidos"));
        this.parametros.put("fechaNacimiento",new Parametro<LocalDate>("fechaNacimiento", "Fecha de Nacimiento (d/MM/yyyy)", null, x -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            return LocalDate.parse(x, formatter);
            
        }));
        this.parametros.put("dni",new Parametro<String>("dni", "DNI"));
        this.parametros.put("genero",new Parametro<TipoGenero>("genero", "Genero (MASCULINO o FEMENINO)", null, x-> {
            return TipoGenero.valueOf(x);
        }));
        this.parametros.put("direccion",new Parametro<String>("direccion", "Dirección"));
        
        
        this.coleccionEnfermero = coleccionEnfermero;
    }

    public ResultadoComando ejecutar()
    {
        Enfermero enfermero = new Enfermero(((Parametro<String>)parametros.get("nombreUsuario")).getValor(),
                                      ((Parametro<String>)parametros.get("clave")).getValor(),
                                      ((Parametro<String>)parametros.get("nombre")).getValor(),
                                      ((Parametro<String>)parametros.get("apellidos")).getValor(),
                                      ((Parametro<LocalDate>)parametros.get("fechaNacimiento")).getValor(),
                                      ((Parametro<String>)parametros.get("dni")).getValor(),
                                      ((Parametro<TipoGenero>)parametros.get("genero")).getValor(),
                                      ((Parametro<String>)parametros.get("direccion")).getValor());
        if(coleccionEnfermero.altaEnfermero(enfermero))
            return new ResultadoComando(TipoResultadoComando.EXITO, "Enfermero dado de alta correctamente");
        else 
            return new ResultadoComando(TipoResultadoComando.ERROR, "El enfermero que intenta dar de alta, ya existe.");
    }

    public String getDescripcion()
    {
        return "Alta Enfermero";
    }   
}
