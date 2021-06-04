package clinica.logica; 

import java.time.LocalDate;

/**
 * La clase usuario es la superclase de los usuarios del sistema.
 *
 * @author Pedro Riera
 * @version 1.0
 */
public abstract class Usuario extends Persona
{
    private String nombreUsuario;
    private String clave;
    private TipoUsuario tipoUsuario;
    
    /**
     * Constructor de la clase
     * @param  nombreUsuario Nombre del usuario
     * @param  clave Clave de acceso al sistema del usuario.
     * @param  tipoUsuario Indica el tipo de usuario del sistema (TECNICO, ENFERMERO, O ADMINISTRADOR)
     * @param  nombre   Nombre de la persona.
     * @param  apellidos   Apellidos de la persona.
     * @param  fechaNacimiento Fecha de nacimiento de la persona.
     * @param  dni   Documento Nacional de Identidad de la persona.
     * @param  genero   Género de la persona.
     * @param  direccion Direcciòn de la persona.
     * @param  telefono Teléfono de la persona.
     */   
    public Usuario(String nombreUsuario, String clave, TipoUsuario tipoUsuario, String nombre, String apellidos, LocalDate fechaNacimiento, String dni, TipoGenero genero, String direccion, String telefono)
    {
        super(nombre, apellidos, fechaNacimiento, dni, genero, direccion, telefono);
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
        this.tipoUsuario = tipoUsuario;
    }
    
    /**
     * Devuelve el tipo de usuario.
     * @return El tipo de usuario (TECNICO, ENFERMERO, O ADMINISTRADOR)
     */
    public TipoUsuario getTipoUsuario()
    {
        return this.tipoUsuario;
    }
    
    /**
     * Función que devuelve el nombre de usuario.
     * @return El nombre de usuario.
     */    
    public String getNombreUsuario()
    {
        return this.nombreUsuario;
    }
    
    /**
     * Valida que el nombre de usuario y la clave introducida coincide con la registrada por el usuario.
     * Permite loggearse en el sistema.
     * @param nombreUsuario nombre del usuario.
     * @param clave clave del usuario.
     * @return booleano que indica si es valido el login de usuario.
     */    
    public Boolean validarUsuario(String nombreUsuario, String clave)
    {
        return this.nombreUsuario.equals(nombreUsuario) && this.clave.equals(clave);
    }  
    
    /**
     * Método abstracto para devolver los pacientes asignados.
     */
    public abstract ColeccionPaciente getPacientesAsignados();

    /**
     * Método que genera una descripción del usuario.
     * @return La cadena con la descripción del usuario.
     */
    public String getDescripcion()
    {
        return "Nombre usuario: " + this.nombreUsuario + "\nTipo usuario: " + this.tipoUsuario.toString() + "\n" + super.getDescripcion() ;
    }
    
    /**
     * Método que se utiliza por el método contains del ArrayList para comparar objetos la lista.
     * Sobreescribiendo este método se puede modificar el criterio por el cual dos objetos se consideran iguales.
     * En este caso nos interesa comparar en base a su nombre de usuario.
     * @param objeto de tipo usuario con el que se compara.
     * @return si es igual o no, en este caso si tiene el mismo nombre de usuario.
     */
    @Override
    public boolean equals(Object objeto) {
        boolean resultado = false;

        if (objeto instanceof Usuario){
            Usuario usuario = (Usuario) objeto;
            resultado = this.nombreUsuario == usuario.nombreUsuario;
        }

        return resultado;
    }
}
