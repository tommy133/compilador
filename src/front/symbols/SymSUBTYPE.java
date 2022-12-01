/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;


public class SymSUBTYPE extends SymBase {

    private SymID ID;
    private SymIDARRAY IDARRAY;
    private SymSTR STR;
    private SymLOGIC LOGIC;
    private SymNUM NUM;
    private SymCALLFUNC CALLFUNC;
    private String type;
    private String name;

    public SymSUBTYPE(SymID a) {
        super("T", 0);
        this.ID = a;
        this.type = a.getType();
        this.name = a.getID();
        tac.getOperands().add(a.getID());
    }

    public SymSUBTYPE(SymIDARRAY IDARRAY) {
        super("T", 0);
        this.IDARRAY = IDARRAY;
        this.type = IDARRAY.getID().getType();
    }

    public SymSUBTYPE(SymNUM a) {
        super("T", 0);
        this.NUM = a;
        this.type = a.getType();
        this.name = a.getValue();
        tac.getOperands().add(a.getValue());
    }

    public SymSUBTYPE(SymSTR a) {
        super("T", 0);
        this.STR = a;
        this.type = a.getType();
        tac.getOperands().add(a.getVal());
    }

    public SymSUBTYPE(SymLOGIC a) {
        super("T", 0);
        this.LOGIC = a;
        this.type = a.getType();
        tac.getOperands().add(a.getValue());
    }

    public SymSUBTYPE(SymCALLFUNC a) {
        super("T", 0);
        this.CALLFUNC = a;
        this.type = a.getType();
    }

    public String getType() {
        return type;
    }

    public String getValor() {
        switch (type.toUpperCase()) {
            case "INTEGER":
                if (NUM != null) {
                    return NUM.getValue();
                } else {
                    return name;
                }
            case "LOGIC":
                return LOGIC.getValue();
            case "STRING":
                return STR.getVal();
            default:
                return name;
        }
    }

    public String getName() {
        return name;
    }

    public SymIDARRAY getIDARRAY() {
        return IDARRAY;
    }

    public boolean isArray() {
        return this.IDARRAY != null;
    }
}
