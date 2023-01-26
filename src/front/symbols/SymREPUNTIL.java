package front.symbols;

public class SymREPUNTIL extends SymBase {
    private final SymCONDITION COND;
    private final SymSENTLIST SENTLIST;

    public SymREPUNTIL(SymCONDITION a, SymSENTLIST b) {
        super("F", 0);
        this.COND = a;
        this.SENTLIST = b;
    }
}
