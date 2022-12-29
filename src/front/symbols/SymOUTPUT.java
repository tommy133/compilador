/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

import front.data_structures.symbol.Symbol;

public class SymOUTPUT extends SymBase {

    private SymPRINT PRINT;

    public SymOUTPUT(SymPRINT a, boolean println) {
        super("OUTPUT", 0);
        this.PRINT = a;

        tac.generateCode(paramType()+" " + a.getVALUELIST() + "\n");
        tac.generateCode("call "+((println)?"line" : "print")+"\n");

        tac.setTemp_id(null);
    }

    private String paramType(){
        Symbol print_id = ts.get(PRINT.getID().getID());
        if (print_id == null){
            //TODO print constant
        }
        if (print_id.getSubtype().equalsIgnoreCase("string")) return "param_c";
        return "param_s";

    }

}
