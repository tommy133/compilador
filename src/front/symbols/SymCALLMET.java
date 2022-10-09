/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

import front.data_structures.symbol.Symbol;
import front.error.*;

import java.util.ArrayList;
import java.util.Collections;

public class SymCALLMET extends SymBase {

    private SymID ID;
    private SymIDLIST IDLIST;

    public SymCALLMET(SymID a, int[] lc) {
        super("CALLMET", 0);
        this.ID = a;

        if (!ts.exist(ID.getID())) {
            new ErrorProcNotExists().printError(lc, ID.getID());

        }

        tac.generateCode("call " + ID.getID() + "\n");
        tac.generateCode(ID.getID() + "Return" + ":skip\n");
    }

    public SymCALLMET(SymID a, SymIDLIST b, int[] lc) {
        super("CALLMET", 0);

        this.ID = a;
        this.IDLIST = b;

        //Comprovar existencia metode.

        if (!ts.exist(ID.getID())) {
            new ErrorProcNotExists().printError(lc, ID.getID());

        }

        //Carregar arguments
        ArrayList<SymID> args_call = new ArrayList();
        while (IDLIST.getIDLISTSEP() != null) {
            args_call.add(IDLIST.getID());
            IDLIST = IDLIST.getIDLISTSEP().getLID();
        }
        args_call.add(IDLIST.getID());

        ArrayList<Symbol> args_real;

        if (ts.get(ID.getID()) != null) {

            args_real = (ArrayList<Symbol>) ts.get(ID.getID()).getArgs().clone();
        } else {

            args_real = new ArrayList();

        }
        if (args_call.size() > args_real.size()) {
            new ErrorMuchParam().printError(lc, ID.getID());
        } else if (args_call.size() < args_real.size()) {
            new ErrorFewParam().printError(lc, ID.getID());
        }
        for (int i = 0; i < args_real.size(); i++) {

            if (!args_call.get(i).getType().equalsIgnoreCase(args_real.get(i).getSubtype())) {
                new ErrorArgTypes().printError(lc, ID.getID());
            }
        }

        Collections.reverse(args_call);
        for (SymID symID : args_call) {
            tac.generateCode("param " + symID + "\n");
        }

        tac.generateCode("call " + ID.getID() + "\n");
        tac.generateCode(ID.getID() + "Return" + ":skip\n");
    }


}
