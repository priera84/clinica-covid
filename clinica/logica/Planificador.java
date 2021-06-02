package clinica.logica;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

/**
 * Esta clase, realiza una planificación de la vacunación y de la realización de pruebas diagnósticas.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class Planificador
{
    private ColeccionPaciente coleccionPaciente;
    private ColeccionTecnico coleccionTecnico;
    private ColeccionEnfermero coleccionEnfermero;
    private StockVacuna stockVacuna;

    private void planificarVacunacionMayores65()
    {

    }

    private void planificarVacunacionMenores65()
    {

    }

    /**
     * Constructor for objects of class Planificador
     */
    public Planificador(ColeccionPaciente coleccionPaciente, ColeccionTecnico coleccionTecnico, ColeccionEnfermero coleccionEnfermero)
    {
        // initialise instance variables
        this.coleccionPaciente = coleccionPaciente;
        this.coleccionTecnico = coleccionTecnico;
        this.coleccionEnfermero = coleccionEnfermero;

    }

    public void planificarVacunacion()
    {
        planificarVacunacionMayores65();
        planificarVacunacionMenores65();        
    }

    private Tecnico buscarTecnicoPrueba(LocalDateTime fechaHoraPrueba)
    {
        return this.coleccionTecnico.obtenerTecnicoDisponible(fechaHoraPrueba);        
    }

    private Enfermero buscarEnfermeroPrueba(LocalDateTime fechaHoraPrueba)
    {
        return this.coleccionEnfermero.obtenerEnfermeroDisponible(fechaHoraPrueba);   
    }

    /**
     * Cuando llega un paciente a la clinica para realizarse la prueba que le indiquen en su centro de salud, 
     * este procedimiento, le asignará un enfermero y un técnico disponible.
     **/
    public Boolean programarPrueba(Prueba prueba)
    {
        Boolean resultado = true;

        if(prueba == null)
            return false;            

        LocalDateTime fechaHoraPrueba = prueba.getFechaHora();

        Tecnico tecnico = buscarTecnicoPrueba(fechaHoraPrueba);
        if(tecnico != null)
            prueba.asignarTecnico(tecnico);
        else 
            resultado = false;

        if(resultado)
        {
            Enfermero enfermero = buscarEnfermeroPrueba(fechaHoraPrueba);
            if(enfermero != null)
                prueba.asignarEnfermero(enfermero);
            else resultado = false;
        }
        
        if(resultado)
            prueba.setEstado(TipoEstado.PROGRAMADO);
        else
            prueba.desasignarPrueba();

        return resultado;            
    }

    public Boolean programarVacunacion(Vacuna vacuna)
    {
        return false;    
    }

    public Prueba getPrueba(TipoPrueba tipoPrueba, LocalDateTime fechaHora, Paciente paciente)
    {
        switch(tipoPrueba)
        {
            case PCR:
                return new PCR(fechaHora, paciente);
            case SEROLOGICA:
                return new Serologico(fechaHora, paciente);
            case ANTIGENOS:
                return new Antigenos(fechaHora, paciente);
        }
        return null;
    }
}
