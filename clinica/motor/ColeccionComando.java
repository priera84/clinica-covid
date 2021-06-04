package clinica.motor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Representa una colección de comandos. Se utiliza para restringir los comandos que puede ejecutar cada usuario.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class ColeccionComando
{
    private  List<Comando> listaComandos;

    /**
     * Constructor 
     */
    public ColeccionComando()
    {
        // initialise instance variables
        listaComandos = new ArrayList<Comando>();
    }

    /**
     * Registra un comando en la colección.
     * @param comando a registrar.
     * @return Booleano que indica si ha podido registrarse un comando en la colección.
     */
    protected Boolean RegistraComando(Comando comando)
    { 
        if(!listaComandos.contains(comando))
        {
            listaComandos.add(comando);
            return true;
        }

        return false;
    }

    public List<Comando> getComandos()
    {
        return listaComandos;
    }

    public Comando getComando(int indice)
    {
        if(indice > 0 && indice <= listaComandos.size())
            return listaComandos.get(indice -1);

        return null;
    }
}
