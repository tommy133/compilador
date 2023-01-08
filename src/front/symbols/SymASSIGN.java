/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

import front.data_structures.symbol.Symbol;
import front.data_structures.variable.VariableTable;
import front.data_types.Subrange;
import front.error.ErrorConstAssign;
import front.error.ErrorVarExists;
import front.error.ErrorVarNotDec;
import front.data_types.Types;


import java.util.ArrayList;
import java.util.Collections;

import static front.data_types.Types.VARIABLE;

public class SymASSIGN extends SymBase {

    private SymID ID;
    private SymIDARRAY IDARRAY;
    private SymOPERANDX OPERANDX;
    private SymTYPE TYPE;

    private ArrayList<String> operators = new ArrayList<>();
    private ArrayList<String> operands = new ArrayList<>();

    public SymASSIGN(SymID a, SymOPERANDX b, int[] lc) {
        super("ASSIGN", 0);
        this.ID = a;
        this.OPERANDX = b;

        if (ts.get(ID.getID()) == null) {
            new ErrorVarNotDec().printError(lc, ID.getID());
        }

        else if (ts.get(ID.getID()).getType().equals(Types.CONSTANT)) {
            new ErrorConstAssign().printError(lc, ID.getID());
        }


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
        if (OPERANDX.getSUBTYPE().isArray()){
            calcOcupArray(false);
        } else {
            calcOcup();
        }


    }

    public SymASSIGN(SymIDARRAY a, SymOPERANDX b, int[] lc) {
        super("ASSIGN", 0);
        this.IDARRAY = a;
        this.OPERANDX = b;

        if (ts.get(IDARRAY.getID().getID()) == null) {
            new ErrorVarNotDec().printError(lc, IDARRAY.getID().getID());
        }

        operands.add(OPERANDX.getSUBTYPE().getValor());

        calcOcupArray(true);

    }

    public SymASSIGN(SymTYPE a, SymID b, SymOPERANDX c, int[] lc) {
        super("ASSIGN", 0);
        this.TYPE = a;
        this.ID = b;
        this.OPERANDX = c;

        Symbol n = new Symbol(ID.getID(), VARIABLE, TYPE.getType(),null, null); //TODO Subranges?
        if (!ts.exist(n.getId())) {
            ts.insertElement(n);
        } else {
            new ErrorVarExists().printError(lc, ID.getID());
        }

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
        if (OPERANDX.getSUBTYPE().isArray()){
            calcOcupArray(false);
        } else {
            calcOcup();
        }
    }

    private void calcOcup(){
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

    private void calcOcupArray(boolean left){//fórmula apunts
        Symbol array;
        if (left){
            array = ts.get(IDARRAY.getID().getID());
        } else {
            array = ts.get(OPERANDX.getSUBTYPE().getIDARRAY().getID().getID());
        }

        int b = array.getB();
        int nbytes = new VariableTable().calculateStore(array.getSubtype(),"");
        String temp_var="", temp_var_post="", temp_t5="", temp_t6="", temp_t7="";
        ArrayList<Subrange> subranges = array.getSubranges();
        int dk=0, t1=0, t2=0, t5=0, t6=0;
        if (operands.size() > 1) operands.remove(0);
        int idx = Integer.parseInt(operands.get(0)); //obtenir el primer desplaçament

        if (operands.size() > 1){
            for (int i=1; i < operands.size(); i++){
                if (i>1) idx=t2;
                dk = subranges.get(i).getVal2() - subranges.get(i).getVal1() + 1;
                t1 = (idx*dk);
                temp_var = tac.newTempVar("integer", ""+t1);

                tac.generateCode(temp_var + " = ");
                tac.generateCode(((i==1)?(idx) : temp_var_post) + " * " + dk +"\n");

                idx = Integer.parseInt(operands.get(i));

                t2 = (t1 + idx);
                temp_var_post = tac.newTempVar("integer", ""+t2);
                tac.generateCode(temp_var_post + " = ");
                tac.generateCode(temp_var + " + "+ idx + "\n");
            }
            t5 = t2 - b;
            temp_t5 = tac.newTempVar("integer", ""+t5);
            tac.generateCode(temp_t5 + " = ");
            tac.generateCode(temp_var_post + " - "+ b + "\n");

            t6 = t5 * nbytes;
            temp_t6 = tac.newTempVar("integer", ""+t6);
            tac.generateCode(temp_t6 + " = ");
            tac.generateCode(temp_t5 + " * "+ nbytes + "\n");

            temp_t7 = tac.newTempVar("integer");
            tac.generateCode(temp_t7 + " = "+ array.getId()+"["+temp_t6+"]"+ "\n");
        } else {
            t1 = idx - subranges.get(0).getVal1();
            temp_var = tac.newTempVar("integer", ""+t1);
            tac.generateCode(temp_var + " = " + idx + " - " + subranges.get(0).getVal1()+ "\n");
            temp_var_post = tac.newTempVar("integer", ""+ t1*nbytes);
            tac.generateCode(temp_var_post + " = " + temp_var +" * " + nbytes+ "\n");

            temp_t7 = tac.newTempVar("integer");
            tac.generateCode(temp_t7 + " = "+ array.getId()+"["+temp_var_post+"]"+ "\n");
        }
//        if (left){ //part esquerra es array
//            tac.generateCode(array.getId()+"["+temp_t7+"]"+ " = "+ tac.newVar(OPERANDX.getSUBTYPE().getID().getID(), OPERANDX.getSUBTYPE().getType(), OPERANDX.getSUBTYPE().getValor()) );
//        } else {
//            tac.generateCode(tac.newVar(ID.getID(), ID.getType()) + " = " + temp_t7 + "\n");
//        }

    }

}
