package clinica.logica;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Write a description of class ColeccionTecnico here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ColeccionTecnico extends ColeccionUsuario
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class ColeccionTecnico
     */
    public ColeccionTecnico()
    {
        super();
    }

    /**
     * 
     */
    public Boolean altaTecnico(Tecnico tecnico)
    {
         return altaPersona(tecnico);
    }

    public Boolean bajaTecnico(String nombreUsuario)
    {
        return bajaUsuario(nombreUsuario);
    }

    public Boolean actualizarTecnico(String nombreUsuario, String direccionNew)
    {
        return actualizarUsuario(nombreUsuario, direccionNew);
    }
    
    public Tecnico obtenerTecnicoDisponible(LocalDateTime fechaHora)
    {
        for(Persona persona : this.listaPersonas)
        {
            if(persona instanceof Tecnico)
            {
                if(((Tecnico)persona).disponibleParaPrueba(fechaHora))
                    return (Tecnico)persona;                
            }
        }
        
        return null;
    }
}
