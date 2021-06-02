package clinica.logica;

import java.time.LocalDateTime;
import java.util.*;
/**
 * Write a description of class ColeccionPrueba here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ColeccionPrueba
{
    private List<Prueba> listaPruebas;

    /**
     * Constructor for objects of class ColeccionPrueba
     */
    public ColeccionPrueba()
    {
        // initialise instance variables
        this.listaPruebas = new ArrayList<Prueba>();
    }

    public Boolean addPrueba(Prueba prueba)
    {
        return this.listaPruebas.add(prueba);
    }
    
    public void borrarPrueba(Prueba prueba)
    {
        if(listaPruebas.contains(prueba))
          listaPruebas.remove(prueba);
    }

    public Boolean existePCRMenos15dias()
    {
        if(listaPruebas.size() > 0)
            for (Prueba prueba: listaPruebas)
            {
                if((prueba instanceof PCR) && (prueba.diasTranscurridosPrueba() < 15))
                    return true;
            }

        return false;
    }

    public Boolean existeSerologicoMenos6Meses()
    {
        if(listaPruebas.size() > 0)
            for (Prueba prueba: listaPruebas)
            {
                if((prueba instanceof Serologico) && (prueba.mesesTranscurridosPrueba() < 6))
                    return true;
            }

        return false;
    }

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

    public ColeccionPaciente getPacientesAsignados()
    {

        if(listaPruebas.size() > 0)
        {   
            ColeccionPaciente listaPacientes = new ColeccionPaciente();
            for (Prueba prueba: listaPruebas)
            {
                Paciente paciente =prueba.getPacienteAsignado();
                if(paciente != null)
                {
                    listaPacientes.agregarPaciente(paciente);
                }
            }
        }           
        return null;
    }

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
}
