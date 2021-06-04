package clinica.logica; 

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * Clase que represente un usuario Enfermero.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class Enfermero extends Usuario
{
    private static int MAX_VACUNAS_DIA = 10;
    private static int HORA_INICIO_JORNADA = 9;
    private ColeccionPrueba coleccionPrueba;
    private ColeccionVacuna coleccionVacuna;
    private HashMap<LocalDate, Integer> vacunasPorDia;

    /**
     * Constructor de la clase
     * @param  nombreUsuario Nombre del usuario
     * @param  clave Clave de acceso al sistema del usuario.
     * @param  nombre   Nombre de la persona.
     * @param  apellidos   Apellidos de la persona.
     * @param  fechaNacimiento Fecha de nacimiento de la persona.
     * @param  dni   Documento Nacional de Identidad de la persona.
     * @param  genero   Género de la persona.
     * @param  direccion Dirección de la persona.
     * @param  telefono Teléfono de la persona.
     */ 
    public Enfermero(String nombreUsuario, String clave, String nombre, String apellidos, LocalDate fechaNacimiento, String dni, TipoGenero genero, String direccion, String telefono)
    {
        super(nombreUsuario, clave, TipoUsuario.ENFERMERO, nombre, apellidos, fechaNacimiento, dni, genero, direccion, telefono);
        this.coleccionPrueba = new ColeccionPrueba();
        this.coleccionVacuna = new ColeccionVacuna();
        this.vacunasPorDia = new HashMap<LocalDate, Integer>(); 
    }

    /**
     * Función que calcula si un enfermero esta disponible en la fechaHora solicitada,
     * dado que no puede hacer más de cinco pruebas a la semana.
     * @param fechaHora fecha y hora en la que se desea realizar la prueba.
     * @return devuelve booleano indicando si el enfermero está disponible.
     */
    public Boolean disponibleParaPrueba(LocalDateTime fechaHora)
    {
        return this.coleccionPrueba.getNumeroPruebasDeSemana(fechaHora) < 5;
    }

    /**
     * Método que desasigna al enfermero una prueba asignada previamente.
     */
    public void desasignarPrueba(Prueba prueba)
    {
        this.coleccionPrueba.borrarPrueba(prueba);
    }

    /**
     * Método que desasigna al enfermero una vacunación asignada previamente.
     * @param vacuna Objeto vacuna que se va a desasignar del enfermero.
     */
    public void desasignarVacuna(Vacuna vacuna)
    {
        this.coleccionVacuna.borrarVacuna(vacuna);
    }

    /**
     * Método que asigna al enfermero una prueba.
     * @param prueba Objeto prueba que se va a asignar a el enfermero.
     * @return Booleano indicando si ha podido asignarse la prueba.
     */
    public Boolean asignarPrueba(Prueba prueba)
    {
        if (this.disponibleParaPrueba(prueba.getFechaHora()))
            return this.coleccionPrueba.addPrueba(prueba);

        return false;
    }

    /**
     * Método que asigna al enfermero una vacuna.
     * @param vacuna Objeto vacuna que se va a asignar a el enfermero.
     * @return Booleano indicando si ha podido asignarse la vacuna.
     */
    public Boolean asignarVacuna(Vacuna vacuna)
    {
        return this.coleccionVacuna.addVacuna(vacuna);        
    }

    /**
     * Función que devuelve los pacientes asignados al enfermero, tanto para pruebas diagnósticas como para vacunación.
     * @return ColeccionPaciente con todos los pacientes asignados.
     */
    public ColeccionPaciente getPacientesAsignados()
    {
        ColeccionPaciente pacientesAsignados = coleccionPrueba.getPacientesAsignados();
        if (pacientesAsignados != null)
            pacientesAsignados.addListaPacientes(coleccionVacuna.getPacientesAsignados());
        else
            pacientesAsignados = coleccionVacuna.getPacientesAsignados();

        return pacientesAsignados;
    }

    /**
     * Función que indica si el enfermero tiene disponibilidad en el día para realizar una vacunación.
     * @param fecha Fecha en la que se desea realizar la vacunación por parte del enfermero.
     * @return Booleano indicando si el enfermero está disponible para la vacunación.
     */
    public Boolean disponibleParaVacunacion(LocalDate fecha)
    {
        Integer vacunasDia = this.vacunasPorDia.get(fecha);
        if(vacunasDia != null)
            return (vacunasDia < MAX_VACUNAS_DIA);
        else
        {
            //Si no hay datos para ese día, es que el enfermero está disponible.
            return true;
        }
    }

    /**
     * Función que calcula la hora en el día indicado que el enfermero realizará la vacunación.
     * @param fecha Fecha en la que se ha programado la vacunación por parte del enfermero.
     * @return LocalDateTime Fecha y hora en que se programa la vacunación.
     */
    public LocalDateTime getFechaHoraDia(LocalDate fecha)
    {
        //Bloquea un turno del dia
        Integer vacunasDia = this.vacunasPorDia.get(fecha);
        if(vacunasDia != null)        
        {
            this.vacunasPorDia.put(fecha, ++vacunasDia);
            return fecha.atTime(HORA_INICIO_JORNADA + vacunasDia, 0);
        }
        else
        {
            this.vacunasPorDia.put(fecha, 1);
            return fecha.atTime(HORA_INICIO_JORNADA, 0);
        }
    }

}
