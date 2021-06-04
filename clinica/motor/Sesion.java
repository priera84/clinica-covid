package clinica.motor;


/**
 * Write a description of class Sesion here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
