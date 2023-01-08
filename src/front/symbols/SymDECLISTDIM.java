package front.symbols;

public class SymDECLISTDIM extends SymBase {
    private SymDECLISTDIM DECLISTDIM;
    private SymSUBRANGE SUBRANGE;

    public SymDECLISTDIM(SymDECLISTDIM a, SymSUBRANGE b) {
        super("ID", 0);
        this.DECLISTDIM =a;
        this.SUBRANGE =b;
    }

    public SymDECLISTDIM(SymSUBRANGE a) {
        super("SUBRANGE", 0);
        this.SUBRANGE = a;
    }

    public SymDECLISTDIM getDECLISTDIM() {
        return DECLISTDIM;
    }

    public SymSUBRANGE getSUBRANGE() {
        return SUBRANGE;
    }
}
