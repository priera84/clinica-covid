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
public class StockVacuna extends Stock
{
    /**
     * Constructor for objects of class StockVacuna
     */
    public StockVacuna()
    {
        super();
    }

    
    public Boolean modificarVacuna(LocalDate fechaModificacion, int unidadesRegistradas, TipoMarcaVacuna marcaVacuna)
    {                
        //Agregamos registro con número actual de vacunas.
        RegistroVacuna registroVacuna = new RegistroVacuna(fechaModificacion, unidadesRegistradas, marcaVacuna);
        return reemplazarStock(registroVacuna);        
    }

   

    public Boolean sacarVacunaMarca(TipoMarcaVacuna marcaVacuna)
    {
        if(listaRegistroStock.size() >= 0)
            for (RegistroStock registroVacuna : listaRegistroStock)
            {
                if(((RegistroVacuna)registroVacuna).getMarcaVacuna() == marcaVacuna && registroVacuna.retirarUnidad())
                    return true;
            }

        return false;
    }

    public String consultarStockVacuna()
    {
        Integer totalPfizer = new Integer(0);
        Integer totalModerna  = new Integer(0);
        Integer totalJJ  = new Integer(0);

        if(listaRegistroStock.size() >= 0)
        {
            for (RegistroStock registroVacuna : listaRegistroStock)
            {
                switch (((RegistroVacuna)registroVacuna).getMarcaVacuna())
                {
                    case PFIZER:
                        totalPfizer += registroVacuna.getUnidadesDisponibles();
                        break;
                    case MODERNA: 
                        totalModerna += registroVacuna.getUnidadesDisponibles();
                        break;
                    case JOHNSON_AND_JOHNSON:
                        totalJJ += registroVacuna.getUnidadesDisponibles();
                        break;
                }       
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Total PFIZER: " + totalPfizer.toString());
        sb.append("\nTotal MODERNA: " + totalModerna.toString());
        sb.append("\nTotal JOHNSON AND JOHNSON: " + totalJJ.toString());
        return sb.toString();
    }
}
