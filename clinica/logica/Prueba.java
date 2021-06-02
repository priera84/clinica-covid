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
     * Constructor for objects of class Prueba
     */
    public Prueba(LocalDateTime fechaHora, Paciente paciente)
    {
        this.fechaHora = fechaHora;
        this.estado = TipoEstado.CREADO;

        this.asignarPaciente(paciente);
    }

    public void asignarPaciente(Paciente paciente)
    {
        if(paciente.asignarPrueba(this))
            this.pacienteAsignado = paciente;
    }

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

    public void asignarEnfermero(Enfermero enfermero)
    {
        if(enfermero.asignarPrueba(this))
        this.enfermeroAsignado = enfermeroAsignado;
    }

    public void asignarTecnico(Tecnico tecnico)
    {
        this.tecnicoAsignado = tecnico;
    }

    public float diasTranscurridosPrueba()
    {
        LocalDate ahora = LocalDate.now(ZoneId.of("Europe/Madrid"));
        return java.time.temporal.ChronoUnit.DAYS.between(this.fechaHora, ahora );
    }

    public float mesesTranscurridosPrueba()
    {
        LocalDate ahora = LocalDate.now(ZoneId.of("Europe/Madrid"));
        return java.time.temporal.ChronoUnit.MONTHS.between(this.fechaHora, ahora );
    }

    public abstract Boolean positivoCovid(); 

    public LocalDateTime getFechaHora()
    {
        return this.fechaHora;
    }

    public Boolean perteneceASemana(LocalDateTime fechaHora)
    {
        TemporalField diaSemana = WeekFields.of(Locale.FRANCE).dayOfWeek();
        LocalDateTime fechaInicioSemana = fechaHora.with(diaSemana, 1); 
        LocalDateTime fechaFinSemana = fechaHora.with(diaSemana, 7); 
        return (this.fechaHora.isBefore(fechaFinSemana) && this.fechaHora.isAfter(fechaInicioSemana));        
    }

    public Paciente getPacienteAsignado()
    {
        return pacienteAsignado;
    }

    public TipoEstado getEstado()
    {
        return estado;
    }

    public void setEstado(TipoEstado estado)
    {
        this.estado = estado;
    }

    public String getDescripcion()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Fecha prueba: ");
        sb.append(this.fechaHora.toString());
        sb.append("\nTipo prueba:");        
        if(this instanceof PCR)
            sb.append(" PRC");
        if(this instanceof Serologico)
            sb.append(" Test serologico");
        if(this instanceof Antigenos)
            sb.append(" Test antigenos");

        sb.append("\nResultado: ");            
        if (this.positivoCovid())
            sb.append("POSITIVO");  
        else 
            sb.append("NEGATIVO");  

        return sb.toString();
    }
}
