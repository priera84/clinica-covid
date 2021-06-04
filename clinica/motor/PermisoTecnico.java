package clinica.motor;


/**
 * Clase que contiene los comandos que un usuario Tecnico puede ejecutar.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
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
