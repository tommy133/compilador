/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

import front.data_structures.symbol.Symbol;
import front.error.ErrorVarNotDec;

public class SymOUTPUT extends SymBase {

    private SymPRINT PRINT;
    private String place = "SymOUTPUT";
    private int[] lc;
    public SymOUTPUT(SymPRINT a, boolean println, int[] lc) throws ErrorVarNotDec {
        super("OUTPUT", 0);
        this.PRINT = a;
        this.lc = lc;
        if(PRINT == null){
            new ErrorVarNotDec().printError(place,lc, "");
            throw new ErrorVarNotDec();
        }
        tac.generateCode(paramType()+" " + a.getVALID() + "\n");
        tac.generateCode("call "+((println)?"line" : "print")+"\n");

        tac.setTemp_id(null);
    }

    private String paramType() throws ErrorVarNotDec {
        String val_id = PRINT.getVALID();
        Symbol id = ts.get(val_id);

        if (id != null){
            if (id.getSubtype().equalsIgnoreCase("string")) return "param_c";
            else return "param_s";
        }
        if(PRINT.getVALUELIST() == null){
            new ErrorVarNotDec().printError(place,lc, this.PRINT.getVALID());
            throw new ErrorVarNotDec();
        }
        if (PRINT.getVALUELIST().getVALUE().getType().equalsIgnoreCase("string")) return "param_c";
        else return "param_s";

    }

}
