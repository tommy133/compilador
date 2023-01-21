/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

import front.data_types.TypeSub;
import front.error.ErrorArgTypes;
import front.error.ErrorIncompatibleTypesOperation;


public class SymOPERANDX extends SymBase {

    private SymOPERANDX OPERANDX;
    private SymOPARITH OPARITH;
    private SymSUBTYPE SUBTYPE;

    private String type_operand;
    private String place = "SymOPERANDX";
    public SymOPERANDX() {
        super("OPERANDX", 0);
    }

    public SymOPERANDX(SymOPERANDX a, SymOPARITH b, SymSUBTYPE c, int[] lc) throws ErrorIncompatibleTypesOperation {
        super("OPERANDX", 0);
        this.OPERANDX = a;
        this.OPARITH = b;
        this.SUBTYPE = c;

        if (OPERANDX.getSUBTYPE().getType() != null) {
            if(!this.OPERANDX.getSUBTYPE().getType().equalsIgnoreCase(this.SUBTYPE.getType())){
                new ErrorIncompatibleTypesOperation().printError(place, lc, type_operand);
                throw new ErrorIncompatibleTypesOperation();
            }
            if (OPERANDX.getSUBTYPE().getType().equalsIgnoreCase(TypeSub.INTEGER.toString())
                    && SUBTYPE.getType().equalsIgnoreCase(TypeSub.INTEGER.toString())) {
                type_operand = TypeSub.INTEGER.toString();
            }
        }
    }

    public SymOPERANDX(SymSUBTYPE a) {
        super("OPERANDX", 0);
        this.SUBTYPE = a;
    }

    public SymSUBTYPE getSUBTYPE() {
        return SUBTYPE;
    }

    public SymOPARITH getOPARITH() {
        return OPARITH;
    }

    public SymOPERANDX getOPERANDX() {
        return OPERANDX;
    }


}
