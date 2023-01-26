/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

public class SymOPERANDX extends SymBase {

    private SymOPERANDX OPERANDX;
    private SymOPARITH OPARITH;
    private SymSUBTYPE SUBTYPE;

    public SymOPERANDX() {
        super("OPERANDX", 0);
    }

    public SymOPERANDX(SymOPERANDX a, SymOPARITH b, SymSUBTYPE c) {
        super("OPERANDX", 0);
        this.OPERANDX = a;
        this.OPARITH = b;
        this.SUBTYPE = c;

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
