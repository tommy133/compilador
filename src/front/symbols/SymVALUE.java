/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

public class SymVALUE extends SymBase {

    private SymNUM NUM;
    private SymSTR STR;
    private SymLOGIC LOGIC;

    public SymVALUE(SymNUM a) {
        super("VALUE", 0);
        this.NUM = a;
    }

    public SymVALUE(SymSTR a) {
        super("VALUE", 0);
        this.STR = a;
    }

    public SymVALUE(SymLOGIC a) {
        super("VALUE", 0);
        this.LOGIC = a;
    }

    public String getVal() {
        if (NUM != null) {
            return NUM.getValue();
        } else if (LOGIC != null) {
            return LOGIC.getValue();
        } else if (STR != null) {
            return STR.getVal();
        }

        return null;
    }

    public String getType() {
        if (NUM != null) {
            return NUM.getType();
        } else if (LOGIC != null) {
            return LOGIC.getType();
        } else if (STR != null) {
            return STR.getType();
        }

        return null;
    }


}
