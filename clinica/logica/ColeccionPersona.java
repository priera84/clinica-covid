package clinica.logica;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a description of class ColeccionPersona here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ColeccionPersona
{
    // instance variables - replace the example below with your own
    protected  List<Persona> listaPersonas;

    /**
     * Constructor for objects of class ColeccionPersona
     */
    public ColeccionPersona()
    {
        // initialise instance variables
        listaPersonas = new ArrayList<Persona>();
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
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
