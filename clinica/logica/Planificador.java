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

    public Boolean programarVacunacion(LocalDate fechaInicioVacunacion, Paciente paciente)
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
        else  resultado = false;

        return resultado;        
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

    public Vacuna getVacuna(Paciente paciente)
    {
        //Obtenemos un numero aleatorio entre el 0 y 2
        Random rand = new Random();
        int opcion = rand.nextInt(3);

        switch(opcion)
        {
            case 0:
                return new Pfizer(paciente);
            case 1:
                return new Moderna(paciente);
            case 2:
                return new JohnsonAndJohnson(paciente);
        }

        return null;
    }
}
