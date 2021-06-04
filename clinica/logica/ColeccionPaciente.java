package clinica.logica;

import java.time.LocalDate;
import java.util.*;
/**
 * Write a description of class ColeccionPaciente here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ColeccionPaciente extends ColeccionPersona
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class GestionUsuario
     */
    public ColeccionPaciente()
    {
        // initialise instance variables
        super();
    }

    public Boolean altaPaciente(Paciente paciente)
    {
        return altaPersona(paciente);
    }

    public Boolean bajaPaciente(String dni)
    {
        return bajaPersona(dni);
    }

    public Boolean modificarPaciente(String dni, String direccionNew)
    {
        return actualizarPersona(dni, direccionNew);
    }  

    public Boolean agregarPaciente(Paciente paciente)
    {
        return this.listaPersonas.add(paciente);
    }

    public Boolean addListaPacientes(ColeccionPaciente coleccionPaciente)
    {
        if(coleccionPaciente != null)
            return this.listaPersonas.addAll(coleccionPaciente.listaPersonas);
        else 
            return true;
    }

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
