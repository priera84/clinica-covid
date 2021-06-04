package clinica.logica;
import java.time.LocalDate;

/**
 * Write a description of class StockPrueba here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StockPrueba extends Stock
{

    /**
     * Constructor for objects of class StockPrueba
     */
    public StockPrueba()
    {
        // initialise instance variables
        super();
    }

       
    public Boolean modificarPrueba(LocalDate fechaModificacion, int unidadesRegistradas, TipoPrueba marcaPrueba)
    {                
        //Agregamos registro con número actual de unidades prueba.
        RegistroPrueba registroPrueba = new RegistroPrueba(fechaModificacion, unidadesRegistradas, marcaPrueba);
        return reemplazarStock(registroPrueba);        
    }
    public String consultarStock()
    {
        Integer totalPCR = new Integer(0);
        Integer totalSerologico = new Integer(0);
        Integer totalAntigenos  = new Integer(0);

        if(listaRegistroStock.size() >= 0)
        {
            for (RegistroStock registroPrueba : listaRegistroStock)
            {
                switch (((RegistroPrueba)registroPrueba).getMarcaPrueba())
                {
                    case PCR:
                        totalPCR += registroPrueba.getUnidadesDisponibles();
                        break;
                    case SEROLOGICA: 
                        totalSerologico += registroPrueba.getUnidadesDisponibles();
                        break;
                    case ANTIGENOS:
                        totalAntigenos += registroPrueba.getUnidadesDisponibles();
                        break;
                }       
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Total PCR: " + totalPCR.toString());
        sb.append("\nTotal SEROLOGICA: " + totalSerologico.toString());
        sb.append("\nTotal ANTIGENOS: " + totalAntigenos.toString());
        return sb.toString();
    }

}
