package clinica.motor;

import clinica.logica.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase que representa un comando que permite programar una prueba para un paciente.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AsignarPruebaPaciente extends Comando
{
    private ColeccionPaciente coleccionPaciente;
    private Planificador planificador;

    /**
     * Constructor for objects of class AsignarVacunacionAEnfermero
     */
    public AsignarPruebaPaciente(ColeccionPaciente coleccionPaciente, Planificador planificador)
    {
        // initialise instance variables
        super();
        this.parametros.put("dni", new Parametro<String>("dni", "DNI del paciente"));
        this.parametros.put("fechaHora", new Parametro<LocalDateTime>("fechaHora", "Fecha Hora prueba dd/MM/yyyy HH:mm", LocalDateTime.now(), x ->{
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    return LocalDateTime.parse(x, formatter);
                }));
        this.parametros.put("tipoPrueba", new Parametro<TipoPrueba>("tipoPrueba", "Tipo prueba diagnostica a realizar (PCR, SEROLOGICA, ANTIGENOS)", null, x ->{
                    return TipoPrueba.valueOf(x);
                }));        

        this.coleccionPaciente = coleccionPaciente;
        this.planificador = planificador;
    }

    public ResultadoComando ejecutar()
    {
        String dni = ((Parametro<String>)parametros.get("dni")).getValor();
        LocalDateTime fechaHora = ((Parametro<LocalDateTime>)parametros.get("fechaHora")).getValor();
        TipoPrueba tipoPrueba = ((Parametro<TipoPrueba>)parametros.get("tipoPrueba")).getValor();
        
        Paciente paciente = (Paciente)coleccionPaciente.getByDni(dni);
        
        if (paciente != null)
        {
            Prueba prueba = planificador.getPrueba(tipoPrueba, fechaHora, paciente);
            paciente.asignarPrueba(prueba);
            
            if(planificador.programarPrueba(prueba))
            {
                return new ResultadoComando(TipoResultadoComando.EXITO, "Prueba programada:\n" + prueba.getDescripcion());
            }
            else
            {                
                return new ResultadoComando(TipoResultadoComando.ERROR, "No hay enfermeros y/o técnicos disponibles para realizar la prueba");
            }
        }
        else
        {
            return new ResultadoComando(TipoResultadoComando.ERROR, "No existe el paciente con dni: " + dni);
        }

        
    }

    public String getDescripcion()
    {
        return "Programar prueba a paciente";
    }
}
