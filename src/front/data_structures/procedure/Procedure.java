package front.data_structures.procedure;

import back.data_structures.Parametro;

import java.util.ArrayList;

public class Procedure {

    private int num_proc;
    private int depth;
    private String start_label;
    private int n_params;
    private ArrayList<Parametro> parametros;
    private int total_store;


    public Procedure(int num_proc, int depth, String start_label, int n_params, int total_store) {
        this.num_proc = num_proc;
        this.depth = depth;
        this.start_label = start_label;
        this.n_params = n_params;
        this.total_store = total_store;
    }

    public int getNum_proc() {
        return num_proc;
    }

    public int getDepth() {
        return depth;
    }

    public int getN_params() {
        return n_params;
    }

    public ArrayList<Parametro> getParametros() { return parametros; }

    public int getTotal_store() {
        return total_store;
    }

    public void setTotal_store(int total_store) {
        this.total_store = total_store;
    }

    public String getStart_label() {
        return start_label;
    }
}
