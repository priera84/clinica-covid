package clinica.motor;

import clinica.logica.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Write a description of class AltaTecnico here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AltaTecnico extends Comando
{
    // instance variables - replace the example below with your own
    private ColeccionTecnico coleccionTecnico;
    /**
     * Constructor for objects of class AltaTecnico
     */
    public AltaTecnico(ColeccionTecnico coleccionTecnico)
    {
        // initialise instance variables
        super();
        this.parametros.put("nombreUsuario", new Parametro<String>("nombreUsuario", "Nombre de usuario"));
        this.parametros.put("clave",new Parametro<String>("clave", "clave"));
        this.parametros.put("nombre",new Parametro<String>("nombre", "Nombre"));
        this.parametros.put("apellidos",new Parametro<String>("apellidos", "Apellidos"));
        this.parametros.put("fechaNacimiento",new Parametro<LocalDate>("fechaNacimiento", "Fecha de Nacimiento (dd/MM/yyyy)", null, x -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(x, formatter);
            
        }));
        this.parametros.put("dni",new Parametro<String>("dni", "DNI"));
        this.parametros.put("genero",new Parametro<TipoGenero>("genero", "Genero (MASCULINO o FEMENINO)", null, x-> {
            return TipoGenero.valueOf(x);
        }));
        this.parametros.put("direccion",new Parametro<String>("direccion", "Dirección"));
        this.coleccionTecnico = coleccionTecnico;
    }

    public ResultadoComando ejecutar()
    {
        Tecnico tecnico = new Tecnico(((Parametro<String>)parametros.get("nombreUsuario")).getValor(),
                                      ((Parametro<String>)parametros.get("clave")).getValor(),
                                      ((Parametro<String>)parametros.get("nombre")).getValor(),
                                      ((Parametro<String>)parametros.get("apellidos")).getValor(),
                                      ((Parametro<LocalDate>)parametros.get("fechaNacimiento")).getValor(),
                                      ((Parametro<String>)parametros.get("dni")).getValor(),
                                      ((Parametro<TipoGenero>)parametros.get("genero")).getValor(),
                                      ((Parametro<String>)parametros.get("direccion")).getValor());
                                      
        if(coleccionTecnico.altaTecnico(tecnico))
            return new ResultadoComando(TipoResultadoComando.EXITO, "Técnico dado de alta correctamente");
        else 
            return new ResultadoComando(TipoResultadoComando.ERROR, "El técnico que intenta dar de alta, ya existe.");
    }

    public String getDescripcion()
    {
        return "Alta Técnico";
    }   
}
