package clinica.logica;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Clase que representa una colección de usuario tecnico.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class ColeccionTecnico extends ColeccionUsuario
{
    // instance variables - replace the example below with your own

    /**
     * Constructor
     */
    public ColeccionTecnico()
    {
        super();
    }

    /**
     * Función que da de alta un técnico en el sistema.
     * @param tecnico Objeto tecnico con los datos del usuario.
     * @return Boolean indicando si se ha dado de alta el usuario en el sistema.
     */
    public Boolean altaTecnico(Tecnico tecnico)
    {
        return altaPersona(tecnico);
    }

    /**
     * Función que da de baja un tecnico en el sistema.
     * @param nombreUsuario Nombre de usuario del tecnico que se desea dar de baja.
     * @return Boolean indicando si se ha dado de baja el usuario en el sistema.
     */  
    public Boolean bajaTecnico(String nombreUsuario)
    {
        return bajaUsuario(nombreUsuario);
    }

    /**
     * Función que modifica un tecnico en el sistema.
     * @param nombreUsuario Nombre de usuario a actualizar.
     * @param direccionNew Nueva dirección del usuario.
     * @return Boolean indicando si se ha modificado el usuario en el sistema.
     */
    public Boolean actualizarTecnico(String nombreUsuario, String direccionNew)
    {
        return actualizarUsuario(nombreUsuario, direccionNew);
    }

    /**
     * Función que devuelve un tecnico disponible para validar la prueba en la fechaHora indicada.
     * @param fechaHora Fecha hora en que se desea programar la prueba.
     * @return Objeto Tecnico asignado para la prueba.
     */
    public Tecnico obtenerTecnicoDisponible(LocalDateTime fechaHora)
    {
        for(Persona persona : this.listaPersonas)
        {
            if(persona instanceof Tecnico)
            {
                if(((Tecnico)persona).disponibleParaPrueba(fechaHora))
                    return (Tecnico)persona;                
            }
        }

        return null;
    }
}
