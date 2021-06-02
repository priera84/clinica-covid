package clinica.motor;

/**
 * Write a description of class Parametro here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Parametro<T>
{
    // instance variables - replace the example below with your own

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

    public void setConversor(IConversor conversor){
        this.conversor = conversor;
    }//end method setConversor

    public String getNombre(){
        return this.nombre;
    }//end method getNombre

    public void setNombre(String nombre){
        this.nombre = nombre;
    }//end method setNombre

    public String getDescripcion(){
        return this.descripcion;
    }//end method getDescripcion

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }//end method setDescripcion

}