/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;


import front.error.ErrorVarNotDec;

public class SymRETURNTYPE extends SymBase {

    private SymID ID;
    private SymNUM NUM;
    private SymCALLPROC CALLPROC;
    private SymLOGIC LOGIC;
    private SymSTR STR;

    private final String type;

    public SymRETURNTYPE(SymNUM a) {
        super("RETURNTYPE", 0);
        this.NUM = a;
        type = a.getType();
    }

    public SymRETURNTYPE(SymID a, int[] lc) throws ErrorVarNotDec {
        super("RETURNTYPE", 0);
        this.ID = a;
        type = a.getType();

    }

    public SymRETURNTYPE(SymCALLPROC a) {
        super("RETURNTYPE", 0);
        this.CALLPROC = a;
        type = a.getType();

    }

    public SymRETURNTYPE(SymLOGIC a) {
        super("RETURNTYPE", 0);
        this.LOGIC = a;
        type = a.getType();

    }

    public SymRETURNTYPE(SymSTR a) {
        super("RETURNTYPE", 0);
        this.STR = a;
        type = a.getType();

    }

    public SymID getID() {
        return ID;
    }

    public String getType() {
        return type;
    }


}
