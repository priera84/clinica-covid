package clinica.logica;

import java.time.LocalDate;
import java.util.*;
/**
 * Clase que representa una colección de pacientes del sistema.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class ColeccionPaciente extends ColeccionPersona
{
    // instance variables - replace the example below with your own

    /**
     * Constructor 
     */
    public ColeccionPaciente()
    {
        // initialise instance variables
        super();
    }

    /**
     * Función que da de alta un paciente en el sistema.
     * @param paciente Objeto paciente con los datos del paciente.
     * @return Boolean indicando si se ha dado de alta el paciente en el sistema.
     */
    public Boolean altaPaciente(Paciente paciente)
    {
        return altaPersona(paciente);
    }

    /**
     * Función que da de baja un paciente en el sistema.
     * @param dni DNI del paciente que se quiere dar de baja.
     * @return Boolean indicando si se ha dado de baja el paciente en el sistema.
     */
    public Boolean bajaPaciente(String dni)
    {
        return bajaPersona(dni);
    }

    /**
     * Función que modifica un paciente en el sistema.
     * @param dni DNI del paciente que se quiere dar de baja.
     * @param direccionNew Nueva dirección asignada al paciente.
     * @return Boolean indicando si se ha modificado el paciente en el sistema.
     */
    public Boolean modificarPaciente(String dni, String direccionNew)
    {
        return actualizarPersona(dni, direccionNew);
    }  

    /**
     * Agrega una lista de pacientes, a la lista actual.
     * @param coleccionPaciente colección que se desea agregar a la colección actual de pacientes.
     * @return Booleano indicando que se ha podido agregar la lista de pacientes.
     */ 
    public Boolean addListaPacientes(ColeccionPaciente coleccionPaciente)
    {
        if(coleccionPaciente != null)
            return this.listaPersonas.addAll(coleccionPaciente.listaPersonas);
        else 
            return true;
    }

    /**
     * Devuelve descripción de todos los pacientes de la colección.
     * @return Cadena con descripción de todos los pacientes de la colección.
     */
    public String getListaPacientes()
    {
        if(listaPersonas.size() > 0)
        {
            StringBuilder sb = new StringBuilder();
            for(Persona persona: listaPersonas)
            {
                sb.append(persona.getDescripcion());

                if(persona instanceof Paciente)
                {
                    ((Paciente)persona).getDetallePruebas();
                    ((Paciente)persona).getDetalleVacunacion();
                }

            }
            return sb.toString();
        }

        return null;
    }

    /**
     * Valida si se puede vacunar un paciente. 
     * @param paciente paciente que se desea vacunar.
     * @return Booleano indicando si se permite vacunar al paciente.
     */
    public Boolean permitirVacunacionPaciente(Paciente paciente)
    {
        //Si es del grupo prioritario y necesita primera o segunda dosis, se permite vacunar
        if (paciente.grupoPrioritario() && paciente.requiereProgramarVacuna())
            return true;
        else
        {
            if(listaPersonas.size() > 0)
            {
                for(Persona persona: listaPersonas)
                {
                    //si existe una persona del grupo prioritario que requiera vacuna, no se permite vacunar
                    if(persona instanceof Paciente && ((Paciente)persona != paciente))
                    {
                        if(((Paciente)persona).grupoPrioritario() &&((Paciente)persona).requiereProgramarVacuna())
                            return false;
                    }

                }                
            }
            return true;
        }
    }
}
