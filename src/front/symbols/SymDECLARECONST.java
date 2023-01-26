/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

import front.data_structures.symbol.Symbol;
import front.error.ErrorConstExists;


import static front.data_types.Types.CONSTANT;

public class SymDECLARECONST extends SymBase {

    private final SymTYPE TYPE;
    private final SymASSIGNCONST ASSIGNCONST;

    public SymDECLARECONST(SymTYPE a, SymASSIGNCONST b, int[] lc) {
        super("DECLARECONST", 0);
        this.TYPE = a;
        this.ASSIGNCONST = b;

        Symbol sym = new Symbol(ASSIGNCONST.getID().getID(), CONSTANT, TYPE.getType(), null);

        if (!ts.existInTs(sym.getId())) {
            ts.insertElement(sym);
            tac.assign(tac.newVar(ASSIGNCONST.getID().getID(), ASSIGNCONST.getID().getType(),
                    ASSIGNCONST.getOPERANDX().getSUBTYPE().getValor()), ASSIGNCONST.getOPERANDX().getSUBTYPE().getValor());
        } else {
            new ErrorConstExists().printError(lc, sym.getId());
        }
    }

    public SymTYPE getType() {
        return TYPE;
    }


}
