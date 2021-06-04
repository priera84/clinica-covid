package clinica.logica;
import java.time.LocalDateTime;

/**
 * Clase que representa una prueba de Serologico.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class Serologico extends Prueba
{
    // instance variables - replace the example below with your own
    private int resultado;

    /**
     * Constructor
     * @param fechaHora fecha hora en que se desea realizar la prueba.
     */
    public Serologico(LocalDateTime fechaHora)
    {
        super(fechaHora);
    }

    /**
     * Asigna el resultado de la prueba.
     * @param resultado int que se le asignará a la prueba.
     * @return Booleano que indica si el resultado está entre los margenes posibles.
     */
    public Boolean setResultado(int resultado)
    {
        if(resultado >0 && resultado <= 10)
        {        
            this.resultado = resultado;
            this.setEstado(TipoEstado.DIAGNOSTICADO);
            return true;
        }

        return false;
    }

    /**
     * Devuelve si el resultado representa un positivo por COVID-19.
     * @return Booleano indicando si el resultado es positivo por COVID-19.
     */
    public Boolean positivoCovid()
    {
        return resultado > 2;
    }
}
