package clinica.logica;

import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 *  Clase que representa una colección de usuario enfermeros.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class ColeccionEnfermero extends ColeccionUsuario
{
    /**
     * Constructor
     */
    public ColeccionEnfermero()
    {
        super();
    }

    /**
     * Función que da de alta un enfermero en el sistema.
     * @param enfermero Objeto enfermero con los datos del usuario.
     * @return Boolean indicando si se ha dado de alta el usuario en el sistema.
     */
    public Boolean altaEnfermero(Enfermero enfermero)
    {
        return altaPersona(enfermero);
    }

    /**
     * Función que da de baja un enfermero en el sistema.
     * @param nombreUsuario Nombre de usuario del enfermero que se desea dar de baja.
     * @return Boolean indicando si se ha dado de baja el usuario en el sistema.
     */   
    public Boolean bajaEnfermero(String nombreUsuario)
    {
        return bajaUsuario(nombreUsuario);
    }

    /**
     * Función que modifica un enfermero en el sistema.
     * @param direccionNew Nueva dirección del usuario.
     * @param nombreUsuario Nombre de usuario a actualizar.
     * @return Boolean indicando si se ha modificado el usuario en el sistema.
     */
    public Boolean actualizarEnfermero(String nombreUsuario, String direccionNew)
    {
        return actualizarUsuario(nombreUsuario, direccionNew);
    }

    /**
     * Función que devuelve un enfermero disponible para realizar la prueba en la fechaHora indicada.
     * @param fechaHora Fecha hora en que se desea programar la prueba.
     * @return Objeto Enfermero asignado para la prueba.
     */
    public Enfermero obtenerEnfermeroDisponiblePrueba(LocalDateTime fechaHora)
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

    /**
     * Función que devuelve un enfermero disponible para realizar la vacunacion en las fechas indicadas.
     * @param fecha Fecha en que se desea programar la primera dosis.
     * @param fecha2dosis Fecha hora en que se desea programar la segunda dosis. Puede ser nulo.
     * @return Objeto Enfermero asignado para la vacunacion.
     */
    public Enfermero obtenerEnfermeroDisponibleVacunacion(LocalDate fecha,LocalDate fecha2dosis)
    {
        for(Persona persona : this.listaPersonas)
        {
            if(persona instanceof Enfermero)
            {
                if(((Enfermero)persona).disponibleParaVacunacion(fecha) && ((fecha2dosis == null) ||((Enfermero)persona).disponibleParaVacunacion(fecha2dosis)))
                    return (Enfermero)persona;                
            }
        }

        return null;
    }
}
