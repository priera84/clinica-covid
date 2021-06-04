package clinica.logica;

import java.time.LocalDateTime;

/**
 * Clase que representa una prueba de Antigenos.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class Antigenos extends Prueba
{
    // instance variables - replace the example below with your own
    private TipoResultadoPrueba resultado;

    /**
     * Constructor
     * @param fechaHora fecha hora en que se desea realizar la prueba.
     */
    public Antigenos(LocalDateTime fechaHora)
    {
        // initialise instance variables
        super(fechaHora);
    }

    /**
     * Asigna el resultado de la prueba.
     * @param resultado TipoResultadoPrueba que se le asignará a la prueba.
     */
    public void setResultado(TipoResultadoPrueba resultado)
    {
        this.resultado = resultado;
        this.setEstado(TipoEstado.DIAGNOSTICADO);
    }

    /**
     * Devuelve si el resultado representa un positivo por COVID-19.
     * @return Booleano indicando si el resultado es positivo por COVID-19.
     */
    public Boolean positivoCovid()
    {
        return resultado == TipoResultadoPrueba.POSITIVO;
    }
}
