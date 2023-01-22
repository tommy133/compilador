/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

import front.data_structures.Dimension;
import front.data_structures.symbol.Symbol;
import front.data_structures.variable.VariableTable;
import front.data_types.TypeSub;
import front.error.*;
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

    public SymASSIGN(SymID a, SymOPERANDX b, int[] lc) throws SintaxErrorException {
        super("ASSIGN", 0);
        this.ID = a;
        this.OPERANDX = b;
        if(this.OPERANDX == null) {
            new ErrorFewParam().printError(lc, "");
            throw new ErrorFewParam();
        }

        if(!this.ID.getType().equalsIgnoreCase(this.OPERANDX.getSUBTYPE().getType())){
            new ErrorArgTypes().printError(lc, this.ID.getID());
            throw new ErrorArgTypes();
        }

        if (ts.get(ID.getID()) == null) {
            new ErrorVarNotDec().printError(lc, ID.getID());
            throw new ErrorVarNotDec();
        }

        else if (ts.get(ID.getID()).getType().equals(Types.CONSTANT)) {
            new ErrorConstAssign().printError(lc, ID.getID());
            throw new ErrorConstAssign();
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

//        if (tac.isOperand()) {
//            operands = (ArrayList<String>) tac.getOperands().clone();
//            tac.resetOperands();
//        }
//
//        tac.setOperand(true);


        //Càlcul d'ocupacions
        if (OPERANDX.getSUBTYPE().isArray()){
            calcOcupArray(false);
        } else {
            calcOcup();
        }


    }

    public SymASSIGN(SymIDARRAY a, SymOPERANDX b, int[] lc) throws ErrorVarNotDec, ErrorArgTypes {
        super("ASSIGN", 0);
        this.IDARRAY = a;
        this.OPERANDX = b;

        if (ts.get(IDARRAY.getID().getID()) == null) {
            new ErrorVarNotDec().printError(lc, IDARRAY.getID().getID());
        }
        if(!ts.get(IDARRAY.getID().getID()).getSubtype().equalsIgnoreCase(this.OPERANDX.getSUBTYPE().getType())){
            new ErrorArgTypes().printError(lc, IDARRAY.getID().getID());
            throw new ErrorArgTypes();
        }

        operands.add(OPERANDX.getSUBTYPE().getValor());

        calcOcupArray(true);

    }

    public SymASSIGN(SymTYPE a, SymID b, SymOPERANDX c, int[] lc) throws ErrorVarNotDec, ErrorArgTypes, ErrorVarExists {
        super("ASSIGN", 0);
        this.TYPE = a;
        this.ID = b;
        this.OPERANDX = c;

        if(this.OPERANDX == null){
            new ErrorArgTypes().printError(lc, this.ID.getID());
            throw new ErrorArgTypes();
        }
        if(!this.TYPE.getType().equalsIgnoreCase(this.OPERANDX.getSUBTYPE().getType())) {
            new ErrorArgTypes().printError(lc, this.ID.getID());
            throw new ErrorArgTypes();
        }

        Symbol n = new Symbol(ID.getID(), VARIABLE, TYPE.getType(),null); //TODO Subranges?
        if (!ts.exist(n.getId())) {
            ts.insertElement(n);
        } else {
            new ErrorVarExists().printError(lc, ID.getID());
            throw new ErrorVarExists();
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

//        if (tac.isOperand()) {
//            operands = (ArrayList<String>) tac.getOperands().clone();
//            tac.resetOperands();
//        }
//
//        tac.setOperand(true);


        //Càlcul d'ocupacions
        if (OPERANDX.getSUBTYPE().isArray()){
            calcOcupArray(false);
        } else {
            calcOcup();
        }
    }

    private void calcOcup() throws ErrorVarNotDec {
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

    private void calcOcupArray(boolean left) throws ErrorVarNotDec {//fórmula apunts
        Symbol array;
        ArrayList<Dimension> dim;

        if (left){
            array = ts.get(IDARRAY.getID().getID());
            dim = setIdxs(array.getDimensions(), IDARRAY.getIdxs());
        } else {
            array = ts.get(OPERANDX.getSUBTYPE().getIDARRAY().getID().getID());
            dim = setIdxs(array.getDimensions(), OPERANDX.getSUBTYPE().getIDARRAY().getIdxs());
        }


        int b = array.getB();
        int nbytes = new VariableTable().calculateStore(array.getSubtype(),"");

        int tn=0, tn1=0, tn2=0, tn3=0; //tn, tn-1
        String temp_var="", temp_prev="";

        if (dim.size() > 1) {
            tn = dim.get(0).getIdx() * (dim.get(1).getSubrange().getVal2() - dim.get(1).getSubrange().getVal1() + 1); //t1 = i1 * d2
            temp_prev = tac.newTempVar(String.valueOf(TypeSub.INTEGER), ""+tn);
            tac.generateCode(temp_prev + " = "+dim.get(0).getIdx()+" * "+
                    (dim.get(1).getSubrange().getVal2() - dim.get(1).getSubrange().getVal1() + 1)+"\n");

            for (int i=1; i< dim.size()-1; i++){
                tn1 = tn + dim.get(i).getIdx();    //t2 = t1 + i2
                temp_var = tac.newTempVar(String.valueOf(TypeSub.INTEGER), ""+tn1);
                tac.generateCode(temp_var + " = " + temp_prev + " + "+dim.get(i).getIdx()+"\n");

                tn = tn1 * (dim.get(i+1).getSubrange().getVal2() - dim.get(i+1).getSubrange().getVal1() + 1);
                temp_prev = temp_var;
                temp_var = tac.newTempVar(String.valueOf(TypeSub.INTEGER), ""+tn);
                tac.generateCode(temp_var + " = " + temp_prev + " * "+
                        (dim.get(i+1).getSubrange().getVal2() - dim.get(i+1).getSubrange().getVal1() + 1)+"\n");
            }
            tn1 = tn + dim.get(dim.size()-1).getIdx();
            if (!temp_var.equals("")) temp_prev = temp_var;
            temp_var = tac.newTempVar(String.valueOf(TypeSub.INTEGER), ""+tn1);
            tac.generateCode(temp_var + " = " + temp_prev + " + "+dim.get(dim.size()-1).getIdx()+"\n");

            tn2 = tn1 - b;
            temp_prev = temp_var;
            temp_var = tac.newTempVar(String.valueOf(TypeSub.INTEGER), ""+tn2);
            tac.generateCode(temp_var + " = " + temp_prev + " - "+ b+"\n");

            tn3 = tn2 * nbytes;
            temp_prev = temp_var;
            temp_var = tac.newTempVar(String.valueOf(TypeSub.INTEGER), ""+tn3);
            tac.generateCode(temp_var + " = " + temp_prev + " * "+ nbytes+"\n");
        } else {
            String i="";
            if (OPERANDX.getSUBTYPE().getID()!=null) i = OPERANDX.getSUBTYPE().getID().getID();
            else if (OPERANDX.getSUBTYPE().isArray()) i = OPERANDX.getSUBTYPE().getIDARRAY().getIDLISTDIM().getOPERANDX().getSUBTYPE().getID().getID();
            else i = dim.get(0).getIdx()+"";

            tn = dim.get(0).getIdx() - dim.get(0).getSubrange().getVal1();
            temp_prev = tac.newTempVar(String.valueOf(TypeSub.INTEGER), ""+tn);
            tac.generateCode(temp_prev + " = "+i+" - "+dim.get(0).getSubrange().getVal1()+"\n");

            tn1 = tn * nbytes;
            temp_var = tac.newTempVar(String.valueOf(TypeSub.INTEGER), ""+tn1);
            tac.generateCode(temp_var + " = "+temp_prev+" * "+nbytes+"\n");
        }

        if (left){
            tac.generateCode(tac.newVarArray(IDARRAY.getID().getID(), IDARRAY.getID().getType(), ts.get(IDARRAY.getID().getID()).getLength())+
                    "["+temp_var+"]"+" = "+OPERANDX.getSUBTYPE().getName()+"\n");
        } else {
            tac.generateCode(tac.newVar(ID.getID(), ID.getType()) + " = "+ OPERANDX.getSUBTYPE().getIDARRAY().getID().getID()
                    + "["+temp_var+"]"+"\n");
        }
    }

    private ArrayList<Dimension> setIdxs(ArrayList<Dimension> dim, ArrayList<Integer> idxs){
        for (int i=0; i<dim.size(); i++){
            dim.get(i).setIdx((int) idxs.get(i));
        }
        return  dim;
    }

}
