package front.symbols;

public class SymIDLISTDIM extends SymBase {
    private SymIDLISTDIM IDLISTDIM;
    private SymSUBRANGE SUBRANGE;

    public SymIDLISTDIM(SymIDLISTDIM a, SymSUBRANGE b) {
        super("ID", 0);
        this.IDLISTDIM =a;
        this.SUBRANGE =b;
    }

    public SymIDLISTDIM(SymSUBRANGE a) {
        super("SUBRANGE", 0);
        this.SUBRANGE = a;
    }

    public SymIDLISTDIM getIDLISTDIM() {
        return IDLISTDIM;
    }

    public SymSUBRANGE getSUBRANGE() {
        return SUBRANGE;
    }
}
