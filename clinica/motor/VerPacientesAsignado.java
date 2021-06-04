package clinica.motor;

import clinica.logica.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

/**
 * Comando que permite ver los pacientes asignados a un usuario.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class VerPacientesAsignado extends Comando
{
    private clinica.logica.Usuario usuario;
    /**
     * Constructor for objects of class VerPacientesAsignados
     */
    public VerPacientesAsignado(clinica.logica.Usuario usuario)
    {
        super();
        this.usuario = usuario;
    }
    
     public ResultadoComando ejecutar()
    {
        ColeccionPaciente pacientesAsignados = usuario.getPacientesAsignados();
        if(pacientesAsignados != null)
            return new ResultadoComando(TipoResultadoComando.EXITO, "Lista de pacientes: \n"+ pacientesAsignados.getListaPacientes());
        else 
            return new ResultadoComando(TipoResultadoComando.ERROR, "No tiene pacientes asignados.");
    }

    public String getDescripcion()
    {
        return "Ver pacientes asignados";
    }

    

}
