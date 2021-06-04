package clinica.logica;

import java.time.LocalDateTime;
/**
 * Write a description of class JohnsonJohnson here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class JohnsonAndJohnson extends Vacuna
{
    /**
     * Constructor for objects of class JohnsonJohnson
     */
    public JohnsonAndJohnson(Paciente paciente)
    {
        super(paciente);
    }   

    public int getNumeroDosis()
    {
        return 1;
    }

    public Vacuna getCopia()
    {
        return new JohnsonAndJohnson(this.getPacienteAsignado());        
    }
}
