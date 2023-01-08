/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

import front.data_structures.symbol.Symbol;



import static front.data_types.Types.VARIABLE;

public class SymARGLIST extends SymBase {

    private SymARG ARG;
    private SymARGLIST ARGLIST;

    public SymARGLIST() {
        super("ARGLIST", 0);
    }

    public SymARGLIST(SymARG a) {
        super("ARGLIST", 0);
        this.ARG = a;
        ts.addParams(new Symbol(ARG.getID().getID(), VARIABLE, ARG.getType().getType(), null,null));
    }

    public SymARGLIST(SymARG a, SymARGLIST b) {
        super("ARGLIST", 0);
        this.ARG = a;
        this.ARGLIST = b;
        ts.addParams(new Symbol(ARG.getID().getID(), VARIABLE, ARG.getType().getType(), null,null));
    }

}
