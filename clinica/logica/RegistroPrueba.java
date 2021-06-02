package clinica.logica;

import java.time.LocalDate;
/**
 * Write a description of class RegistroVacuna here.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class RegistroPrueba extends RegistroStock
{
    private TipoPrueba marcaPrueba;    
    /**
     * Constructor for objects of class RegistroVacuna
     */
    public RegistroPrueba(LocalDate fechaRegistro, int unidadesRegistradas, TipoPrueba marcaPrueba)
    {
        super(fechaRegistro, unidadesRegistradas);  
        this.marcaPrueba = marcaPrueba;
    }  

    public TipoPrueba getMarcaPrueba()
    {
        return this.marcaPrueba;
    }    
    
    /**
     * Método que se utiliza por el método contains del ArrayList para comparar objetos la lista.
     * Sobreescribiendo este método se puede modificar el criterio por el cual dos objetos se consideran iguales.
     * En este caso nos interesa comparar personas en base a su DNI.
     * @param objeto de tipo persona con el que se compara.
     * @return si es igual o no, en este caso si tiene el mismo DNI.
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
