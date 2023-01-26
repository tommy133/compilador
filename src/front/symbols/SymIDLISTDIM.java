package front.symbols;

public class SymIDLISTDIM extends SymBase {
    private SymIDLISTDIM IDLISTDIM;
    private final SymOPERANDX OPERANDX;


    public SymIDLISTDIM(SymIDLISTDIM a, SymOPERANDX b) {
        super("ID", 0);
        this.IDLISTDIM = a;
        this.OPERANDX = b;
    }

    public SymIDLISTDIM(SymOPERANDX OPERANDX) {
        super("OPERANDX", 0);
        this.OPERANDX = OPERANDX;
    }

    public SymIDLISTDIM getIDLISTDIM() {
        return IDLISTDIM;
    }

    public SymOPERANDX getOPERANDX() {
        return OPERANDX;
    }

}
