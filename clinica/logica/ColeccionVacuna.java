package clinica.logica;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * Write a description of class ColeccionVacuna here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ColeccionVacuna
{
    private List<Vacuna> listaVacunas;

    /**
     * Constructor for objects of class ColeccionVacuna
     */
    public ColeccionVacuna()
    {
        // initialise instance variables
        this.listaVacunas = new ArrayList<Vacuna>();
    }

    public Boolean addVacuna(Vacuna vacuna)
    {
        return this.listaVacunas.add(vacuna);
    } 

    public void borrarVacuna(Vacuna vacuna)
    {
        if(listaVacunas.contains(vacuna))
            listaVacunas.remove(vacuna);
    }

    public int getNumeroVacunas()
    {
        return this.listaVacunas.size();
    }

    /**
     * Devuelve si se requiere segunda dosis
     */
    public Boolean requiereSegundaDosis()
    {
        return ((this.listaVacunas.size() == 1) && (!(this.listaVacunas.get(0) instanceof JohnsonAndJohnson)));
    }

    public String getDescripcion()
    {

        if(listaVacunas.size() > 0)
        {   
            StringBuilder sb = new StringBuilder();            
            Enfermero enfermero = null;
            for (Vacuna vacuna: listaVacunas)
            {
                sb.append("\n" + vacuna.getDescripcion() + "\n");
                enfermero = vacuna.getEnfermeroAsignado();                
            }
            if(enfermero != null)
                sb.append("Enfermero asignado: \n" + enfermero.getDescripcion() + "\n");
            return sb.toString();
        }           
        return null;
    }

    public ColeccionPaciente getPacientesAsignados()
    {

        if(listaVacunas.size() > 0)
        {   
            ColeccionPaciente listaPacientes = new ColeccionPaciente();
            for (Vacuna vacuna: listaVacunas)
            {
                Paciente paciente = vacuna.getPacienteAsignado();
                if(paciente != null)
                {
                    if(listaPacientes.getByDni(paciente.getDni()) == null)
                        listaPacientes.agregarPaciente(paciente);
                }
            }
            return listaPacientes;
        }           
        return null;
    }

    public int totalVacunasDia(LocalDate fecha)
    {
        int contador = 0;
        if(listaVacunas.size() > 0)
            for (Vacuna vacuna: listaVacunas)
            {
                if (vacuna.getFechaHora().toLocalDate().equals(fecha))
                    contador++;
            }

        return contador;
    }
}
