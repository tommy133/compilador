/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;


public class SymPRINT extends SymBase {

    private SymVALUELIST VALUELIST;
    private SymPRINT PRINT;
    private SymIDLIST IDLIST;

    public SymPRINT(SymVALUELIST a, SymPRINT b) {
        super("PRINT", 0);
        this.VALUELIST = a;
        this.PRINT = b;
    }

    public SymPRINT(SymIDLIST a, SymPRINT b) {
        super("PRINT", 0);
        this.IDLIST = a;
        this.PRINT = b;
    }

    public SymID getID() {
        return IDLIST.getID();
    }

    public SymPRINT() {
        super("PRINT", 0);
    }

    public SymVALUELIST getVALUELIST() {
        return VALUELIST;
    }

    public String getVALID() {
        if (VALUELIST != null) {
            return VALUELIST.getVAL();
        } else {
            return IDLIST.getID().toString();
        }
    }



}
