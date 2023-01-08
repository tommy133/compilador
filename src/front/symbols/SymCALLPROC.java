/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

import front.data_structures.symbol.Symbol;
import front.error.ErrorArgTypes;
import front.error.ErrorFewParam;
import front.error.ErrorMuchParam;
import front.error.ErrorProcNotExists;


import java.util.ArrayList;
import java.util.Collections;


public class SymCALLPROC extends SymBase {

    private SymID ID;
    private SymIDLIST IDLIST;

    public SymCALLPROC(SymID a, int[] lc) { //NoArgs
        super("CALLPROC", 0);
        this.ID = a;

        if (!ts.exist(ID.getID())) {
            new ErrorProcNotExists().printError(lc, ID.getID());
        }

        tac.generateCode("call " + ID.getID() + "\n");

    }

    public SymCALLPROC(SymID a, SymIDLIST b, int[] lc) { //WithArgs
        super("CALLPROC", 0);
        this.ID = a;
        this.IDLIST = b;

        //Comprovar existencia

        if (!ts.exist(ID.getID())) {
            new ErrorProcNotExists().printError(lc, ID.getID());

        }

        ArrayList<SymID> args_call = new ArrayList();
        while (IDLIST.getIDLISTSEP() != null) {
            args_call.add(IDLIST.getID());
            IDLIST = IDLIST.getIDLISTSEP().getLID();
        }
        args_call.add(IDLIST.getID());

        //Carregar arguments
        ArrayList<Symbol> real_args;
        real_args = (ArrayList<Symbol>) ts.get(ID.getID()).getArgs().clone();

        if (args_call.size() > real_args.size()) {
            new ErrorMuchParam().printError(lc, ID.getID());

        } else if (args_call.size() < real_args.size()) {
            new ErrorFewParam().printError(lc, ID.getID());

        }

        for (int i = 0; i < args_call.size(); i++) {

            if (!args_call.get(i).getType().equalsIgnoreCase(real_args.get(i).getSubtype())) {
                new ErrorArgTypes().printError(lc, ID.getID());

            }
        }

        Collections.reverse(args_call);
        for (SymID symID : args_call) {
            tac.generateCode(paramType(symID) + " " + symID + "\n");
        }

        if (tac.getTemp_id() != null) {
            tac.generateCode(tac.newVar(tac.getTemp_id(), "integer") + " = ");
        }

        tac.generateCode("call " + ID.getID() + "\n");

    }

    private String paramType(SymID symID){
        if (symID.getType().equalsIgnoreCase("string")) return "param_c";
        return "param_s";
    }

    public String getType() {
        return (ts.get(ID.getID()).getSubtype());
    }


}
