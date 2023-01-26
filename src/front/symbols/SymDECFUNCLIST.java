/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;


public class SymDECFUNCLIST extends SymBase {

    private final SymDECFUNC DECFUNC;
    private final SymMETFUNC METFUNC;

    public SymDECFUNCLIST(SymDECFUNC a, SymMETFUNC b, int[] lc) {
        super("F", 0);
        this.DECFUNC = a;
        this.METFUNC = b;

    }

}
