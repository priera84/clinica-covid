package clinica.logica;

import java.time.LocalDateTime;
/**
 * Write a description of class Pfizer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Pfizer extends Vacuna
{   
    /**
     * Constructor for objects of class Pfizer
     */
    public Pfizer(Paciente paciente)
    {
        super(paciente);
    }   

    public int getNumeroDosis()
    {
        return 2;
    }

    public Vacuna getCopia()
    {
        return new Pfizer(this.getPacienteAsignado());        
    }
}
