/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

import front.data_structures.symbol.Symbol;
import front.error.ErrorVarExists;


import static front.data_types.Types.VARIABLE;

public class SymDECVAR extends SymBase {

    private final SymTYPE TYPE;
    private SymIDLIST IDLIST;

    public SymDECVAR(SymTYPE a, SymIDLIST b, int[] lc) {
        super("DVAR", 0);

        this.TYPE = a;
        this.IDLIST = b;

        while (IDLIST.getIDLISTSEP() != null) {
            Symbol sym = new Symbol(IDLIST.getID().getID(), VARIABLE, TYPE.getType(),null);
            if (!ts.existInTs(sym.getId())) {
                ts.insertElement(sym);
            } else {
                new ErrorVarExists().printError(lc, IDLIST.getID().getID());
            }
            IDLIST = IDLIST.getIDLISTSEP().getLID();
        }
        if (!ts.existInTs(IDLIST.getID().getID())) {
            ts.insertElement(new Symbol(IDLIST.getID().getID(), VARIABLE, TYPE.getType(), null));
        } else {
            new ErrorVarExists().printError(lc, IDLIST.getID().getID());
        }

    }


}
