package clinica.logica; 

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Clase que represente un Paciente.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class Paciente extends Persona
{
    // instance variables - replace the example below with your own
    private Boolean pacienteCovid;  
    private Boolean confinado;

    private ColeccionPrueba coleccionPrueba;
    private ColeccionVacuna coleccionVacuna;

    /**
     * Constructor de la clase
     * @param  nombre   Nombre de la persona.
     * @param  apellidos   Apellidos de la persona.
     * @param  fechaNacimiento Fecha de nacimiento de la persona.
     * @param  dni   Documento Nacional de Identidad de la persona.
     * @param  genero   Género de la persona.
     * @param  direccion Dirección de la persona.
     * @param  pacienteCovid Indica si el paciente tiene covid. 
     * @param  telefono Teléfono de la persona.
     */ 
    public Paciente(String nombre, String apellidos, LocalDate fechaNacimiento, String dni, TipoGenero genero, String direccion, Boolean pacienteCovid, String telefono)
    {
        super(nombre, apellidos, fechaNacimiento, dni, genero, direccion, telefono);
        this.pacienteCovid = false;        
        this.coleccionPrueba = new ColeccionPrueba();
        this.coleccionVacuna = new ColeccionVacuna();
    }

    /**
     * Función que genera una descripción de la vacuna asignada al paciente.
     * @return Cadena que contiene la descripción de la vacuna.
     */
    public String getDescripcionVacuna()
    {
        return this.coleccionVacuna.getDescripcion();
    }

    /**
     * Devuelve si el paciente está confinado.
     * @return Booleano indicando si el paciente está confinado por una prueba positiva de COVID.
     */
    public Boolean getConfinado()
    {
        return this.confinado;
    }

    /**
     * Función que valida que un paciente puede programar una prueba de COVID.
     * @param fechaHora Fecha hora en la que se desea programar la prueba.
     * @return Booleano indicando si un paciente puede programar una prueba COVID.
     */
    private Boolean validarPrueba(Prueba prueba, LocalDateTime fechaHora)
    {
        if(prueba instanceof Antigenos)
            return true;

        if(prueba instanceof PCR)
            return !coleccionPrueba.existePCRMenos15dias(fechaHora);

        if(prueba instanceof Serologico)
            return !coleccionPrueba.existeSerologicoMenos6Meses(fechaHora);

        return false;    
    }

    /**
     * Asigna una prueba al paciente.
     * @param prueba Objeto Prueba que se asignará al paciente.
     * @param fechaHora Fecha hora en la que se desea programar la prueba.
     * @return Booleano indicando si la prueba ha pasado la validación y puede asignarse al paciente.
     */
    public Boolean asignarPrueba(Prueba prueba, LocalDateTime fechaHora)
    {
        if(validarPrueba(prueba, fechaHora))
        {
            prueba.asignarPaciente(this);
            return coleccionPrueba.addPrueba(prueba);
        }
        else 
            return false;
    }

    /**
     * Desasigna una prueba al paciente.
     * @param prueba Objeto Prueba que se desasignará al paciente.
     */
    public void desasignarPrueba(Prueba prueba)
    {
        prueba.desasignarPrueba();
        coleccionPrueba.borrarPrueba(prueba);
    }

    /**
     * Desasigna una vacuna al paciente.
     * @param vacuna Objeto Vacuna que se desasignará al paciente.
     */
    public void desasignarVacuna(Vacuna vacuna)
    {
        coleccionVacuna.borrarVacuna(vacuna);
    }

    /**
     * Función que define si un paciente requiere programar una vacunación adicional.
     * @return Booleano indicando si paciente requiere programar una vacunación adicional.
     */
    public Boolean requiereVacuna()
    {
        //No tiene vacunas asignadas
        if(coleccionVacuna.getNumeroVacunas() == 0)
            return true;

        //Tiene una vacuna y requiere una segunda dosis 
        if(coleccionVacuna.getNumeroVacunas() == 1 && coleccionVacuna.requiereSegundaDosis())
            return true;
        else 
            return false;         
    }

    /**
     * Función que indica si el paciente pertenece al grupo prioritario de vacunación.
     * @return Booleano que indica si el paciente pertenece al grupo prioritario de vacunación.
     */
    public Boolean grupoPrioritario()
    {
        return (this.getEdadPersona() >= 65);
    }

    /**
     * Función que devuelve el número de dosis que tiene programadas el paciente.
     * @return integer con el número de dosis programadas.
     */
    public int dosisProgramadas()
    {
        return coleccionVacuna.getNumeroVacunas(); 
    }

    /**
     * Indica si requiere programar una vacuna, en base a las vacunas asignadas actualmente.
     * @return Booleano que indica si paciente requiere programar una vacuna.
     */
    public Boolean requiereProgramarVacuna()
    {
        //No tiene vacunas asignadas
        if(coleccionVacuna.getNumeroVacunas() == 0)
            return true;

        //Tiene una vacuna, requiere una segunda dosis 
        if(coleccionVacuna.getNumeroVacunas() == 1 && coleccionVacuna.requiereSegundaDosis())
            return true;
        else 
            return false;         
    }

    /**
     * Función que asigna una vacuna al paciente.
     * @return Booleano que indica si ha podido realizarse la asignación.
     */
    public Boolean asignarVacuna(Vacuna vacuna)
    {
        if(requiereVacuna())
        {
            vacuna.asignarPaciente(this);
            return coleccionVacuna.addVacuna(vacuna);
        }
        else 
            return false;
    }

    /**
     * Función que devuelve la descripción de las pruebas asignadas.
     * @return Cadena con la  descripción de las pruebas asignadas.
     */
    public String getDetallePruebas()
    {
        return this.coleccionPrueba.getDescripcion();
    }

    /**
     * Función que devuelve la descripción de las vacunas asignadas.
     * @return Cadena con la  descripción de las vacunas asignadas.
     */
    public String getDetalleVacunacion()
    {
        return this.coleccionVacuna.getDescripcion();
    }

    /**
     * Función que devuelve la descripción de los datos del paciente.
     * @return Cadena con la  descripción de los datos del paciente.
     */
    public String getDescripcion()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\nPaciente: \n" + super.getDescripcion() + "\n");
        String vacunacion = getDetalleVacunacion();
        if(vacunacion != null)
            sb.append("\n" + vacunacion);
        String pruebas = getDetallePruebas(); 
        if(pruebas != null)
            sb.append("\n" + pruebas);

        return sb.toString();

    }

    /**
     * Función que marca como realizada una de las dosis pendientes de aplicar.
     * @param fecha Fecha programada de la vacuna.
     * @return Booleano indicando si se ha encontrado dosis para marcar como aplicada.
     */
    public Boolean marcarDosisAplicada(LocalDate fecha)
    {
        Vacuna vacuna = coleccionVacuna.getVacunaPendiente(fecha);
        if(vacuna != null)
        {
            vacuna.setEstado(TipoEstado.REALIZADO);
            return true;
        }

        return false;
    }
    
    /**
     * Función que devuelve una prueba pendiente de validación de resultados por parte del técnico.
     * @return Prueba pendiente de validación de resultados por parte del técnico.
     */
    public Prueba getPruebaPendienteValidacion()
    {
        return coleccionPrueba.getPruebaPendienteValidacion();
    }

    /**
     * Marca la prueba programada en la fecha, como realizada por parte del enfermero.
     * @param fecha Fecha en que se realiza la prueba.
     * @return Booleano que indica si se marcado una prueba como realizada.
     */
    public Boolean marcarPruebaRealizada(LocalDate fecha)
    {
        Prueba prueba = coleccionPrueba.getPruebaPendiente(fecha);
        if(prueba != null)
        {
            prueba.setEstado(TipoEstado.REALIZADO);
            return true;
        }

        return false;
    }
}
