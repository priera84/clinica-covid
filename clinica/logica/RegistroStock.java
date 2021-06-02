package clinica.logica;

import java.time.LocalDate;
/**
 * Write a description of class RegistroVacuna here.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public abstract class RegistroStock
{
    private LocalDate fechaRegistro;
    private int unidadesRegistradas;    
    private int unidadesDisponibles;
    /**
     * Constructor for objects of class RegistroVacuna
     */
    public RegistroStock(LocalDate fechaRegistro, int unidadesRegistradas)
    {
        this.fechaRegistro = fechaRegistro;
        this.unidadesRegistradas = unidadesRegistradas;
        this.unidadesDisponibles = unidadesRegistradas;
    }     

    public Boolean retirarUnidad()
    {
        if(unidadesDisponibles > 0)
        {
            unidadesDisponibles--;
            return true;
        }
        else
            return false;
    }

    public int getUnidadesDisponibles()
    {
        return this.unidadesDisponibles;
    }
    
    public int getUnidadesRegistradas()
    {
        return this.unidadesRegistradas;
    }
    
    
}
