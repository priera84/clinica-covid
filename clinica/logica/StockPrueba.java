package clinica.logica;
import java.time.LocalDate;

/**
 * Representa un almacen/stock de pruebas diagnósticas.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
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

    /**
     * Función que permite modificar el stock registrado de una prueba.
     * @param fechaModificacion Fecha en que se registra la modificación.
     * @param unidadesRegistradas Número de unidades registradas para esa prueba.
     * @param tipoPrueba Tipo de prueba registrado.
     * @return Booleano indicando si se ha podido modificar el stock de la prueba.
     */       
    public Boolean modificarPrueba(LocalDate fechaModificacion, int unidadesRegistradas, TipoPrueba tipoPrueba)
    {                
        //Agregamos registro con número actual de unidades prueba.
        RegistroPrueba registroPrueba = new RegistroPrueba(fechaModificacion, unidadesRegistradas, tipoPrueba);
        return reemplazarStock(registroPrueba);        
    }
    
    /**
     * Función que devuelve el estado del stock de pruebas.
     * @return String que describe la cantidad de pruebas en stock de cada tipo.
     */
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
    
    /**
     * Función que descuenta una prueba del inventario para su consumo.
     * @param tipoPrueba Tipo de la prueba que se desea consumir.
     * @return Booleano que indica si hay stock de la prueba deseada.
     */
     public Boolean sacarPruebaTipo(TipoPrueba tipoPrueba)
    {
        if(listaRegistroStock.size() >= 0)
            for (RegistroStock registroPrueba : listaRegistroStock)
            {
                if(((RegistroPrueba)registroPrueba).getMarcaPrueba().equals(tipoPrueba) && registroPrueba.retirarUnidad())
                    return true;
            }

        return false;
    }

}
