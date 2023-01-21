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

    private SymTYPE TYPE;
    private SymIDLIST IDLIST;
    private final String place = "SymDECVAR";
    public SymDECVAR(SymTYPE a, SymIDLIST b, int[] lc) throws ErrorVarExists {
        super("DVAR", 0);

        this.TYPE = a;
        this.IDLIST = b;

        while (IDLIST.getIDLISTSEP() != null) {
            Symbol n = new Symbol(IDLIST.getID().getID(), VARIABLE, TYPE.getType(),null, null);
            if (!ts.exist(n.getId())) {
                ts.insertElement(n);
            } else {
                new ErrorVarExists().printError(place,lc, IDLIST.getID().getID());
                throw new ErrorVarExists();
            }
            IDLIST = IDLIST.getIDLISTSEP().getLID();
        }
        if (!ts.exist(IDLIST.getID().getID())) {
            ts.insertElement(new Symbol(IDLIST.getID().getID(), VARIABLE, TYPE.getType(), null,null));
        } else {
            new ErrorVarExists().printError(place,lc, IDLIST.getID().getID());
            throw new ErrorVarExists();
        }

    }


}
