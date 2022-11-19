package front.data_structures.symbol;

import front.data_types.Subrange;
import front.data_types.Types;

import java.util.ArrayList;


public class Symbol {

    private String id;
    private Types type;
    private String subtype;
    private Subrange subrange;
    private ArrayList<Symbol> args;

    public Symbol(String id, Types types, String subtype, Subrange subrange, ArrayList<Symbol> args) {
        this.id = id;
        this.type = types;
        this.subtype = subtype;
        this.subrange = subrange;
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

    public Subrange getSubrange() {
        return subrange;
    }

    public ArrayList<Symbol> getArgs() {
        return args;
    }
}
