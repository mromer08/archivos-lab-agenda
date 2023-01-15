package com.mrojas;

public class Contacto implements Comparable<Contacto>{
    private static int max_nombre = 10, max_correo = 10, max_red_social = 10;
    private String nombre, telefono, correo, red_social;

    public Contacto(String nombre, String telefono, String correo, String red_social) {

        this.nombre = nombre.toUpperCase();
        setTelefono(telefono);
        setCorreo(correo);
        setRed_social(red_social);
        if (nombre.length() > max_nombre) max_nombre = nombre.length();

    }

    public Contacto(String nombre, String telefono, String correo) {
        this(nombre, telefono, correo, null);
    }

    public Contacto(String nombre, String telefono) {
        this(nombre, telefono, null, null);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        telefono = telefono.replaceAll("(\\+|[ ])*", "");
        if (telefono.length() > 8)
            telefono = telefono.substring(3, telefono.length());
        telefono = "+502 ".concat(telefono.substring(0, 4)).concat(" ").concat(telefono.substring(4, 8));
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo != null ? correo.toLowerCase() : null;
        if (correo != null && correo.length() > max_correo) max_correo = correo.length();
    }

    public String getRed_social() {
        return red_social;
    }

    public void setRed_social(String red_social) {
        this.red_social = red_social != null ? red_social.toLowerCase() : null;
        if (red_social != null && red_social.length() > max_red_social) max_red_social = red_social.length();
    }

    public static int getMax_nombre() {
        return max_nombre;
    }

    public static int getMax_correo() {
        return max_correo;
    }

    public static int getMax_red_social() {
        return max_red_social;
    }

    @Override
    public int compareTo(Contacto arg0) {
        return nombre.compareTo(arg0.getNombre());
    }

    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Contacto other = (Contacto) obj;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("|%-".concat(Integer.toString(max_nombre+1)).concat("s|%-15s|%-")
        .concat(Integer.toString(max_correo+1)).concat("s|%-").concat(Integer.toString(max_red_social+1)).concat("s|")
        ,nombre,telefono,correo != null ? correo : "---",red_social != null ? red_social : "---");
    }
      
}
