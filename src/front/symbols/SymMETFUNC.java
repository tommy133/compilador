/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

public class SymMETFUNC extends SymBase {

    private SymDECMETLIST DECMETLIST;
    private SymDECFUNCLIST DECFUNCLIST;

    public SymMETFUNC(SymDECMETLIST a) {
        super("MF", 0);
        this.DECMETLIST = a;
    }

    public SymMETFUNC(SymDECFUNCLIST a) {
        super("MF", 0);
        this.DECFUNCLIST = a;
    }

    public SymMETFUNC() {
        super("METFUNC", 0);
    }


}
