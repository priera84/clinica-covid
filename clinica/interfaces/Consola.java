package clinica.interfaces;

import java.util.Scanner;
import clinica.motor.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * Write a description of class Consola here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Consola
{
    private int totalComandosMostrados = 0;
    // instance variables - replace the example below with your own
    ColeccionComando coleccionComando;

    /**
     * Constructor for objects of class Consola
     */
    public Consola()
    {
        coleccionComando = new Sesion();        
    }

    private void imprimirComandos()
    {
        int indice = 1;
        for(Comando comando: coleccionComando.getComandos())
        {
            System.out.println(indice + ". " + comando.getDescripcion());            
            indice++;
        }            

        totalComandosMostrados = indice;
    }

    private void procesarResultado(ResultadoComando resultadoComando)
    {
        switch(resultadoComando.getTipoResultadoComando())
        {
            case EXITO:
                System.out.println(resultadoComando.getMensajeResultado());
                break;
            case ERROR:
                System.out.println(resultadoComando.getMensajeResultado());
                // imprimirComandos();
                break;
            case NUEVO_COMANDO:
                ejecutarComando(resultadoComando.getSiguienteComando());
                break;
            case NUEVA_COLECCION_COMANDO:
                coleccionComando = resultadoComando.getSiguienteColeccionComando();            
        }
    }

    private void ejecutarComando(Comando comando)
    {
        LinkedHashMap<String, Parametro> parametros = comando.getParametros();
        ResultadoComando resultadoComando;
        if(parametros != null && parametros.size() > 0)
        {
            Scanner teclado = new Scanner (System.in);
            
            System.out.println("A continuación se le solicitarán los parámetros necesarios para ejectuar el comando: "+ comando.getDescripcion());

            for (Object valor: parametros.values()) {                                     
                Parametro parametro = (Parametro)valor;
                Boolean resultadoSetValor = false;
                do
                {
                    System.out.println(parametro.getDescripcion() + ": ");
                    String valorTeclado = teclado.nextLine();
                    
                    resultadoSetValor = parametro.setValor(valorTeclado);                    
                    if(!resultadoSetValor)
                        System.out.println("Valor incorrecto");

                }while(!resultadoSetValor);

            }
            resultadoComando = comando.ejecutar();

        }
        else
        { 
            resultadoComando =  comando.ejecutar();
        }
        procesarResultado(resultadoComando);
    }

    public void iniciar()
    {
        int opcion;
        Scanner teclado = new Scanner (System.in);
        do{

            imprimirComandos();
            System.out.println("\n0.- Salir de la aplicación");   
            System.out.print("Seleccionar opción ==>");

            // pedir opción seleccionada
            opcion=teclado.nextInt();    

            if(opcion != 0)
            {
                if (opcion > totalComandosMostrados)
                    System.out.println("Opción incorrecta");
                else
                {
                    Comando comando = coleccionComando.getComando(opcion);
                    ejecutarComando(comando);
                }
            }

        }  while (opcion != 0);   //  do del bucle principal
    }

}
