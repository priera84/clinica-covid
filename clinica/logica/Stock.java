package clinica.logica;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
/**
 * Clase abstracta que representa un almacen/stock.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public abstract class Stock
{
    protected List<RegistroStock> listaRegistroStock;

    /**
     * Constructor for objects of class StockVacuna
     */
    public Stock()
    {
        this.listaRegistroStock = new ArrayList<RegistroStock>();
    }

    /**
     * Agrega un producto al stock.
     * @param registroStock Representa un producto que se añade al stock.
     * @return Booleano indicando que se ha podido agregar al stock.
     */
    public Boolean registrarStock(RegistroStock registroStock)
    {
        if(registroStock.getUnidadesRegistradas() == 0)
            return false;
            
        return  this.listaRegistroStock.add(registroStock);

    }    
    
    /**
     * Modifica el stock de un producto.
     * @param registroStockNuevo Representa el objeto producto que reempleza a los anteriormente registrados.
     * @return Booleano indicando que se ha podido modificar el stock.
     */
    public Boolean reemplazarStock(RegistroStock registroStockNuevo)
    {
        if(listaRegistroStock.size() >= 0)
        {
            List<RegistroStock> listaStockBorrar = new ArrayList<RegistroStock>();
            for (RegistroStock registroStock : listaRegistroStock)
            {
                if(registroStock.equals(registroStockNuevo))
                    listaStockBorrar.add(registroStock);
            }

            for (RegistroStock registroStock : listaStockBorrar)
            {
                listaRegistroStock.remove(registroStock);
            }

        }   
        
        return listaRegistroStock.add(registroStockNuevo);
    }
    
    /**
     * Función abstracta que devuelve el estado del stock.
     * @return String que describe la cantidad de producto de cada tipo.
     */
    public abstract String consultarStock();
}
