package clinica.motor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Write a description of class ColeccionComando here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ColeccionComando
{
    private  List<Comando> listaComandos;

    /**
     * Constructor for objects of class ColeccionComando
     */
    public ColeccionComando()
    {
        // initialise instance variables
        listaComandos = new ArrayList<Comando>();
    }

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
