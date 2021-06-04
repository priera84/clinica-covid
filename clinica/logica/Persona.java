package clinica.logica; 
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
/**
 * Abstract class usuario - Clase abstracta que define a cualquier persona del sistema.
 *
 * @author Pedro Riera
 * @version 1.0.0.0
 */
public abstract class Persona
{
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String dni;
    private TipoGenero genero;
    private String direccion;
    private String telefono;

    /**
     * Constructor de la clase
     * @param  nombre   Nombre de la persona.
     * @param  apellidos   Apellidos de la persona.
     * @param  fechaNacimiento Fecha de nacimiento de la persona.
     * @param  dni   Documento Nacional de Identidad de la persona.
     * @param  genero   Género de la persona.
     * @param  direccion Direcciòn de la persona.
     * @param  telefono Teléfono de la persona.
     */
    public Persona(String nombre, String apellidos, LocalDate fechaNacimiento, String dni, TipoGenero genero, String direccion, String telefono)
    {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.dni = dni;
        this.genero = genero;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    /**
     * Devuelve el nombre de la persona.
     *
     * @return  El nombre de la persona.
     */
    public String getNombre()
    {
        // put your code here
        return nombre;
    }

    /**
     * Asigna el nombre a la persona.
     * @param nombre de la persona.
     */    
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * Devuelve los apellidos de la persona.
     * @return Los apellidos de la persona.
     */
    public String getApellidos()
    {
        return apellidos;
    }

    /**
     * Asigna los apellidos de la persona.
     * @param apellidos Los apellidos de la persona.
     */
    public void setApellidos(String apellidos)
    {
        this.apellidos = apellidos;
    }   

    /**
     * Devuelve el dni de la persona.
     * @return El dni de la persona.
     */
    public String getDni()
    {
        return this.dni;
    }

    /**
     * Devuelve una cadena con la descripción de la persona.
     * @return Una cadena con la descripción de la persona.
     */
    public String getDescripcion()
    {
        StringBuilder sbDescripcion = new StringBuilder();
        sbDescripcion.append("Nombre: " + this.nombre);
        sbDescripcion.append("\nApellidos: " + this.apellidos);
        sbDescripcion.append("\nFecha de Nacimiento: " + this.fechaNacimiento.toString());
        sbDescripcion.append("\nEdad: " + String.valueOf(this.getEdadPersona()));
        sbDescripcion.append("\nGenero: " + this.genero);
        sbDescripcion.append("\nDNI: " + this.dni);
        sbDescripcion.append("\nDirección: " + this.direccion);
        sbDescripcion.append("\nTeléfono: " + this.telefono);
        return sbDescripcion.toString();
    }

    /**
     * Método que se utiliza por el método contains del ArrayList para comparar objetos la lista.
     * Sobreescribiendo este método se puede modificar el criterio por el cual dos objetos se consideran iguales.
     * En este caso nos interesa comparar personas en base a su DNI.
     * @param objeto de tipo persona con el que se compara.
     * @return si es igual o no, en este caso si tiene el mismo DNI.
     */
    @Override
    public boolean equals(Object objeto) {
        boolean resultado = false;

        if (objeto instanceof Persona){
            Persona persona = (Persona) objeto;
            resultado = this.dni.equals(persona.dni);
        }

        return resultado;
    }

    /**
     * Método que calcula la edad de la persona.
     * @return la edad de la persona.
     */
    public long getEdadPersona()
    {
        LocalDate ahora = LocalDate.now(ZoneId.of("Europe/Madrid"));
        return java.time.temporal.ChronoUnit.YEARS.between( fechaNacimiento , ahora );
    }
    
    /**
     * Método que actualiza la dirección
     * @param direccion Dirección a asignar al campo dirección
     */
    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }
}