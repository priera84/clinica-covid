package clinica.motor;

import java.util.*;
/**
 * Abstract class Comando - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Comando
{
   protected LinkedHashMap<String, Parametro> parametros;
   
   public Comando()
   {
      parametros = new LinkedHashMap<String, Parametro>();
   }
   
   public abstract ResultadoComando ejecutar();
   
   public abstract String getDescripcion();
   
   /***
    * Devuelve una lista con los parámetros necesarios para ejecutar el comando
    */
   public LinkedHashMap<String, Parametro> getParametros()
   {
      return parametros;
   }
}
