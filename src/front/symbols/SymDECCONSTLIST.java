/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;




public class SymDECCONSTLIST extends SymBase {

    private SymDECLARECONST DECLARECONST;
    private SymDECCONSTLIST DECCONSTLIST;

    public SymDECCONSTLIST() {
        super("DECCONSTLIST", 0);
    }

    public SymDECCONSTLIST(SymDECLARECONST a, SymDECCONSTLIST b, int[] lc) {
        super("DECCONSTLIST", 0);
        this.DECLARECONST = a;
        this.DECCONSTLIST = b;

    }

}
