package clinica.logica; 

import java.time.LocalDateTime;
import java.time.LocalDate;
/**
 * Clase que represente un usuario Técnico.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class Tecnico extends Usuario
{
    ColeccionPrueba coleccionPrueba;

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
    public Tecnico(String nombreUsuario, String clave, String nombre, String apellidos, LocalDate fechaNacimiento, String dni, TipoGenero genero, String direccion, String telefono)
    {
        super(nombreUsuario, clave, TipoUsuario.TECNICO, nombre, apellidos, fechaNacimiento, dni, genero, direccion, telefono);
        this.coleccionPrueba = new ColeccionPrueba();
    }

    /**
     * Función que devuelve si el técnico tiene disponibilidad para realizar una prueba diagnóstica,
     * dado que no puede realizar más de cuatro pruebas a la semana.
     * @return booleano que indica si está disponible.
     */
    public Boolean disponibleParaPrueba(LocalDateTime fechaHora)
    {
        return this.coleccionPrueba.getNumeroPruebasDeSemana(fechaHora) < 4;
    }

    /**
     * Método que desasigna al técnico una prueba asignada previamente.
     */
    public void desasignarPrueba(Prueba prueba)
    {
        this.coleccionPrueba.borrarPrueba(prueba);
    }
    
    
    /**
     * Método que asigna una prueba al técnico.
     * @return Booleano indicando si ha podido asignarse correctamente.
     */
    public Boolean asignarPrueba(Prueba prueba)
    {
        return this.coleccionPrueba.addPrueba(prueba);
    }

    
    /**
     * Función que devuelve los pacientes asignados al técnico, para pruebas diagnósticas.
     * @return ColeccionPaciente con todos los pacientes asignados.
     */
    public ColeccionPaciente getPacientesAsignados()
    {
        return coleccionPrueba.getPacientesAsignados();
    }   
}
