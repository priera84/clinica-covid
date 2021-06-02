package clinica.logica;
import java.time.LocalDate;

/**
 * Write a description of class ColeccionAdministrador here.
 *
 * @author (your name)
 * @version (a version number or a date)
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

    public Boolean altaAdministrador(Administrador administrador)
    {
        return altaPersona(administrador);
    }

    public Boolean bajaAdministrador(String nombreUsuario)
    {
        return bajaUsuario(nombreUsuario);
    }



    public Boolean actualizarAdministrador(String nombreUsuario, String direccionNew)
    {
        return actualizarUsuario(nombreUsuario, direccionNew);
    }
}
