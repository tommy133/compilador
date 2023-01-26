/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;


public class SymCONDELSE extends SymBase {

    private SymSENTLIST SENTLIST;

    public SymCONDELSE(SymSENTLIST a) {
        super("F", 0);
        this.SENTLIST = a;
        String end_label = tac.getTop(tac.getEnd_stack());
        tac.pop(tac.getFalse_stack());
        tac.generateCode(end_label + ":skip\n");
    }

    public SymCONDELSE() {
        super("F", 0);
        tac.removeGotoElse();
    }

}
