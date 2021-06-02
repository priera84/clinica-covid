package clinica.motor;

import clinica.logica.*;
/**
 * Clase que contiene los comandos que un usuario Administrador puede ejecutar.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class PermisoAdministrador extends ColeccionComando
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class ComandoAdministrador
     */
    public PermisoAdministrador(Clinica clinica)
    {
        super();
        //Gestion usuarios
        RegistraComando(new AltaAdministrador(clinica.getColeccionAdministrador()));
        RegistraComando(new BajaAdministrador(clinica.getColeccionAdministrador()));
        RegistraComando(new ModificacionAdministrador(clinica.getColeccionAdministrador()));
        
        RegistraComando(new AltaEnfermero(clinica.getColeccionEnfermero()));
        RegistraComando(new BajaEnfermero(clinica.getColeccionEnfermero()));
        RegistraComando(new ModificacionEnfermero(clinica.getColeccionEnfermero()));

        RegistraComando(new AltaTecnico(clinica.getColeccionTecnico()));
        RegistraComando(new BajaTecnico(clinica.getColeccionTecnico()));
        RegistraComando(new ModificacionTecnico(clinica.getColeccionTecnico()));

        RegistraComando(new AltaPaciente(clinica.getColeccionPaciente()));
        RegistraComando(new BajaPaciente(clinica.getColeccionPaciente()));
        RegistraComando(new ModificacionPaciente(clinica.getColeccionPaciente()));
        //Asignar pruebas
        RegistraComando(new AsignarPruebaATecnico(clinica.getColeccionTecnico(), clinica.getColeccionPaciente()));
        RegistraComando(new AsignarPruebaAEnfermero(clinica.getColeccionEnfermero(), clinica.getColeccionPaciente()));

        RegistraComando(new AsignarVacunacionAEnfermero(clinica.getColeccionEnfermero(), clinica.getColeccionPaciente()));
        
        RegistraComando(new AsignarPruebaPaciente(clinica.getColeccionPaciente(), clinica.getPlanificador()));

        RegistraComando(new VisualizarDatosPersonas(clinica.getColeccionTecnico(), "Visualizar Datos de técnicos registrados"));
        RegistraComando(new VisualizarDatosPersonas(clinica.getColeccionEnfermero(), "Visualizar Datos de enfermeros registrados"));
        RegistraComando(new VisualizarDatosPersonas(clinica.getColeccionPaciente(), "Visualizar Datos de pacientes registrados"));      
        RegistraComando(new VisualizarDatosPersonas(clinica.getColeccionAdministrador(), "Visualizar Datos de administradores registrados"));      
        
        RegistraComando(new RegistrarStockVacuna(clinica.getStockVacuna()));
        RegistraComando(new ConsultarStockVacuna(clinica.getStockVacuna()));
        RegistraComando(new ModificacionStockVacuna(clinica.getStockVacuna()));
        
        RegistraComando(new RegistrarStockPrueba(clinica.getStockPrueba()));
        
        
    }

}
