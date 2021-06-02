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
    
    public Vacuna(LocalDateTime fechaHora)
    {
        this.fechaHora = fechaHora;
        this.estado = TipoEstado.PROGRAMADO;
    }

    public void asignarPaciente(Paciente paciente)
    {
        if(paciente.asignarVacuna(this))
            this.pacienteAsignado = paciente;
    }

    public void asignarEnfermero(Enfermero enfermero)
    {
        this.enfermeroAsignado = enfermeroAsignado;
    }

    public float diasTranscurridosVacunacion()
    {
        LocalDate ahora = LocalDate.now(ZoneId.of("Europe/Madrid"));
        return java.time.temporal.ChronoUnit.DAYS.between(this.fechaHora, ahora );
    }

    public float mesesTranscurridosVacunacion()
    {
        LocalDate ahora = LocalDate.now(ZoneId.of("Europe/Madrid"));
        return java.time.temporal.ChronoUnit.MONTHS.between(this.fechaHora, ahora );
    }

    public LocalDateTime getFechaHora()
    {
        return this.fechaHora;
    }
    
    public TipoEstado getEstado()
    {
        return this.estado;
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
}
