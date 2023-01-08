package front.symbols;

import front.error.ErrorVarNotDec;

public class SymIDARRAY extends SymBase{
    private SymID ID;
    private SymIDLISTDIM IDLISTDIM;

    public SymIDARRAY(SymID ID, SymIDLISTDIM IDLISTDIM) {
        super("IDARRAY", 0);
        this.ID = ID;
        this.IDLISTDIM = IDLISTDIM;
    }

    public SymID getID() {
        return ID;
    }

    public SymIDLISTDIM getIDLISTDIM() {
        return IDLISTDIM;
    }

}
