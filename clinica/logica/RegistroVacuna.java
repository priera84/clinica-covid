package clinica.logica;

import java.time.LocalDate;
/**
 * Write a description of class RegistroVacuna here.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class RegistroVacuna extends RegistroStock
{
    private TipoMarcaVacuna marcaVacuna;    
    /**
     * Constructor for objects of class RegistroVacuna
     */
    public RegistroVacuna(LocalDate fechaRegistro, int unidadesRegistradas, TipoMarcaVacuna marcaVacuna)
    {
        super(fechaRegistro, unidadesRegistradas);  
        this.marcaVacuna = marcaVacuna;
    }  

    public TipoMarcaVacuna getMarcaVacuna()
    {
        return this.marcaVacuna;
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

        if (objeto instanceof RegistroVacuna){
            RegistroVacuna registroVacuna = (RegistroVacuna) objeto;
            resultado = this.marcaVacuna.equals(registroVacuna.getMarcaVacuna());
        }

        return resultado;
    }
}
