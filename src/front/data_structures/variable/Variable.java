package front.data_structures.variable;

public class Variable {

    private String name;
    private int n_var;
    private String subprog;
    private int store;
    private int offset;
    private String type;
    private String value;

    public Variable(String name, int n_var, String subprog, int store, int offset, String type, String value) {
        this.name = name;
        this.n_var = n_var;
        this.subprog = subprog;
        this.store = store;
        this.offset = offset;
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getN_var() {
        return n_var;
    }

    public String getSubprog() {
        return subprog;
    }

    public int getStore() {
        return store;
    }

    public int getOffset() {
        return offset;
    }

    public String getValue() {
        return value;
    }

    public String getType() {
        return type;
    }
}
