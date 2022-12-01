/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;


public class SymCONDITION extends SymBase {

    private SymOPERANDX OPERANDX1;
    private SymOPERANDX OPERANDX2;
    private SymOPERATION OPERATION;
    private SymCONDEXT CONDEXT;
    private SymID ID;
    private SymIDARRAY IDARRAY;

    private String type_op;

    public SymCONDITION(SymOPERANDX a, SymOPERATION c, SymOPERANDX b, SymCONDEXT d) {
        super("CONDITION", 0);
        this.OPERANDX1 = a;
        this.OPERANDX2 = b;
        this.OPERATION = c;
        this.CONDEXT = d;

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

    public SymCONDITION(SymID a) {
        super("CONDITION", 0);

        this.ID = a;
        this.type_op = ID.getType();
        tac.generateCode(ID.getID() + " then ");
    }

    public SymCONDITION(SymIDARRAY IDARRAY) {
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
