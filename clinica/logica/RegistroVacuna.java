package clinica.logica;

import java.time.LocalDate;
/**
 * Clase que representa un registro del stock de vacuna.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class RegistroVacuna extends RegistroStock
{
    private TipoMarcaVacuna marcaVacuna;    

    /**
     * Constructor
     * @param fechaRegistro Fecha en que se registra la modificación.
     * @param unidadesRegistradas Número de unidades registradas para esa vacuna.
     * @param marcaVacuna Marca de la vacuna registrada.
     */ 
    public RegistroVacuna(LocalDate fechaRegistro, int unidadesRegistradas, TipoMarcaVacuna marcaVacuna)
    {
        super(fechaRegistro, unidadesRegistradas);  
        this.marcaVacuna = marcaVacuna;
    }  

    /**
     * Devuelve la marca de la vacuna registrada en stock.
     * @return Tipo marca de la vacuna.
     */
    public TipoMarcaVacuna getMarcaVacuna()
    {
        return this.marcaVacuna;
    }    

    /**
     * Método que se utiliza por el método contains del ArrayList para comparar objetos la lista.
     * Sobreescribiendo este método se puede modificar el criterio por el cual dos objetos se consideran iguales.
     * En este caso nos interesa comparar registros de stock de vacunas en base a su marca.
     * @param objeto de tipo RegistroVacuna con el que se compara.
     * @return si es igual o no, en este caso si tienen la misma marca.
     */
    @Override
    public boolean equals(Object objeto) {
        boolean resultado = false;

        if (objeto instanceof RegistroVacuna){
            RegistroVacuna registroVacuna = (RegistroVacuna) objeto;
            resultado = this.marcaVacuna.equals(registroVacuna.getMarcaVacuna());
        }

        return resultado;
    }
}
