/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

public class SymOUTPUT extends SymBase {

    private SymPRINT PRINT;

    public SymOUTPUT(SymPRINT a) {
        super("OUTPUT", 0);
        this.PRINT = a;

        if (a != null) {
            if (ts.get(a.getID().getID()).getSubtype().equalsIgnoreCase("logic")) {
                tac.generateCode("param " + a.getVALUELIST() + "\n");
                tac.generateCode("call printLogic\n");
            } else {
                tac.generateCode("param " + a.getVALUELIST() + "\n");
                tac.generateCode("call print\n");
            }
        } else {
            tac.generateCode("call printLogic\n");
        }
        tac.setTemp_id(null);
    }


}
