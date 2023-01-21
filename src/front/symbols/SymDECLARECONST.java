/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

import front.data_structures.symbol.Symbol;
import front.error.ErrorConstExists;
import front.error.ErrorVarNotDec;


import static front.data_types.Types.CONSTANT;

public class SymDECLARECONST extends SymBase {

    private SymTYPE TYPE;
    private SymASSIGNCONST ASSIGNCONST;
    private final String place = "SymDECLARECONST";

    public SymDECLARECONST(SymTYPE a, SymASSIGNCONST b, int[] lc) throws ErrorVarNotDec, ErrorConstExists {
        super("DECLARECONST", 0);
        this.TYPE = a;
        this.ASSIGNCONST = b;

        Symbol n = new Symbol(ASSIGNCONST.getID().getID(), CONSTANT, TYPE.getType(), null);

        if (!ts.exist(n.getId())) {
            ts.insertElement(n);
            tac.assign(tac.newVar(ASSIGNCONST.getID().getID(), ASSIGNCONST.getID().getType(),
                    ASSIGNCONST.getOPERANDX().getSUBTYPE().getValor()), ASSIGNCONST.getOPERANDX().getSUBTYPE().getValor());
        } else {
            new ErrorConstExists().printError(place,lc, n.getId());
            throw new ErrorConstExists();
        }
    }

    public SymTYPE getType() {
        return TYPE;
    }


}
