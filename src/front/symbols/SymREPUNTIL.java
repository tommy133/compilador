package front.symbols;

public class SymREPUNTIL extends SymBase {
    private SymCONDITION COND;
    private SymSENTLIST SENTLIST;

    public SymREPUNTIL(SymCONDITION a, SymSENTLIST b) {
        super("F", 0);
        this.COND = a;
        this.SENTLIST = b;
    }
}
