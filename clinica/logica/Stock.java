package clinica.logica;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
/**
 * Write a description of class StockVacuna here.
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

    
    public Boolean registrarStock(RegistroStock registroStock)
    {
        if(registroStock.getUnidadesRegistradas() == 0)
            return false;
            
        return  this.listaRegistroStock.add(registroStock);

    }    
    
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
}
