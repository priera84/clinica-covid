package clinica.logica; 
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
/**
 * Clase que gestiona todas las operaciones sobre los usuarios del sistema.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */

public class ColeccionUsuario extends ColeccionPersona
{

    /**
     * Constructor de la clase
     */
    public ColeccionUsuario()
    {
        super();
    }

    /**
     * Función que da de baja a un usuario del sistema.
     * @param nombreUsuario Nombre del usuario que se desea dar de baja.
     * @return Booleano que indica si el usuario se ha dado de baja.
     */
    public Boolean bajaUsuario(String nombreUsuario)
    {
        if(listaPersonas.size() >= 0)
            for (Persona persona : listaPersonas)
            {
                if (persona instanceof Usuario)
                    if(((Usuario)persona).getNombreUsuario().equals(nombreUsuario))
                        return bajaPersona(persona.getDni());
            }

        return false;
    }

    /**
     * Esta función permite actualizar la dirección del usuario.
     * @param nombreUsuario Nombre de usuario que se desea actualizar.
     * @param direccionNew Nueva dirección asignada al usuario.
     * @return Booleano indicando si se ha realizado la actualización.
     */
    public Boolean actualizarUsuario(String nombreUsuario, String direccionNew)
    {
           if(listaPersonas.size() >= 0)
            for (Persona persona : listaPersonas)
            {
                if (persona instanceof Usuario)
                    if(((Usuario)persona).equals(nombreUsuario))
                        return actualizarPersona(persona.getDni(), direccionNew);
            }

        return false;
    }

    /**
     * Valida un usuario en el sistema.
     * @param nombreUsuario Nombre de usuario.
     * @param clave Clave del usuario.
     * @return objeto Usuario validado en el sistema.
     */
    public Usuario validarUsuario(String nombreUsuario,String clave)
    {
        if(listaPersonas.size() >= 0)
            for (Persona persona : listaPersonas)
            {
                if (persona instanceof Usuario)
                    if(((Usuario)persona).validarUsuario(nombreUsuario, clave))
                        return (Usuario)persona;
            }

        return null;
    }
}
