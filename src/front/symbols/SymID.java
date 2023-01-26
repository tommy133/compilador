/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

import front.error.ErrorVarNotDec;


public class SymID extends SymBase {

    private final String ID;
    int[] lc;

    public SymID(String a, int[] lc) {
        super("ID", 0);
        this.ID = a;
        this.lc = lc;

        if (tac.getTemp_id() == null) tac.setTemp_id(ID);

    }

    public String getID() {
        return this.ID;
    }

    public String getType() throws ErrorVarNotDec {

        if (ts.get(ID) == null) {
            new ErrorVarNotDec().printError(lc, ID);
            throw new ErrorVarNotDec();
        } else {
            return ts.get(ID).getSubtype();
        }
    }

    @Override
    public String toString() {
        return ID;
    }


}
