package front.data_structures.symbol;

import front.data_types.Subrange;
import front.data_types.Types;

import java.util.ArrayList;


public class Symbol {

    private String id;
    private Types type;
    private String subtype;
    private ArrayList<Subrange> subranges;
    private ArrayList<Symbol> args;
    private int b; //base del desplaçament del array coneguda en temps de compilació

    public Symbol(String id, Types types, String subtype, ArrayList<Subrange> subranges, ArrayList<Symbol> args) {
        this.id = id;
        this.type = types;
        this.subtype = subtype;
        this.subranges = subranges;
        this.args = args;
        this.b = (subranges!=null)? calcBase(subranges.size()-1) : 0;
    }

    private int calcBase(int i){
        int lik = subranges.get(i).getVal1(); //limit inferior
        int lfk = subranges.get(i).getVal2(); //limit superior

        int dk = lfk - lik + 1; //longitud dimensio

        if (i == 0) return lik;
        return lik + dk * calcBase(i-1);

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

    public ArrayList<Subrange> getSubranges() {
        return subranges;
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
