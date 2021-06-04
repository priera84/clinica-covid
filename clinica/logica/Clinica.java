package clinica.logica;
import java.time.LocalDate;

/**
 * Clase principal de la clínica. Se basa en el patrón de diseño Singleton.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public class Clinica
{
    private ColeccionAdministrador coleccionAdministrador;
    private ColeccionEnfermero coleccionEnfermero;
    private ColeccionTecnico coleccionTecnico;
    private ColeccionPaciente coleccionPaciente;
    private Planificador planificador;
    private StockVacuna stockVacuna;
    private StockPrueba stockPrueba;

    private Usuario usuarioActivo; 

    private static Clinica instancia;

    public static Clinica getInstancia()
    {
        if(instancia == null)
            instancia = new Clinica();
        return instancia;
    }

    public StockPrueba getStockPrueba()
    {
        return this.stockPrueba;
    }

    public StockVacuna getStockVacuna()
    {
        return this.stockVacuna;
    }

    public Planificador getPlanificador()
    {
        return this.planificador;
    }

    public ColeccionAdministrador getColeccionAdministrador()
    {
        return this.coleccionAdministrador;
    }

    public ColeccionEnfermero getColeccionEnfermero()
    {
        return this.coleccionEnfermero;
    }

    public ColeccionTecnico getColeccionTecnico()
    {
        return this.coleccionTecnico;
    }

    public ColeccionPaciente getColeccionPaciente()
    {
        return this.coleccionPaciente;
    }

    public Usuario getUsuarioActivo()
    {
        return usuarioActivo;
    }

    /**
     * Agrega datos de prueba para la practica.
     */
    private void agregarDatosPrueba()
    {
        coleccionAdministrador.altaAdministrador(new Administrador("a", "a", "Pedro", "Riera Cortés", LocalDate.of(1984, 2, 5), "43127814P", TipoGenero.MASCULINO, "Av. Argentina", "999198963"));
        coleccionEnfermero.altaEnfermero(new Enfermero("e", "e", "Pedro", "Riera Cortés", LocalDate.of(1984, 2, 5), "43127814P", TipoGenero.MASCULINO, "Av. Argentina", "999198963"));
        coleccionTecnico.altaTecnico(new Tecnico("t", "t", "Pedro", "Riera Cortés", LocalDate.of(1984, 2, 5), "43127814P", TipoGenero.MASCULINO, "Av. Argentina", "999198963"));
        coleccionPaciente.altaPaciente(new Paciente("Francisco", "Martinez Soria", LocalDate.of(1984, 2, 5), "11111111A", TipoGenero.MASCULINO, "Av. Argentina", true, "999198963"));
        coleccionPaciente.altaPaciente(new Paciente("Laura", "Garcia", LocalDate.of(1984, 2, 5), "22222222A", TipoGenero.FEMENINO, "Av. Argentina", false, "999198963"));
        coleccionPaciente.altaPaciente(new Paciente("Beatriz", "Ceballos", LocalDate.of(1984, 2, 5), "33333333A", TipoGenero.FEMENINO, "Av. Argentina", true, "999198963"));
        coleccionPaciente.altaPaciente(new Paciente("Sofia", "Caceres", LocalDate.of(1984, 2, 5), "44444444A", TipoGenero.FEMENINO, "Av. Argentina", false, "999198963"));
        coleccionPaciente.altaPaciente(new Paciente("Patricia", "Farfán", LocalDate.of(1984, 2, 5), "55555555A", TipoGenero.FEMENINO, "Av. Argentina", true, "999198963"));
        coleccionPaciente.altaPaciente(new Paciente("Lesly", "Abellaneda", LocalDate.of(1984, 2, 5), "666666666A", TipoGenero.FEMENINO, "Av. Argentina", false, "999198963"));
        coleccionPaciente.altaPaciente(new Paciente("Andrea", "Inga Vilchez", LocalDate.of(1984, 2, 5), "77777777A", TipoGenero.FEMENINO, "Av. Argentina", true, "999198963"));
        coleccionPaciente.altaPaciente(new Paciente("Monica", "Zuazu", LocalDate.of(1984, 2, 5), "88888888A", TipoGenero.FEMENINO, "Av. Argentina", false, "999198963"));
        coleccionPaciente.altaPaciente(new Paciente("Juana", "Juaneda", LocalDate.of(1984, 2, 5), "99999999A", TipoGenero.FEMENINO, "Av. Argentina", true, "999198963"));
        coleccionPaciente.altaPaciente(new Paciente("Isabel", "La católica", LocalDate.of(1984, 2, 5), "00000000A", TipoGenero.FEMENINO, "Av. Argentina", false, "999198963"));
        coleccionPaciente.altaPaciente(new Paciente("Mercedes", "García", LocalDate.of(1984, 2, 5), "11110000A", TipoGenero.FEMENINO, "Av. Argentina", true, "999198963"));

    }

    /**
     * Constructor con visibilidad privada pues es un singleton.
     */
    private Clinica()
    {
        this.coleccionAdministrador = new ColeccionAdministrador();
        this.coleccionEnfermero = new ColeccionEnfermero();
        this.coleccionPaciente = new ColeccionPaciente();
        this.coleccionTecnico = new ColeccionTecnico();
        this.stockVacuna = new StockVacuna();
        this.stockPrueba = new StockPrueba();

        this.planificador = new Planificador(this.coleccionPaciente,  this.coleccionTecnico, this.coleccionEnfermero,this.stockVacuna, this.stockPrueba);        
        agregarDatosPrueba();
    }

    /**
     * Loggea un usuario en el sistema.
     * @param nombreUsuario Nombre de usuario.
     * @param clave Clave del usuario.
     * @return Booleano indicando si los datos del usuario son correctos y se ha podido loggear en el sistema.
     */
    public Boolean loggearUsuario(String nombreUsuario, String clave)
    {

        //Montar factory para buscar en cada una de las colecciones?
        usuarioActivo = coleccionAdministrador.validarUsuario(nombreUsuario, clave);
        if(usuarioActivo != null)
            return true;
        else
        {
            usuarioActivo = coleccionEnfermero.validarUsuario(nombreUsuario, clave);
            if(usuarioActivo != null)
                return true;
            else
            {
                usuarioActivo = coleccionTecnico.validarUsuario(nombreUsuario, clave);
                if(usuarioActivo != null)
                    return true;
                else 
                    return false;
            }
        }
    }

    /**
     * Cierra la sesión.
     * @return Booleano indicando que si se ha cerrado la sesion.
     */
    public Boolean cerrarSesion()
    {
        usuarioActivo = null;
        return true;
    }
}
