/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;


public class SymIDLIST extends SymBase {

    private SymID ID;
    private SymIDLISTSEP IDLISTSEP;

    public SymIDLIST(SymID a, SymIDLISTSEP b) {
        super("ID", 0);
        this.ID = a;
        this.IDLISTSEP = b;
    }

    public SymIDLISTSEP getIDLISTSEP() {
        return IDLISTSEP;
    }

    public SymID getID() {
        return ID;
    }


}
