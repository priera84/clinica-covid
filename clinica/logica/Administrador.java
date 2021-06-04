package clinica.logica; 

import java.time.LocalDate;
/**
 * Clase que representa un usuario Administrador.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class Administrador extends Usuario
{

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
    public Administrador(String nombreUsuario, String clave, String nombre, String apellidos, LocalDate fechaNacimiento, String dni, TipoGenero genero, String direccion, String telefono)
    {
        super(nombreUsuario, clave, TipoUsuario.ADMINISTRADOR, nombre, apellidos, fechaNacimiento, dni, genero, direccion, telefono);
    }

    /**
     * Función que devuelve los pacientes asignados.
     * @return ColeccionPaciente con todos los pacientes asignados.
     */
    public ColeccionPaciente getPacientesAsignados()
    {
        //El usuario Admnistrador no tiene pacientes asignados.
        return null;
    }
}
