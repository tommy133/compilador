/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

import front.data_structures.symbol.Symbol;


import static front.data_types.Types.MAINPROG;

public class SymDECMP extends SymBase {

    private SymID ID;
    private SymARGLIST ARGLIST;
    private SymDECCONSTLIST DECCONSTLIST;
    private SymSENTLIST SENTLIST;

    public SymDECMP(SymID a, SymARGLIST b, SymDECCONSTLIST c, SymSENTLIST d) {
        super("F", 0);
        this.ID = a;
        this.ARGLIST = b;
        this.DECCONSTLIST = c;
        this.SENTLIST = d;

        ts.insertElement(new Symbol("program", MAINPROG, "NULL", ts.getParams()));
        ts.emptyParams();
    }


}
