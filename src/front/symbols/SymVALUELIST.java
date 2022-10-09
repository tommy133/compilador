/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

public class SymVALUELIST extends SymBase {

    private SymVALUE VAL;
    private SymVALUELIST VALUELIST;

    public SymVALUELIST(SymVALUE a) {
        super("F", 0);
        this.VAL = a;
    }

    public SymVALUELIST(SymVALUE a, SymVALUELIST b) {
        super("F", 0);
        this.VAL = a;
        this.VALUELIST = b;
    }

    public String getVAL() {
        return VAL.getVal();
    }


}
