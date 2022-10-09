/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;


public class SymASSIGNCONST extends SymBase {

    private SymID ID;
    private SymOPERANDX OPERANDX;

    public SymASSIGNCONST(SymID a, SymOPERANDX b) {
        super("ASSIGNCONST", 0);
        this.ID = a;
        this.OPERANDX = b;
        tac.setOperand(false);
    }

    public SymID getID() {
        return ID;
    }

    public SymOPERANDX getOPERANDX() {
        return OPERANDX;
    }


}
