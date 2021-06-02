package clinica.logica;

import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * Write a description of class ColeccionEnfermero here.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class ColeccionEnfermero extends ColeccionUsuario
{
    /**
     * Constructor for objects of class ColeccionEnfermero
     */
    public ColeccionEnfermero()
    {
        super();
    }

    public Boolean altaEnfermero(Enfermero enfermero)
    {
        return altaPersona(enfermero);
    }

    public Boolean bajaEnfermero(String nombreUsuario)
    {
        return bajaUsuario(nombreUsuario);
    }

    public Boolean actualizarEnfermero(String nombreUsuario, String direccionNew)
    {
        return actualizarUsuario(nombreUsuario, direccionNew);
    }

    public Enfermero obtenerEnfermeroDisponible(LocalDateTime fechaHora)
    {
        for(Persona persona : this.listaPersonas)
        {
            if(persona instanceof Enfermero)
            {
                if(((Enfermero)persona).disponibleParaPrueba(fechaHora))
                    return (Enfermero)persona;                
            }
        }

        return null;
    }

    public Enfermero obtenerEnfermeroDisponibleVacunacion(LocalDateTime fechaHora)
    {
        for(Persona persona : this.listaPersonas)
        {
            if(persona instanceof Enfermero)
            {
                return (Enfermero)persona;                
            }
        }

        return null;
    }
}
