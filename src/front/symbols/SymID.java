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
    private final String place = "SymID";

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

    public String getType() throws ErrorVarNotDec {

        if (ts.get(ID) == null) {
            new ErrorVarNotDec().printError(place,lc, ID);
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
