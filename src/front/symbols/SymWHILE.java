/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;


public class SymWHILE extends SymBase {

    private final SymCONDITION COND;
    private final SymSENTLIST SENTLIST;

    public SymWHILE(SymCONDITION a, SymSENTLIST b) {
        super("F", 0);
        this.COND = a;
        this.SENTLIST = b;
    }


}
