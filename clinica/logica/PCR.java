package clinica.logica;
import java.time.LocalDateTime;

/**
 * Write a description of class PCR here.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class PCR extends Prueba
{
    // instance variables - replace the example below with your own
    private TipoResultadoPrueba resultado;

    /**
     * Constructor for objects of class PCR
     */
    public PCR(LocalDateTime fechaHora, Paciente paciente)
    {
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
