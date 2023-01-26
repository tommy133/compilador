package front.symbols;

import back.data_structures.Parametro;
import front.data_structures.procedure.Procedure;
import front.data_structures.symbol.Symbol;
import front.data_types.TypeSub;

import java.util.ArrayList;


public class SymPMB extends SymBase {

    //Genera el preamble de cada metode/funcio

    public SymPMB() {
        super("PMB", 0);

        String startLabel = tac.getTemp_id();
        tac.setTemp_id(null);
        tp.addRow(new Procedure(tp.getNewNumProc(), 0, startLabel, getParams(), 0, TypeSub.NULL));
        tac.generateCode("pmb " + startLabel + "\n");
    }

    private ArrayList<Parametro> getParams() {
        ArrayList<Symbol> paramSymbol = ts.getParams();
        ArrayList<Parametro> params = new ArrayList<>();

        for (Symbol symbol : paramSymbol) {
            TypeSub enum_type = TypeSub.valueOf(symbol.getSubtype().toUpperCase());
            params.add(new Parametro(symbol.getId(), enum_type));
        }

        return params;
    }
}
