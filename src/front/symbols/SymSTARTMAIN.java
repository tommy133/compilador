package front.symbols;

public class SymSTARTMAIN extends SymBase{
    public SymSTARTMAIN() {
        super("STARTMAIN", 0);

        tac.generateCode("goto main\n");
    }
}
