package clinica.logica; 

import java.time.LocalDateTime;
import java.time.LocalDate;
/**
 * Write a description of class Tecnico here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
     */ 
    public Tecnico(String nombreUsuario, String clave, String nombre, String apellidos, LocalDate fechaNacimiento, String dni, TipoGenero genero, String direccion)
    {
        super(nombreUsuario, clave, TipoUsuario.TECNICO, nombre, apellidos, fechaNacimiento, dni, genero, direccion);
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

    public void desasignarPrueba(Prueba prueba)
    {
        this.coleccionPrueba.borrarPrueba(prueba);
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
