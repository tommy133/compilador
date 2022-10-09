package front.data_structures.symbol;

import front.data_types.Types;

import java.util.ArrayList;


public class Symbol {

    private String id;
    private Types type;
    private String subtype;
    private ArrayList<Symbol> args;

    public Symbol(String id, Types types, String subtype, ArrayList<Symbol> args) {
        this.id = id;
        this.type = types;
        this.subtype = subtype;
        this.args = args;
    }

    public void setId(String a) {
        id = a;
    }

    public String getId() {
        return id;
    }

    public Types getType() {
        return type;
    }

    public String getSubtype() {
        return subtype;
    }

    public ArrayList<Symbol> getArgs() {
        return args;
    }
}
