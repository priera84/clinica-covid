package clinica.logica;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * Clase que representa una colección de vacunas.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class ColeccionVacuna
{
    private List<Vacuna> listaVacunas;

    /**
     * Constructor
     */
    public ColeccionVacuna()
    {
        // initialise instance variables
        this.listaVacunas = new ArrayList<Vacuna>();
    }

    /**
     * Agrega una vacuna a la colección.
     * @param vacuna Vacuna que se desea agregar a la colección.
     * @return Booleano que indica si se ha agregado la vacuna a la colección.
     */
    public Boolean addVacuna(Vacuna vacuna)
    {
        return this.listaVacunas.add(vacuna);
    } 

    /**
     * Borra una vacuna a la colección.
     * @param vacuna Vacuna que se desea borrar a la colección.
     * @return Booleano que indica si se ha borrado la vacuna de la colección.
     */
    public void borrarVacuna(Vacuna vacuna)
    {
        if(listaVacunas.contains(vacuna))
            listaVacunas.remove(vacuna);
    }

    /**
     * Devuelve el total de vacunas de la colección.
     * @return Número de vacunas de la colección.
     */
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

    /**
     * Devuelve un string con la descripción de la vacuna.
     * @return String con la descripción de la vacuna.
     */
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

    /**
     * Devuelve una colección de pacientes asignados a todas las vacunas de la colección.
     * @return ColeccionPaciente de todos los pacientes asignados a todas las vacunas de la colección.
     */
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
                    listaPacientes.altaPaciente(paciente);                        
                }
            }
            return listaPacientes;
        }           
        return null;
    }

    /**
     * Devuelve el total de vacunas en la fecha.
     * @param fecha Fecha en que se desea calcular.
     * @return Número de vacunas en la colección con la misma fecha que la del parámetro.
     */
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

    /**
     * Devuelve la primera vacuna con estado PROGRAMADO, es decir, pendiente de aplicar al paciente.
     * @param fecha Fecha que debe tener la vacuna que se desea obtener.
     * @return Vacuna vacuna pendiente de aplicar al paciente.
     */
    public Vacuna getVacunaPendiente(LocalDate fecha)
    {
        if(listaVacunas.size() > 0)
        {
            for (Vacuna vacuna: listaVacunas)
            {
                if (vacuna.getFechaHora().toLocalDate().equals(fecha) && vacuna.getEstado() == TipoEstado.PROGRAMADO)
                    return vacuna;
            }
        }
        return null;
    }
}
