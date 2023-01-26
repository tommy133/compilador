/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

public class SymOPERATION extends SymBase {

    private SymANDOR ANDOR;
    private SymOPLOGIC OPLOGIC;

    public SymOPERATION(SymANDOR a) {
        super("OPERATION", 0);
        this.ANDOR = a;
    }

    public SymOPERATION(SymOPLOGIC b) {
        super("OPERATION", 0);
        this.OPLOGIC = b;
    }

    public SymOPERATION() {
        super("OPERATION", 0);
    }

    public SymANDOR getANDOR() {
        return ANDOR;
    }

    public SymOPLOGIC getOPLOGIC() {
        return OPLOGIC;
    }



}
