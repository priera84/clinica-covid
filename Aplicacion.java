import clinica.interfaces.*;

/**
 * Punto de entrada la ejecución de la aplicación en modo consola.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class Aplicacion
{
    public static void main(String [] args)
    {                
        Consola consola = new Consola();
        consola.iniciar();
    }
}
