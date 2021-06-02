package clinica.logica; 

import java.time.LocalDate;
/**
 * Write a description of class Administrador here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Administrador extends Usuario
{

    public Administrador(String nombreUsuario, String clave, String nombre, String apellidos, LocalDate fechaNacimiento, String dni, TipoGenero genero, String direccion)
    {
        super(nombreUsuario, clave, TipoUsuario.ADMINISTRADOR, nombre, apellidos, fechaNacimiento, dni, genero, direccion);

    }

    public ColeccionPaciente getPacientesAsignados()
    {
        return null;
    }
}
