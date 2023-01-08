/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

import front.error.ErrorVarNotDec;


public class SymID extends SymBase {

    private String ID;
    int[] lc;

    public SymID(String a, int[] lc) {
        super("ID", 0);
        this.ID = a;
        this.lc = lc;

        if (tac.getTemp_id() == null) {
            String startLabel = ID;
            tac.setTemp_id(startLabel);
        }
    }

    public String getID() {
        return this.ID;
    }

    public String getType() {

        if (ts.get(ID) == null) {
            new ErrorVarNotDec().printError(lc, ID);
        } else {
            return ts.get(ID).getSubtype();
        }
        return null;
    }

    @Override
    public String toString() {
        return ID;
    }


}
