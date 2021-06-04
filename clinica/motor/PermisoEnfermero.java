package clinica.motor;

import clinica.logica.*;

/**
 * Clase que gestiona los comandos disponibles para los enfermeros.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class PermisoEnfermero extends ColeccionComando
{
    /**
     * Constructor for objects of class Enfermero
     * @param enfermero loggeado en el sistema
     */
    public PermisoEnfermero(clinica.logica.Enfermero enfermero)
    {
        super();
        RegistraComando(new VerPacientesAsignado(enfermero));
        
//        RegistraComando(new RegistroPrueba(enfermero));
//        RegistraComando(new RegistroVacunacion(enfermero));
    }

    
}
