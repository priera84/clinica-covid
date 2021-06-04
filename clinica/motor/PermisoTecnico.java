package clinica.motor;


/**
 * Write a description of class Tecnico here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PermisoTecnico extends ColeccionComando
{

    /**
     * Constructor for objects of class Tecnico
     */
    public PermisoTecnico(clinica.logica.Tecnico tecnico)
    {
        super();
        RegistraComando(new VerPacientesAsignado(tecnico));
        RegistraComando(new ObtenerPruebaPaciente(tecnico.getPacientesAsignados()));
        
        
            
        RegistraComando(new Logout());
    }
}
