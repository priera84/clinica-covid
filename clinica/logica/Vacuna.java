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
     * Constructor for objects of class Prueba
     */
    public Vacuna()
    {
        this.estado = TipoEstado.CREADO;
    }

    public Vacuna(Paciente paciente)
    {
        this.estado = TipoEstado.CREADO;

        this.asignarPaciente(paciente);
    }

    public void asignarPaciente(Paciente paciente)
    {
        this.pacienteAsignado = paciente;
    }

    public void asignarEnfermero(Enfermero enfermero)
    {
        this.enfermeroAsignado = enfermero;
    }

    public float diasTranscurridosVacunacion()
    {
        LocalDate ahora = LocalDate.now(ZoneId.of("Europe/Madrid"));
        return java.time.temporal.ChronoUnit.DAYS.between(this.fechaHora.toLocalDate(), ahora );
    }

    public float mesesTranscurridosVacunacion()
    {
        LocalDate ahora = LocalDate.now(ZoneId.of("Europe/Madrid"));
        return java.time.temporal.ChronoUnit.MONTHS.between(this.fechaHora.toLocalDate(), ahora );
    }

    public LocalDateTime getFechaHora()
    {
        return this.fechaHora;
    }

    public TipoEstado getEstado()
    {
        return this.estado;
    }    

    public void setEstado(TipoEstado estado)
    {
        this.estado = estado;
    }
    
    public void setFechaHora(LocalDateTime fechaHora)
    {
        this.fechaHora = fechaHora;
        this.setEstado(TipoEstado.PROGRAMADO);
    }

    public Boolean perteneceASemana(LocalDateTime fechaHora)
    {
        TemporalField diaSemana = WeekFields.of(Locale.FRANCE).dayOfWeek();
        LocalDateTime fechaInicioSemana = fechaHora.with(diaSemana, 1); 
        LocalDateTime fechaFinSemana = fechaHora.with(diaSemana, 7); 
        return (this.fechaHora.isBefore(fechaFinSemana) && this.fechaHora.isAfter(fechaInicioSemana));        
    }

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

        return sb.toString();
    }

    public Paciente getPacienteAsignado()
    {
        return pacienteAsignado;
    }

    public Enfermero getEnfermeroAsignado()
    {
        return this.enfermeroAsignado;
    }
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
    
    public abstract int getNumeroDosis();
    
    public abstract Vacuna getCopia();
    
}
