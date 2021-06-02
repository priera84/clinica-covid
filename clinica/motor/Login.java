package clinica.motor;

import clinica.logica.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
/**
 * Comando que permite logearse en el sistema.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class Login extends Comando
{
    // instance variables - replace the example below with your own
    /**
     * Constructor for objects of class Login
     */
    public Login()
    {
        super();

        this.parametros.put("nombreUsuario", new Parametro<String>("nombreUsuario", "Nombre de usuario"));
        this.parametros.put("clave",new Parametro<String>("clave", "clave"));
    }

    public ResultadoComando ejecutar()
    {
        Clinica clinica = Clinica.getInstancia();

        if(!clinica.loggearUsuario(((Parametro<String>)parametros.get("nombreUsuario")).getValor(),
            ((Parametro<String>)parametros.get("clave")).getValor()))
            return new ResultadoComando(TipoResultadoComando.ERROR, "Nombre de usuario y/o clave incorrectos");

        else
        {
            clinica.logica.Usuario usuario = clinica.getUsuarioActivo();
            switch(usuario.getTipoUsuario())
            {
                case ADMINISTRADOR:            
                    return new ResultadoComando(TipoResultadoComando.NUEVA_COLECCION_COMANDO, null, new PermisoAdministrador(clinica));
                case ENFERMERO:
                    return new ResultadoComando(TipoResultadoComando.NUEVA_COLECCION_COMANDO, null, new PermisoEnfermero((Enfermero)usuario));
                case TECNICO:
                    return new ResultadoComando(TipoResultadoComando.NUEVA_COLECCION_COMANDO, null, new PermisoTecnico((Tecnico)usuario));
            }
        }

        return null;
    }

    public String getDescripcion()
    {
        return "Loggin en el sistema";
    }   
}
