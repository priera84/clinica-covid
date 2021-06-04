package clinica.logica;

import java.time.LocalDateTime;

/**
 * Write a description of class Antigenos here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Antigenos extends Prueba
{
    // instance variables - replace the example below with your own
   private TipoResultadoPrueba resultado;

    /**
     * Constructor for objects of class PCR
     */
    public Antigenos(LocalDateTime fechaHora, Paciente paciente)
    {
        // initialise instance variables
        super(fechaHora, paciente);
    }
    
    public void setResultado(TipoResultadoPrueba resultado)
    {
        this.resultado = resultado;
        this.setEstado(TipoEstado.DIAGNOSTICADO);
    }

     public Boolean positivoCovid()
     {
         return resultado == TipoResultadoPrueba.POSITIVO;
     }
}
