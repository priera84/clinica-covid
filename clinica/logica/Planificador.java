package clinica.logica;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Random;
import java.time.LocalDate;

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
    private StockPrueba stockPrueba;
   
    /**
     * Constructor 
     * @param coleccionPaciente Objeto ColeccionPaciente con todos los pacientes del sistema.
     * @param coleccionTecnico Objeto ColeccionTecnico con todos los técnicos del sistema.
     * @param coleccionEnfermero Objeto ColeccionEnfermero con todos los enfermeros del sistema.
     * @param stockVacuna Objeto StockVacuna con el stock de vacunas del sistema.
     * @param stockPrueba Objeto StockPrueba con el stock de pruebas del sistema.
     */
    public Planificador(ColeccionPaciente coleccionPaciente, ColeccionTecnico coleccionTecnico, ColeccionEnfermero coleccionEnfermero, StockVacuna stockVacuna, StockPrueba stockPrueba)
    {
        // initialise instance variables
        this.coleccionPaciente = coleccionPaciente;
        this.coleccionTecnico = coleccionTecnico;
        this.coleccionEnfermero = coleccionEnfermero;
        this.stockVacuna = stockVacuna;
        this.stockPrueba = stockPrueba;
    }  

    /**
     * Busca un técnico disponible para asignar a una prueba.
     * @param fechaHoraPrueba Fecha y hora de la prueba que se desea programar.
     * @return Tecnico disponible para validar la prueba.
     */
    private Tecnico buscarTecnicoPrueba(LocalDateTime fechaHoraPrueba)
    {
        return this.coleccionTecnico.obtenerTecnicoDisponible(fechaHoraPrueba);        
    }

    /**
     * Busca un enfermero disponible para asignar a una prueba.
     * @param fechaHoraPrueba Fecha y hora de la prueba que se desea programar.
     * @return Enfermero disponible para validar la prueba.
     */
    private Enfermero buscarEnfermeroPrueba(LocalDateTime fechaHoraPrueba)
    {
        return this.coleccionEnfermero.obtenerEnfermeroDisponiblePrueba(fechaHoraPrueba);   
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
        {
            prueba.asignarTecnico(tecnico);
            tecnico.asignarPrueba(prueba);
        }
        else 
            resultado = false;

        if(resultado)
        {
            Enfermero enfermero = buscarEnfermeroPrueba(fechaHoraPrueba);
            if(enfermero != null)
            {
                prueba.asignarEnfermero(enfermero);
                enfermero.asignarPrueba(prueba);
            }
            else resultado = false;
        }

        if(resultado)
            prueba.setEstado(TipoEstado.PROGRAMADO);
        else
            prueba.desasignarPrueba();

        return resultado;            
    }

    public Boolean programarVacunacion(LocalDate fechaInicioVacunacion, Paciente paciente) throws Exception
    {
        // Comprobar que todos los pacientes prioritarios han sido vacunados, 
        // cuando se planifica un paciente no prioritario
        if(!this.coleccionPaciente.permitirVacunacionPaciente(paciente))
            return false;

        Boolean resultado = false;    
        Vacuna vacuna = null;

        if(paciente.dosisProgramadas() == 0)
        {
            //Vacuna asignada de forma aleatoria
            vacuna = getVacuna(paciente);           
                
            Enfermero enfermero = null;
            if(vacuna.getNumeroDosis() == 2)
            {
                Vacuna vacuna2Dosis = vacuna.getCopia();                
                //Programamos un enfermero, para toda la dosis (1 o 2 segun corresponda)
                LocalDate fechaSegundaDosis = fechaInicioVacunacion.plusDays(21);                
                enfermero = this.coleccionEnfermero.obtenerEnfermeroDisponibleVacunacion(fechaInicioVacunacion, fechaSegundaDosis);

                if(enfermero != null)
                {
                    //Asignamos primera dosis
                    vacuna.asignarEnfermero(enfermero);
                    paciente.asignarVacuna(vacuna);
                    enfermero.asignarVacuna(vacuna);
                    vacuna.setFechaHora(enfermero.getFechaHoraDia(fechaInicioVacunacion));                    
                    //asignamos segunda dosis
                    vacuna2Dosis.asignarEnfermero(enfermero);
                    paciente.asignarVacuna(vacuna2Dosis);
                    enfermero.asignarVacuna(vacuna2Dosis);
                    vacuna2Dosis.setFechaHora(enfermero.getFechaHoraDia(fechaSegundaDosis));                    

                    resultado = true;
                }                
                else 
                    resultado = false;
            }
            else
            {   
                enfermero = this.coleccionEnfermero.obtenerEnfermeroDisponibleVacunacion(fechaInicioVacunacion, null);
                if(enfermero != null)
                {
                    vacuna.asignarEnfermero(enfermero);
                    paciente.asignarVacuna(vacuna);
                    enfermero.asignarVacuna(vacuna);                    
                    vacuna.setFechaHora(enfermero.getFechaHoraDia(fechaInicioVacunacion));                             
                    resultado = true;
                }                
                else 
                    resultado = false;

            }
        }
        else  throw new Exception("El paciente ya tiene vacunación asignada.");

        return resultado;        
    }

    public Prueba getPrueba(TipoPrueba tipoPrueba, LocalDateTime fechaHora)
    {
        switch(tipoPrueba)
        {
            case PCR:
                if(this.stockPrueba.sacarPruebaTipo(TipoPrueba.PCR))
                    return new PCR(fechaHora);
                else 
                    return null;
            case SEROLOGICA:
                if(this.stockPrueba.sacarPruebaTipo(TipoPrueba.SEROLOGICA))
                    return new Serologico(fechaHora);
                else 
                    return null;
            case ANTIGENOS:
                if(this.stockPrueba.sacarPruebaTipo(TipoPrueba.ANTIGENOS))
                    return new Antigenos(fechaHora);
                else 
                    return null;
        }
        return null;
    }

    public Vacuna getVacuna(Paciente paciente) throws Exception
    {
        //Obtenemos un numero aleatorio entre el 0 y 2
        Random rand = new Random();
        int opcion = rand.nextInt(3);

        switch(opcion)
        {
            case 0:
                if(this.stockVacuna.sacarVacunaMarca(TipoMarcaVacuna.PFIZER))
                    return new Pfizer(paciente);
                else 
                    throw new Exception("No hay stock de vacuna PFIZER");
            case 1:
                if(this.stockVacuna.sacarVacunaMarca(TipoMarcaVacuna.MODERNA))
                    return new Moderna(paciente);
                else 
                    throw new Exception("No hay stock de vacuna MODERNA");
            case 2:
                if(this.stockVacuna.sacarVacunaMarca(TipoMarcaVacuna.JOHNSON_AND_JOHNSON))
                    return new JohnsonAndJohnson(paciente);
                else 
                    throw new Exception("No hay stock de vacuna Johnson and Johnson");
        }

        return null;
    }
}
