package clinica.logica;

import java.time.LocalDateTime;
/**
 * Representa una vacuna Moderna.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class Moderna extends Vacuna
{
    /**
     * Constructor 
     * @param paciente Objeto del paciente asignado a la vacuna.
     */
    public Moderna(Paciente paciente)
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
        return new Moderna(this.getPacienteAsignado());        
    }
}
