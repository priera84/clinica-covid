package clinica.motor;

import clinica.logica.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * Clase que representa un comando que permite registrar la vacunación de un paciente.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class RegistroPruebaTecnico extends Comando
{
    private Prueba prueba;    

    /**
     * Constructor for objects of class RegistroVacunacion
     */
    public RegistroPruebaTecnico(Prueba prueba)
    {
        // initialise instance variables
        super();
        if(prueba instanceof PCR || prueba instanceof Antigenos)
            this.parametros.put("resultado", new Parametro<TipoResultadoPrueba>("resultado", "Introduzca resultado (POSITIVO, NEGATIVO)", null, x ->{
                        return TipoResultadoPrueba.valueOf(x);
                    }));         
        if(prueba instanceof  Serologico)
            this.parametros.put("resultado", new Parametro<Integer>("resultado", "Introduzca resultado (0-10)", null, x ->{
                        return Integer.parseInt(x);
                    }));

        this.prueba = prueba;
    }

    public ResultadoComando ejecutar()
    {
        TipoResultadoPrueba resultado;
        Integer resultadoSerologico;

        Boolean resultadoCambioEstado = false;
        if(prueba instanceof PCR)
        { 
            resultado  = ((Parametro<TipoResultadoPrueba>)parametros.get("resultado")).getValor();
             ((PCR)prueba).setResultado(resultado);
             resultadoCambioEstado = true;
        }

        if(prueba instanceof Antigenos)
        { 
            resultado  = ((Parametro<TipoResultadoPrueba>)parametros.get("resultado")).getValor();
            ((Antigenos)prueba).setResultado(resultado);
            resultadoCambioEstado = true;
        }
        if(prueba instanceof  Serologico)
        {

            resultadoSerologico =  ((Parametro<Integer>)parametros.get("resultado")).getValor();
            resultadoCambioEstado = ((Serologico)prueba).setResultado(resultadoSerologico);
        }

        if(resultadoCambioEstado)
        {
            return new ResultadoComando(TipoResultadoComando.EXITO, "Prueba actualizada correctamente:\n" + prueba.getPacienteAsignado().getDetallePruebas());        
        }
        else
        {
            return new ResultadoComando(TipoResultadoComando.ERROR, "Resultado introducido no válido");        
        }

    }

    public String getDescripcion()
    {
        return "Actualizar resultado prueba paciente";
    }
}
