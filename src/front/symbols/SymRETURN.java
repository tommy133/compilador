/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;


import front.error.ErrorVarNotDec;

public class SymRETURN extends SymBase {

    private SymRETURNTYPE RETURNTYPE;

    public SymRETURN(SymRETURNTYPE a) throws ErrorVarNotDec {
        super("F", 0);
        this.RETURNTYPE = a;

        tac.pop(tac.getStart_stack());
        tac.setTemp_id(null);
        tac.generateCode("rtn "+ tac.getCur_prog() + " " + RETURNTYPE.getID().getID() + "\n\n"); //TODO retorn de constant
        tac.resetDisplacement();
    }

    public SymRETURNTYPE getRETURNTYPE() {
        return RETURNTYPE;
    }
}
