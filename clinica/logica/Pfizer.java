package clinica.logica;

import java.time.LocalDateTime;
/**
 * Representa una vacuna Pfizer.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class Pfizer extends Vacuna
{   
    /**
     * Constructor 
     * @param paciente Objeto del paciente asignado a la vacuna.
     */
    public Pfizer(Paciente paciente)
    {
        super(paciente);
    }   

    /**
     * Devuelve el número de dosis para considerar completa la inmunidad del paciente.
     * @return Número entero que representa el número de dosis para considerar completa la inmunidad del paciente.
     */
    public int getNumeroDosis()
    {
        return 2;
    }

    /**
     * Función que devuelve una copia del objeto.
     * @return Objeto vacuna del mismo tipo con el mismo paciente asignado.
     */
    public Vacuna getCopia()
    {
        return new Pfizer(this.getPacienteAsignado());        
    }
}
