package clinica.motor;


/**
 * Clase que contiene los comandos que aparecen inicialmente en la aplicación.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class Sesion extends ColeccionComando
{
    /**
     * Constructor for objects of class Sesion
     */
    public Sesion()
    {
        super();
        // initialise instance variables
       RegistraComando(new Login());       
    }

    
}
