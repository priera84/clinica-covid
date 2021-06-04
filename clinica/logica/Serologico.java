package clinica.logica;
import java.time.LocalDateTime;

/**
 * Write a description of class Serologico here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Serologico extends Prueba
{
    // instance variables - replace the example below with your own
    private int resultado;

    /**
     * Constructor for objects of class Serologico
     */
    public Serologico(LocalDateTime fechaHora, Paciente paciente)
    {
        super(fechaHora, paciente);
    }

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

    public Boolean positivoCovid()
    {
        return resultado > 2;
    }
}
