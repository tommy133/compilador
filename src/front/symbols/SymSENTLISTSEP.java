/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

public class SymSENTLISTSEP extends SymBase {

    private SymSENTLIST SENTLIST;

    public SymSENTLISTSEP(SymSENTLIST a) {
        super("SENTLISTSEP", 0);
        this.SENTLIST = a;
    }

    public SymSENTLISTSEP() {
        super("SENTLISTSEP", 0);
    }


}
