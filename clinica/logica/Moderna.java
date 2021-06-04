package clinica.logica;

import java.time.LocalDateTime;
/**
 * Write a description of class Moderna here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Moderna extends Vacuna
{
    /**
     * Constructor for objects of class Moderna
     */
    public Moderna(Paciente paciente)
    {
        super(paciente);
    }   
    
    public int getNumeroDosis()
    {
        return 2;
    }
    
    public Vacuna getCopia()
    {
        return new Moderna(this.getPacienteAsignado());        
    }
}
