package clinica.logica;

import java.time.LocalDate;
/**
 * Clase que representa un registro del stock de pruebas.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class RegistroPrueba extends RegistroStock
{
    private TipoPrueba marcaPrueba;    
    
    /**
     * Constructor
     * @param fechaRegistro Fecha en que se registra la modificación.
     * @param unidadesRegistradas Número de unidades registradas para esa prueba.
     * @param marcaPrueba Marca de la prueba registrada.
     */ 
    public RegistroPrueba(LocalDate fechaRegistro, int unidadesRegistradas, TipoPrueba marcaPrueba)
    {
        super(fechaRegistro, unidadesRegistradas);  
        this.marcaPrueba = marcaPrueba;
    }  

    /**
     * Función que devuelve el tipo de prueba registrada en stock.
     * @return TipoPrueba de la prueba registrada.
     */
    public TipoPrueba getMarcaPrueba()
    {
        return this.marcaPrueba;
    }    

    /**
     * Método que se utiliza por el método contains del ArrayList para comparar objetos la lista.
     * Sobreescribiendo este método se puede modificar el criterio por el cual dos objetos se consideran iguales.
     * En este caso nos interesa comparar objetos de tipo RegistroPrueba en base a la marca registrada.
     * @param objeto de tipo RegistroPrueba con el que se compara.
     * @return si es igual o no
     */
    @Override
    public boolean equals(Object objeto) {
        boolean resultado = false;

        if (objeto instanceof RegistroPrueba){
            RegistroPrueba registroPrueba = (RegistroPrueba) objeto;
            resultado = this.marcaPrueba.equals(registroPrueba.getMarcaPrueba());
        }

        return resultado;
    }
}
