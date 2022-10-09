/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

import front.error.ErrorConstAssign;
import front.error.ErrorVarNotDec;
import front.data_types.Types;


import java.util.ArrayList;
import java.util.Collections;

public class SymASSIGN extends SymBase {

    private SymID ID;
    private SymOPERANDX OPERANDX;

    public SymASSIGN(SymID a, SymOPERANDX b, int[] lc) {
        super("ASSIGN", 0);
        this.ID = a;
        this.OPERANDX = b;

        if (ts.get(ID.getID()) == null) {
            new ErrorVarNotDec().printError(lc, ID.getID());
        }

        if (ts.get(ID.getID()).getType().equals(Types.CONSTANT)) {
            new ErrorConstAssign().printError(lc, ID.getID());
        }


        ArrayList<String> operators = new ArrayList<>();
        ArrayList<String> operands = new ArrayList<>();

        while (OPERANDX.getOPARITH() != null) {
            operands.add(OPERANDX.getSUBTYPE().getName());
            operators.add(OPERANDX.getOPARITH().getType());
            OPERANDX = OPERANDX.getOPERANDX();
        }

        if (OPERANDX.getSUBTYPE().getName() != null) {
            operands.add(OPERANDX.getSUBTYPE().getName());
        } else {
            operands.add(OPERANDX.getSUBTYPE().getValor());
        }

        if (tac.isOperand()) {
            operands = (ArrayList<String>) tac.getOperands().clone();
            tac.resetOperands();
        }

        tac.setOperand(true);


        //Càlcul d'ocupacions

        Collections.reverse(operands);

        if (operands.size() > 2) {
            String temp_var = tac.newTempVar("");
            tac.generateCode(temp_var + " = " + operands.get(0) + " " + operators.get(0) + " " + operands.get(0) + "\n");
            String temp_prec_var;

            for (int i = 2; i < operands.size(); i++) {
                temp_prec_var = temp_var;
                temp_var = tac.newTempVar("");
                tac.generateCode(temp_var  + " = "  + temp_prec_var + " " + operators.get(i - 1) +  " "  +
                        operands.get(i) + "\n");
            }


            tac.generateCode(tac.newVar(ID.getID(),  ID.getType(), OPERANDX.getSUBTYPE().getValor()) +  " = " +  temp_var + "\n");

        } else if (operands.size() == 2) {

            String temp_var;
            temp_var = tac.newTempVar(ID.getType(), OPERANDX.getSUBTYPE().getValor());

            tac.generateCode(temp_var + " = ");
            tac.generateCode(operands.get(0) + " " + operators.get(0) + " " + operands.get(1) + "\n");

            tac.generateCode(tac.newVar(ID.getID(), ID.getType(), OPERANDX.getSUBTYPE().getValor()) + " = " + temp_var + "\n");

        } else if (operands.size() == 1 && operands.get(0) != null) {

            String temp_var;

            temp_var = tac.newTempVar(ID.getType(), OPERANDX.getSUBTYPE().getValor());

            tac.generateCode(temp_var + " = ");
            tac.generateCode(operands.get(0) + "\n");

            tac.generateCode(tac.newVar(ID.getID(), ID.getType(), OPERANDX.getSUBTYPE().getValor()) + " = " + temp_var + "\n");
        }
        tac.setTemp_id(null);
    }

}
