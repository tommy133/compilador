/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;


public class SymARG extends SymBase {

    private final SymTYPE TYPE;
    private final SymID ID;

    public SymARG(SymTYPE a, SymID b) {
        super("F", 0);
        this.TYPE = a;
        this.ID = b;
    }

    public SymID getID() {
        return ID;
    }

    public SymTYPE getType() {
        return TYPE;
    }


}
