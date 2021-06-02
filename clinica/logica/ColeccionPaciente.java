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
        return this.listaPersonas.addAll(coleccionPaciente.listaPersonas);
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
}
