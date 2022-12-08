package front.symbols;

import back.data_structures.Parametro;
import front.data_structures.procedure.Procedure;
import front.data_structures.symbol.Symbol;
import front.data_types.TypeSub;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;


public class SymPMB extends SymBase {

    //Genera el preamble de cada metode/funcio

    public SymPMB() {
        super("PMB", 0);

        String startLabel = tac.getTemp_id();
        tac.setTemp_id(null);
        procedureTable.addRow(new Procedure(procedureTable.getNewNumProc(), 0, startLabel, getParams(), 0, TypeSub.NULL));
        tac.generateCode("pmb " + startLabel + "\n");
    }

    private ArrayList<Parametro> getParams() {
        ArrayList<Symbol> paramSymbol = ts.getParams();
        ArrayList<Parametro> params = new ArrayList<>();

        for (int i=0; i<paramSymbol.size(); i++) {
            TypeSub enum_type = TypeSub.valueOf(paramSymbol.get(i).getSubtype().toUpperCase());
            params.add(new Parametro(paramSymbol.get(i).getId(), enum_type));
        }

        return params;
    }
}
