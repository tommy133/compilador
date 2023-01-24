package front.data_structures.procedure;

import back.data_structures.Parametro;
import front.data_types.TypeSub;

import java.util.ArrayList;

public class Procedure {

    private final int num_proc;
    private final int depth;
    private final String start_label;
    private final ArrayList<Parametro> parametros;
    private int total_store;
    private TypeSub type_return;

    public Procedure(int num_proc, int depth, String start_label, ArrayList<Parametro> parametros, int total_store, TypeSub type_return) {
        this.num_proc = num_proc;
        this.depth = depth;
        this.start_label = start_label;
        this.parametros = parametros;
        this.total_store = total_store;
        this.type_return = type_return;
    }

    public int getNum_proc() {
        return num_proc;
    }

    public int getDepth() {
        return depth;
    }

    public ArrayList<Parametro> getParametros() { return parametros; }

    public int getTotal_store() {
        return total_store;
    }

    public void setTotal_store(int total_store) {
        this.total_store = total_store;
    }

    public void setType_return(TypeSub type_return) {
        this.type_return = type_return;
    }

    public String getStart_label() {
        return start_label;
    }

    public TypeSub getType_return() {
        return type_return;
    }
}
