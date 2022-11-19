/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

import front.data_structures.symbol.Symbol;
import front.error.ErrorProcExists;
import java.util.ArrayList;

import static front.data_types.Types.FUNCTION;
import static front.data_types.Types.METHOD;

public class SymDECMET extends SymBase {

    private SymID ID;
    private SymARGLIST ARGLIST;
    private SymSENTLIST SENTLIST;

    public SymDECMET(SymID a, SymARGLIST b, SymSENTLIST c, int[] lc) {
        super("F", 0);

        this.ID = a;
        this.ARGLIST = b;
        this.SENTLIST = c;

        Symbol n = new Symbol(ID.getID(), METHOD, "NULL", null, (ArrayList<Symbol>) ts.getParams().clone());

        if (ts.exist(n.getId())) {

            Symbol node = ts.get(n.getId());

            if (node.getType() == METHOD || node.getType() == FUNCTION) {
                new ErrorProcExists().printError(lc, n.getId());
            } else {
                ts.insertElement(n);
            }
        } else {
            ts.insertElement(n);
        }
        ts.emptyParams();

    }

}
