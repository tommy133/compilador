package back.data_structures;

import front.data_types.TypeSub;

public class Parametro {
    private String nombre;
    private TypeSub tipo;

    public Parametro(String nombre, TypeSub tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public TypeSub getTipo() {
        return tipo;
    }
}
