package clinica.logica;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una coleccion de personas.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class ColeccionPersona
{
    // instance variables - replace the example below with your own
    protected  List<Persona> listaPersonas;

    /**
     * Constructor 
     */
    public ColeccionPersona()
    {
        // initialise instance variables
        listaPersonas = new ArrayList<Persona>();
    }

    /**
     * Función que da de alta una persona en la colección.
     *
     * @param  persona Objeto persona para agregar a la colección.
     * @return    Si se ha agregado correctamente a la colección.
     */
    protected Boolean altaPersona(Persona persona)
    {
        if(getByDni(persona.getDni()) == null)
        {
            listaPersonas.add(persona);
            return true;
        }

        return false;
    }

    /**
     * Busca una persona en la colección usando el dni.
     * @param dni DNI de la persona.
     * @return Objeto persona encontrado.
     */
    public Persona getByDni(String dni)
    {
        if(listaPersonas.size() > 0)
        {
            for(Persona persona : listaPersonas)
            {
                if(persona.getDni().equals(dni))
                    return persona;
            }
        }

        return null;
    }

    /**
     * Función que da de baja a una persona en el sistema.
     * @param dni DNI de la persona a dar de baja.
     * @return Booleano indicando si se ha dado de baja a la persona.
     */
    protected Boolean bajaPersona(String dni)
    {
        if(listaPersonas.size() > 0)
        {
            Persona personaBorrar = getByDni(dni);
            if(personaBorrar != null)
            {           
                listaPersonas.remove(personaBorrar);
                return true;
            }
        }

        return false;
    }   

    /**
     * Esta función permite actualizar la dirección de la persona.
     * @param dni Dni de la persona que se desea actualizar.
     * @param direccionNew Nueva dirección que se desea asignar al usuario.
     * @return Booleano indicando si se ha realizado la actualización.
     */
    protected Boolean actualizarPersona(String dni, String direccionNew)
    {
        Persona persona = getByDni(dni);

        if(persona != null)
        {
            persona.setDireccion(direccionNew);
            return true;
        }
        return false;
    }    

    /**
     * Función que devuelve en un string la descripción de todas las personas de la colección.
     * @return String con la descripción de todas las personas de la colección.
     */
    public String getDescripcionPersonas()
    {
        if(listaPersonas.size() > 0)
        {
            StringBuilder sbPersonas = new StringBuilder();

            for(Persona persona: listaPersonas)
            {
                sbPersonas.append(persona.getDescripcion());
            }

            return sbPersonas.toString();
        }
        return null;
    }
}
