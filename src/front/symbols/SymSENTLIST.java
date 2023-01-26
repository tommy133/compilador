/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;


public class SymSENTLIST extends SymBase {

    private final SymSENT SENT;
    private final SymSENTLISTSEP SENTLISTSEP;

    public SymSENTLIST(SymSENT a, SymSENTLISTSEP b) {
        super("SENTLISTSEP", 0);
        this.SENT = a;
        this.SENTLISTSEP = b;
    }
}
