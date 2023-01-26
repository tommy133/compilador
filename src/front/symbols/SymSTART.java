/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;


public class SymSTART extends SymBase {

    private SymMETFUNC METFUNC;
    private SymDECMP DECMP;

    public SymSTART(SymMETFUNC a, SymDECMP b) {
        super("START", 0);
        this.METFUNC = a;
        this.DECMP = b;

        tp.calculateLocalVarSize(tv);
        closeFiles();
    }



}
