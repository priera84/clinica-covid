package clinica.motor;


/**
 * Write a description of class ResultadoComando here.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class ResultadoComando
{
    private String mensajeResultado;
    
    private TipoResultadoComando resultadoComando;
    
    private Comando siguienteComando;
    
    private ColeccionComando siguienteColeccionComando;

    /**
     * Constructor for objects of class ResultadoComando
     */
    public ResultadoComando(TipoResultadoComando resultadoComando,String mensajeResultado, Comando siguienteComando)
    {
        this.mensajeResultado = mensajeResultado;
        this.resultadoComando = resultadoComando;
        this.siguienteComando = siguienteComando;
    }
    public ResultadoComando(TipoResultadoComando resultadoComando,String mensajeResultado, ColeccionComando siguienteColeccionComando)
    {
        this.mensajeResultado = mensajeResultado;
        this.resultadoComando = resultadoComando;
        this.siguienteColeccionComando = siguienteColeccionComando;
    }
    public ResultadoComando(TipoResultadoComando resultadoComando,String mensajeResultado)
    {
        this.mensajeResultado = mensajeResultado;
        this.resultadoComando = resultadoComando;
    }
    
    public TipoResultadoComando getTipoResultadoComando()
    {
        return this.resultadoComando;
    }

    public Comando getSiguienteComando()
    {
        return siguienteComando;
    }
    
    public ColeccionComando getSiguienteColeccionComando()
    {
        return siguienteColeccionComando;
    }
    
    public String getMensajeResultado()
    {
        return this.mensajeResultado;
    }
    
}
