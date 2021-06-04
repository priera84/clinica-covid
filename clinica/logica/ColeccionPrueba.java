package clinica.logica;

import java.time.LocalDateTime;
import java.util.*;
import java.time.LocalDate;

/**
 * Representa una colección de pruebas.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class ColeccionPrueba
{
    private List<Prueba> listaPruebas;

    /**
     * Constructor 
     */
    public ColeccionPrueba()
    {
        // initialise instance variables
        this.listaPruebas = new ArrayList<Prueba>();
    }

    /**
     * Agrega una prueba a la colección.
     * @param prueba Objeto prueba que se agrega a la colección.
     * @return Booelano indicando si se ha agregado a la colección.
     */
    public Boolean addPrueba(Prueba prueba)
    {
        return this.listaPruebas.add(prueba);
    }

    /**
     * Borra una prueba a la colección.
     * @param prueba Objeto prueba que se borra de la colección.
     * @return Booelano indicando si se ha borrado a la colección.
     */
    public void borrarPrueba(Prueba prueba)
    {
        if(listaPruebas.contains(prueba))
            listaPruebas.remove(prueba);
    }

    /**
     * Indica si existe una prueba PCR realizada en los últimos 15 días.
     * @param fechaHora Fecha hora desde la que se quieren contar los dias transcurridos.
     * @return Booelano indicando si existe una prueba PCR realizada en los últimos 15 días.
     */
    public Boolean existePCRMenos15dias(LocalDateTime fechaHora)
    {
        if(listaPruebas.size() > 0)
            for (Prueba prueba: listaPruebas)
            {
                if((prueba instanceof PCR) && (prueba.diasTranscurridosPrueba(fechaHora) < 15))
                    return true;
            }

        return false;
    }

    /**
     * Indica si existe una prueba Serológica realizada en los últimos 6 meses.
     * @param fechaHora Fecha hora desde la que se quieren contar los meses transcurridos.
     * @return Booelano indicando si existe una prueba Serológica realizada en los últimos 6 meses.
     */
    public Boolean existeSerologicoMenos6Meses(LocalDateTime fechaHora)
    {
        if(listaPruebas.size() > 0)
            for (Prueba prueba: listaPruebas)
            {
                if((prueba instanceof Serologico) && (prueba.mesesTranscurridosPrueba(fechaHora) < 6))
                    return true;
            }

        return false;
    }

    /**
     * Función que devuelve el numero de pruebas realizadas en la semana de la fecha que se pasa por parámetro.
     * @param fechaHora Fecha hora en la que se calcula el numero de pruebas de la semana.
     * @return Integer con el número de pruebas.
     */
    public int getNumeroPruebasDeSemana(LocalDateTime fechaHora)
    {
        int totalPruebasSemana = 0;        
        for(Prueba prueba: listaPruebas)
        {
            if(prueba.perteneceASemana(fechaHora))
                totalPruebasSemana++;
        }

        return totalPruebasSemana;
    }

    /**
     * Devuelve una colección con todos los pacientes asignados a las pruebas de la colección.
     * @return ColeccionPaciente con todos los pacientes asignados a las pruebas de la colección.
     */
    public ColeccionPaciente getPacientesAsignados()
    {

        if(listaPruebas.size() > 0)
        {   
            ColeccionPaciente listaPacientes = new ColeccionPaciente();
            for (Prueba prueba: listaPruebas)
            {
                Paciente paciente = prueba.getPacienteAsignado();
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
     * Funcion que devuelve la descripcion de las pruebas de la colección.
     * @return String con la descripcion de las pruebas de la colección.
     */
    public String getDescripcion()
    {

        if(listaPruebas.size() > 0)
        {   
            StringBuilder sb = new StringBuilder();            
            for (Prueba prueba: listaPruebas)
            {
                sb.append(prueba.getDescripcion() + "\n");
            }
            return sb.toString();
        }           
        return null;
    }

    /**
     * Devuelve una prueba de la colección pendiente de actualizar su resultado.
     * @return Prueba que se ha realizado pero que tiene pendiente de actualizar su resultado.
     */
    public Prueba getPruebaPendienteValidacion()
    {
        if(listaPruebas.size() > 0)
        {
            for (Prueba prueba: listaPruebas)
            {
                if (prueba.getEstado() == TipoEstado.REALIZADO)
                    return prueba;
            }
        }
        return null;
    }

    /**
     * Devuelve la prueba programada en la fecha indicada por parámetro, y que se está pendiente de realizar.
     * @return Prueba que esta pendiente de realizarse en la fecha indicada.
     */
    public Prueba getPruebaPendiente(LocalDate fecha)
    {
        if(listaPruebas.size() > 0)
        {
            for (Prueba prueba: listaPruebas)
            {
                if (prueba.getFechaHora().toLocalDate().equals(fecha) && prueba.getEstado() == TipoEstado.PROGRAMADO)
                    return prueba;
            }
        }
        return null;
    }
}
