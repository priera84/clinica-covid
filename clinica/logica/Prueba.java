package clinica.logica;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.ZoneId;
import  java.time.temporal.*;
import java.util.Locale;
/**
 * Clase que representa una prueba de COVID
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public abstract class Prueba
{
    private LocalDateTime fechaHora;    
    private Enfermero enfermeroAsignado;
    private Tecnico tecnicoAsignado;
    private Paciente pacienteAsignado;
    private TipoEstado estado;

    /**
     * Constructor
     * @param fechaHora Fecha hora en que se programa la prueba.
     * @param paciente Paciente al que se le asigna la prueba.
     */
    public Prueba(LocalDateTime fechaHora)
    {
        this.fechaHora = fechaHora;
        this.estado = TipoEstado.CREADO;        
    }

    /**
     * Procedimiento que asigna una prueba al paciente.
     * @param paciente Paciente al que se le asigna la prueba.
     */
    public void asignarPaciente(Paciente paciente)
    {
        this.pacienteAsignado = paciente;
    }

    /**
     * Procedimiento que libera la prueba
     */
    public void desasignarPrueba()
    {
        if(this.pacienteAsignado != null)
        {
            this.pacienteAsignado.desasignarPrueba(this);
            this.pacienteAsignado = null;
        }

        if(this.enfermeroAsignado != null)
        {
            this.enfermeroAsignado.desasignarPrueba(this);
            this.enfermeroAsignado = null;
        }

        if(this.tecnicoAsignado != null)
        {
            this.tecnicoAsignado.desasignarPrueba(this);
            this.tecnicoAsignado = null;
        }

    }

    /**
     * Procedimiento que asigna un enfermero a la prueba.
     * @param enfermero Enfermero al que se le asigna la prueba.
     */
    public void asignarEnfermero(Enfermero enfermero)
    {
        if(enfermero.asignarPrueba(this))
            this.enfermeroAsignado = enfermeroAsignado;
    }

    /**
     * Procedimiento que asigna un técnico a la prueba.
     * @param tecnico Técnico al que se le asigna la prueba.
     */
    public void asignarTecnico(Tecnico tecnico)
    {
        this.tecnicoAsignado = tecnico;
    }

    /**
     * Función que devuelve los días transcurridos desde la fecha de la prueba.
     * @param fechaHora Fecha hora desde la que se quieren contar los dias transcurridos.
     * @return Nº de dias transcurridos desde la fecha de la prueba.
     */
    public float diasTranscurridosPrueba(LocalDateTime fechaHora)
    {
        return java.time.temporal.ChronoUnit.DAYS.between(this.fechaHora.toLocalDate(), fechaHora.toLocalDate());
    }

    /**
     * Función que devuelve los meses transcurridos desde la fecha de la prueba.
     * @param fechaHora Fecha hora desde la que se quieren contar los dias transcurridos.    
     * @return Nº de meses transcurridos desde la fecha de la prueba.
     */
    public float mesesTranscurridosPrueba(LocalDateTime fechaHora)
    {
        return java.time.temporal.ChronoUnit.MONTHS.between(this.fechaHora.toLocalDate(), fechaHora.toLocalDate());
    }

    /**
     * Función abstracta que devuelve si la prueba ha dado positivo en COVID.
     * @return Booleano que devuelve si la prueba ha dado positivo en COVID.
     */
    public abstract Boolean positivoCovid(); 

    /**
     * Devuelve el campo fechaHora.
     * @return fechahora.
     */
    public LocalDateTime getFechaHora()
    {
        return this.fechaHora;
    }

    /**
     * Función que devuelve si la prueba se encuentra en la misma semana que la fecha pasada por parámetro.
     * @param fechahora Fecha y hora en la que se desea saber si la prueba pertenece a la misma semana.
     * @return Booleano indicando si la prueba pertenece a la misma semana que la fecha pasada por parámetro.
     */
    public Boolean perteneceASemana(LocalDateTime fechaHora)
    {
        TemporalField diaSemana = WeekFields.of(Locale.FRANCE).dayOfWeek();
        LocalDateTime fechaInicioSemana = fechaHora.with(diaSemana, 1); 
        LocalDateTime fechaFinSemana = fechaHora.with(diaSemana, 7); 
        return (this.fechaHora.isBefore(fechaFinSemana) && this.fechaHora.isAfter(fechaInicioSemana));        
    }

    /**
     * Devuelve el paciente asignado a la prueba.
     * @return Objeto paciente asignado a la prueba.
     */
    public Paciente getPacienteAsignado()
    {
        return pacienteAsignado;
    }

    /**
     * Devuelve el estado en que se encuentra la prueba.
     * @return TipoEstado en el que se encuentra la prueba.
     */
    public TipoEstado getEstado()
    {
        return estado;
    }

    /**
     * Asigna el estado de la prueba.
     */
    public void setEstado(TipoEstado estado)
    {
        this.estado = estado;
    }

    /**
     * Devuelve la descripcion de la prueba.
     * @return Cadena con al descripción de la prueba.
     */
    public String getDescripcion()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\nFecha prueba: ");
        sb.append(this.fechaHora.toString());
        sb.append("\nTipo prueba:");        
        if(this instanceof PCR)
            sb.append(" PCR");
        if(this instanceof Serologico)
            sb.append(" Test serologico");
        if(this instanceof Antigenos)
            sb.append(" Test antigenos");

        sb.append("\nEstado: " + estado.toString());

        if(estado == TipoEstado.DIAGNOSTICADO)
        {
            sb.append("\nResultado: ");            
            if (this.positivoCovid())
                sb.append("POSITIVO");  
            else 
                sb.append("NEGATIVO");  
        }
        return sb.toString();
    }
}
