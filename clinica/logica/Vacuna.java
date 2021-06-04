package clinica.logica;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.ZoneId;
import  java.time.temporal.*;
import java.util.Locale;
/**
 * Clase que representa una Vacuna de COVID
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public abstract class Vacuna
{
    private LocalDateTime fechaHora;    
    private Enfermero enfermeroAsignado;
    private Paciente pacienteAsignado;
    private TipoEstado estado;

    /**
     * Constructor
     */
    public Vacuna()
    {
        this.estado = TipoEstado.CREADO;
    }

    /**
     * Constructor
     * @param paciente Paciente que se le asigna a la vacuna.
     */
    public Vacuna(Paciente paciente)
    {
        this.estado = TipoEstado.CREADO;

        this.asignarPaciente(paciente);
    }

    /**
     * Procedimiento que asinga un paciente a la vacuna.
     * @param paciente Paciente que se le asigna a la vacuna.
     */
    public void asignarPaciente(Paciente paciente)
    {
        this.pacienteAsignado = paciente;
    }

    /**
     * Procedimiento que asinga un enfermero a la vacuna.
     * @param paciente Paciente que se le asigna a la vacuna.
     */
    public void asignarEnfermero(Enfermero enfermero)
    {
        this.enfermeroAsignado = enfermero;
    }

    /**
     * Devuelve la fecha hora asignada a la vacuna.
     * @return Fecha hora asignada a la vacunación.
     */
    public LocalDateTime getFechaHora()
    {
        return this.fechaHora;
    }

    /**
     * Devuelve el estado de la vacunación.
     * @return Estado de la vacunación.
     */
    public TipoEstado getEstado()
    {
        return this.estado;
    }    

    /**
     * Asigna el estado de la vacunación.
     * @param estado TipoEstado 
     */
    public void setEstado(TipoEstado estado)
    {
        this.estado = estado;
    }

    /**
     * Asigna la fechaHora de la vacunación.
     * @param fechaHora Fecha hora de la vacunación.
     */
    public void setFechaHora(LocalDateTime fechaHora)
    {
        this.fechaHora = fechaHora;
        this.setEstado(TipoEstado.PROGRAMADO);
    }

    /**
     * Devuvele si la vacunación se realiza en la misma semana que la fecha hora pasada por parámetro.
     * @param fechaHora Fecha hora que se desea realizar la vacunación.
     * @return Booleano indicando si la vacunación se realiza en la misma semana que la fecha pasada por parámetro.
     */
    public Boolean perteneceASemana(LocalDateTime fechaHora)
    {
        TemporalField diaSemana = WeekFields.of(Locale.FRANCE).dayOfWeek();
        LocalDateTime fechaInicioSemana = fechaHora.with(diaSemana, 1); 
        LocalDateTime fechaFinSemana = fechaHora.with(diaSemana, 7); 
        return (this.fechaHora.isBefore(fechaFinSemana) && this.fechaHora.isAfter(fechaInicioSemana));        
    }

    /**
     * Devuelve una cadena que representa los datos del objeto vacuna.
     * @return String que contiene la descripción de la vacuna.
     */
    public String getDescripcion()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Fecha vacunacion: ");
        sb.append(this.fechaHora.toString());
        sb.append("\nTipo vacuna:");        
        if(this instanceof Pfizer)
            sb.append(" Pfizer");
        if(this instanceof Moderna)
            sb.append(" Moderna");
        if(this instanceof JohnsonAndJohnson)
            sb.append(" Johnson and Johnson");

        sb.append("\nEstado vacuna:" + this.estado.toString());

        return sb.toString();
    }

    /**
     * Devuelve el paciente asignado a la vacuna.
     * @return Objeto paciente asignado a la vacuna.
     */
    public Paciente getPacienteAsignado()
    {
        return pacienteAsignado;
    }

    /**
     * Devuelve el enfermero asignado a la vacuna.
     * @return Objeto enfermero asignado a la vacuna.
     */
    public Enfermero getEnfermeroAsignado()
    {
        return this.enfermeroAsignado;
    }

    /**
     * Desasigna la vacuna del paciente y del enfermero.
     */
    public void desasignarVacuna()
    {
        if(this.pacienteAsignado != null)
        {
            this.pacienteAsignado.desasignarVacuna(this);
            this.pacienteAsignado = null;
        }

        if(this.enfermeroAsignado != null)
        {
            this.enfermeroAsignado.desasignarVacuna(this);
            this.enfermeroAsignado = null;
        }       
    }

    /**
     * Devuelve el número de dosis para considerar completa la inmunidad del paciente.
     * @return Número entero que representa el número de dosis para considerar completa la inmunidad del paciente.
     */
    public abstract int getNumeroDosis();

    /**
     * Función que devuelve una copia del objeto.
     * @return Objeto vacuna del mismo tipo con el mismo paciente asignado.
     */
    public abstract Vacuna getCopia();

}
