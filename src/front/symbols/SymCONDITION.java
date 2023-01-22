/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;


import front.error.ErrorIncompatibleTypesOperation;
import front.error.ErrorVarNotDec;

public class SymCONDITION extends SymBase {

    private SymOPERANDX OPERANDX1;
    private SymOPERANDX OPERANDX2;
    private SymOPERATION OPERATION;
    private SymCONDEXT CONDEXT;
    private SymID ID;
    private SymIDARRAY IDARRAY;

    private String type_op;

    public SymCONDITION(SymOPERANDX a, SymOPERATION c, SymOPERANDX b, SymCONDEXT d, int[] lc) throws ErrorIncompatibleTypesOperation {
        super("CONDITION", 0);
        this.OPERANDX1 = a;
        this.OPERANDX2 = b;
        this.OPERATION = c;
        this.CONDEXT = d;
        if(!this.OPERANDX1.getSUBTYPE().getType().equalsIgnoreCase(this.OPERANDX2.getSUBTYPE().getType())){
            new ErrorIncompatibleTypesOperation().printError(lc, "");
            throw new ErrorIncompatibleTypesOperation();
        }
        do {

            tac.generateCode(OPERANDX1.getSUBTYPE().getValor() + " ");

            if (OPERATION.getANDOR() != null) {
                tac.generateCode(OPERATION.getANDOR().getOperation() + " ");
            } else {
                tac.generateCode(OPERATION.getOPLOGIC().getOperator() + " ");
            }

            tac.generateCode(OPERANDX2.getSUBTYPE().getValor() + " ");
        }
        while (CONDEXT != null);
    }

    public SymCONDITION(SymID a, int[] lc) throws ErrorVarNotDec, ErrorIncompatibleTypesOperation {
        super("CONDITION", 0);

        this.ID = a;
        this.type_op = ID.getType();
        if(!this.type_op.equalsIgnoreCase("LOGIC")){
            new ErrorIncompatibleTypesOperation().printError(lc, "");
            throw new ErrorIncompatibleTypesOperation();
        }
        tac.generateCode(ID.getID() + " then ");
    }

    public SymCONDITION(SymIDARRAY IDARRAY) throws ErrorVarNotDec {
        super("CONDITION", 0);

        this.IDARRAY = IDARRAY;
        this.type_op = IDARRAY.getID().getType();
    }

    @Override
    public String toString() {
        if (ID != null) {
            return ID.getID();
        } else {
            return "condition";
        }
    }



}
