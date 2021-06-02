package clinica.motor;

import clinica.logica.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Write a description of class AltaPaciente here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AltaPaciente extends Comando
{
    ColeccionPaciente coleccionPaciente;

    /**
     * Constructor for objects of class AltaPaciente
     */
    public AltaPaciente(ColeccionPaciente coleccionPaciente)
    {
        // initialise instance variables
        super();
        this.parametros.put("nombre",new Parametro<String>("nombre", "Nombre"));
        this.parametros.put("apellidos",new Parametro<String>("apellidos", "Apellidos"));
        this.parametros.put("fechaNacimiento",new Parametro<LocalDate>("fechaNacimiento", "Fecha de Nacimiento", null, x -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    return LocalDate.parse(x, formatter);

                }));
        this.parametros.put("dni",new Parametro<String>("dni", "DNI"));
        this.parametros.put("genero",new Parametro<TipoGenero>("genero", "Genero (MASCULINO o FEMENINO)", null, x-> {
                    return TipoGenero.valueOf(x);
                }));
        this.parametros.put("direccion",new Parametro<String>("direccion", "Dirección"));
        this.parametros.put("pacienteCovid", new Parametro<Boolean>("pacienteCovid", "Paciente COVID? (true o false)", false, x-> {
                    return Boolean.getBoolean(x);
                }));
        this.coleccionPaciente = coleccionPaciente;
    }

    public ResultadoComando ejecutar()
    {
        Paciente paciente = new Paciente(((Parametro<String>)parametros.get("nombre")).getValor(),
                ((Parametro<String>)parametros.get("apellidos")).getValor(),
                ((Parametro<LocalDate>)parametros.get("fechaNacimiento")).getValor(),
                ((Parametro<String>)parametros.get("dni")).getValor(),
                ((Parametro<TipoGenero>)parametros.get("genero")).getValor(),
                ((Parametro<String>)parametros.get("direccion")).getValor(),
                ((Parametro<Boolean>)parametros.get("pacienteCovid")).getValor());

        if(coleccionPaciente.altaPaciente(paciente))
            return new ResultadoComando(TipoResultadoComando.EXITO, "Paciente dado de alta correctamente");
        else 
            return new ResultadoComando(TipoResultadoComando.ERROR, "El paciente que intenta dar de alta, ya existe.");
    }

    public String getDescripcion()
    {
        return "Alta Paciente";
    }
}
