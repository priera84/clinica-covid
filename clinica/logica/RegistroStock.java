package clinica.logica;

import java.time.LocalDate;
/**
 * Clase abstracta que representa un registro de un producto en el stock.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public abstract class RegistroStock
{
    private LocalDate fechaRegistro;
    private int unidadesRegistradas;    
    private int unidadesDisponibles;
    /**
     * Constructor
     * @param fechaRegistro Fecha en que se registra la modificación.
     * @param unidadesRegistradas Número de unidades registradas para ese producto.     
     */ 
    public RegistroStock(LocalDate fechaRegistro, int unidadesRegistradas)
    {
        this.fechaRegistro = fechaRegistro;
        this.unidadesRegistradas = unidadesRegistradas;
        this.unidadesDisponibles = unidadesRegistradas;
    }     

    /**
     * Función que permite retirar una unidad de producto del stock.
     * @return Booleano indicando si se ha podido retirar una unidad de producto, por existir en stock.
     */
    public Boolean retirarUnidad()
    {
        if(unidadesDisponibles > 0)
        {
            unidadesDisponibles--;
            return true;
        }
        else
            return false;
    }

    /**
     * Función que devuelve el número de unidades de producto disponibles para consumo.
     * @return integer con el número de unidades de prooducto disponibles.
     */
    public int getUnidadesDisponibles()
    {
        return this.unidadesDisponibles;
    }

    /**
     * Función que devuelve el número de unidades de producto registradas.
     * @return integer con el número de unidades de prooducto registradas.
     */
    public int getUnidadesRegistradas()
    {
        return this.unidadesRegistradas;
    }

}
