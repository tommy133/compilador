/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;


public class SymCONDEXT extends SymBase {

    private SymOPERATION OPERATION;
    private SymCONDITION CONDITION;

    public SymCONDEXT(SymOPERATION a, SymCONDITION b) {
        super("CONDEXT", 0);
        this.OPERATION = a;
        this.CONDITION = b;
    }


    public SymCONDEXT() {
        super("CONDEXT", 0);
    }


}
