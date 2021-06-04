package clinica.motor;

/**
 * Representa un comando de la aplicación. Clase generica con un parametro de tipo (T).
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class Parametro<T>
{
    //Representa el interface que permite convertir los strings introducidos por el usuario al tipo de destino del parametro.
    interface IConversor<T>{
        T convertir(String value);
    }

    private IConversor conversor;
    private String nombre;
    private String descripcion;
    private T valor;

    /**
     * Constructor for objects of class Parametro
     */
    public Parametro(String nombre, String descripcion,T valorDefecto, IConversor conversor)
    {
        // initialise instance variables
        this.conversor = conversor;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valor = valorDefecto;
    }

    public Parametro(String nombre, String descripcion,T valorDefecto)
    {
        // initialise instance variables
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valor = valorDefecto;
    }

    public Parametro(String nombre, String descripcion)
    {
        // initialise instance variables
        this.nombre = nombre;
        this.descripcion = descripcion;        
    }

    public IConversor getConversor(){
        return this.conversor;
    }//end method getConversor

    public T getValor()
    {
        return this.valor;
    }

    public Boolean setValor(String valorNuevo)
    {
        try
        {
            if(this.conversor != null)
            {
                this.valor = (T)this.conversor.convertir(valorNuevo);   
            }
            else
                this.valor = (T)valorNuevo;

            return true;
        }
        catch(Exception ex)
        {
            return false;
        }

    }

    public void setConversor(IConversor conversor)
    {
        this.conversor = conversor;
    }

    public String getNombre()
    {
        return this.nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getDescripcion()
    {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }
}