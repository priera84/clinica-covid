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
   protected LinkedHashMap<String, Parametro> parametros;// A rellenar por cada comando que hereda de esta clase.
   
   public Comando()
   {
      parametros = new LinkedHashMap<String, Parametro>();
   }
   
   /**
    * Ejecuta el comando sobre la logica de la aplicación.
    */
   public abstract ResultadoComando ejecutar();
   
   /**
    * Obtiene la descripción del comando.
    * @return Cadena que describe el comando a ejecutar.
    */
   public abstract String getDescripcion();
   
   /***
    * Devuelve una lista con los parámetros necesarios para ejecutar el comando
    * @return Diccionario ordenado con todos los parametros a solicitar al usuario.
    */
   public LinkedHashMap<String, Parametro> getParametros()
   {
      return parametros;
   }
}
