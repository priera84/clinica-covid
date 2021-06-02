package clinica.logica; 

import java.time.LocalDate;

/**
 * Write a description of class paciente here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Paciente extends Persona
{
    // instance variables - replace the example below with your own
    private Boolean pacienteCovid;  
    private Boolean confinado;
    
    private ColeccionPrueba coleccionPrueba;
    private ColeccionVacuna coleccionVacuna;

    /**
     * Constructor for objects of class Paciente
     */
    public Paciente(String nombre, String apellidos, LocalDate fechaNacimiento, String dni, TipoGenero genero, String direccion, Boolean pacienteCovid)
    {
        super(nombre, apellidos, fechaNacimiento, dni, genero, direccion);
        this.pacienteCovid = false;        
        this.coleccionPrueba = new ColeccionPrueba();
        this.coleccionVacuna = new ColeccionVacuna();
    }

   

    public Boolean getConfinado()
    {
        return this.confinado;
    }

    private Boolean validarPrueba(Prueba prueba)
    {
        if(prueba instanceof Antigenos)
            return true;

        if(prueba instanceof PCR)
            return !coleccionPrueba.existePCRMenos15dias();

        if(prueba instanceof Serologico)
            return !coleccionPrueba.existeSerologicoMenos6Meses();

        return false;    
    }

    public Boolean asignarPrueba(Prueba prueba)
    {
        if(validarPrueba(prueba))
        {
            return coleccionPrueba.addPrueba(prueba);
        }
        else 
            return false;
    }
    
    public void desasignarPrueba(Prueba prueba)
    {
        coleccionPrueba.borrarPrueba(prueba);
    }
    

    public Boolean requiereVacuna()
    {
        if(coleccionVacuna.getNumeroVacunas() == 1 && coleccionVacuna.requiereSegundaDosis())
            return !coleccionVacuna.existeDosisMenos21dias();
        else return true;         
    }

    public Boolean asignarVacuna(Vacuna vacuna)
    {
        if(requiereVacuna())
        {
            vacuna.asignarPaciente(this);
            return coleccionVacuna.addVacuna(vacuna);
        }
        else 
            return false;
    }

    public String getDetallePruebas()
    {
        return this.coleccionPrueba.getDescripcion();
    }

    public String getDetalleVacunacion()
    {
        return this.coleccionVacuna.getDescripcion();
    }
    
    public String getDescripcion()
    {
        return "Paciente: " + super.getDescripcion();
    }
}
