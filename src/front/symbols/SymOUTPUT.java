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

        tac.generateCode(paramType()+" " + a.getVALUELIST() + "\n");
        tac.generateCode("call print\n");

        tac.setTemp_id(null);
    }

    private String paramType(){
        if (ts.get(PRINT.getID().getID()).getSubtype().equalsIgnoreCase("string")) return "param_c";
        return "param_s";
    }

}
