package front.symbols;


public class SymSTARTLOOP extends SymBase {

    //etiqueta inici de bucle

    public SymSTARTLOOP() {
        super("STARTLOOP", 0);

        String startLabel = tac.newLabel();
        tac.push(tac.getStart_stack(), startLabel);

        tac.generateCode(startLabel + ":skip\n");

    }


}
