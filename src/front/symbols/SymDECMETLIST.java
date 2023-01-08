/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;


public class SymDECMETLIST extends SymBase {

    private SymDECMET DECMET;
    private SymMETFUNC METFUNC;

    public SymDECMETLIST(SymDECMET a, SymMETFUNC b) {
        super("F", 0);
        this.DECMET = a;
        this.METFUNC = b;
    }

}
