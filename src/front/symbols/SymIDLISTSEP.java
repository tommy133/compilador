/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;


public class SymIDLISTSEP extends SymBase {

    private SymIDLIST IDLIST;

    public SymIDLISTSEP(SymIDLIST a) {
        super("IDLIST", 0);
        this.IDLIST = a;
    }

    public SymIDLISTSEP() {

        super("IDLISTSEP", 0);

    }

    public SymIDLIST getLID() {
        return IDLIST;
    }



}
