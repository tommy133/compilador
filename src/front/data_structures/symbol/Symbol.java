package front.data_structures.symbol;

import front.data_structures.Dimension;
import front.data_types.Types;

import java.util.ArrayList;


public class Symbol {

    private String id;
    private Types type;
    private String subtype;
    private ArrayList<Dimension> dimensions;
    private ArrayList<Symbol> args;
    private int b; //base del desplaçament del array coneguda en temps de compilació
    private int length; //nombre d'elements si es tracta d'un array

    public Symbol(String id, Types types, String subtype, ArrayList<Symbol> args) {
        this.id = id;
        this.type = types;
        this.subtype = subtype;
        this.args = args;
    }

    public Symbol(String id, Types types, String subtype, ArrayList<Dimension> dimensions, ArrayList<Symbol> args) {
        this.id = id;
        this.type = types;
        this.subtype = subtype;
        this.dimensions = dimensions;
        this.b = (this.dimensions !=null)? calcBase(this.dimensions.size()-1) : 0;
        this.length = (dimensions !=null)? calcLength() : 0;
    }

    private int calcBase(int i){
        int lik = dimensions.get(i).getSubrange().getVal1();
        int lfk = dimensions.get(i).getSubrange().getVal2();

        int dk = lfk - lik + 1; //longitud dimensio

        if (i == 0) return lik;
        return lik + dk * calcBase(i-1);
    }

    private int calcLength(){
        int len=0;
        for (Dimension dim : dimensions){
            len += dim.getSubrange().getVal2() + 1;
        }
        return len;
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

    public int getLength() {
        return length;
    }

    public ArrayList<Dimension> getDimensions() {
        return dimensions;
    }

    public ArrayList<Symbol> getArgs() {
        return args;
    }

    public int getB() {
        return b;
    }

    @Override
    public String toString() {
        return id;
    }
}
