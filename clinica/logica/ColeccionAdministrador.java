package clinica.logica;
import java.time.LocalDate;

/**
 * Clase que representa una colección de usuario administradores.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class ColeccionAdministrador extends ColeccionUsuario
{

    /**
     * Constructor for objects of class ColeccionAdministrador
     */
    public ColeccionAdministrador()
    {
        // initialise instance variables

    }

    /**
     * Función que da de alta un administrador en el sistema.
     * @param administrador Objeto administrador con los datos del usuario.
     * @return Boolean indicando si se ha dado de alta el usuario en el sistema.
     */
    public Boolean altaAdministrador(Administrador administrador)
    {
        return altaPersona(administrador);
    }

    /**
     * Función que da de baja un administrador en el sistema.
     * @param nombreUsuario Nombre de usuario del administrador que se desea dar de baja.
     * @return Boolean indicando si se ha dado de baja el usuario en el sistema.
     */   
    public Boolean bajaAdministrador(String nombreUsuario)
    {
        return bajaUsuario(nombreUsuario);
    }

    /**
     * Función que modifica un administrador en el sistema.
     * @param nombreUsuario Nombre de usuario a actualizar.
     * @param direccionNew Nueva dirección del usuario.
     * @return Boolean indicando si se ha modificado el usuario en el sistema.
     */
    public Boolean actualizarAdministrador(String nombreUsuario, String direccionNew)
    {
        return actualizarUsuario(nombreUsuario, direccionNew);
    }
}
