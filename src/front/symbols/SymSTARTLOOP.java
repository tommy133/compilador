package front.symbols;


public class SymSTARTLOOP extends SymBase {

    //etiqueta inici de bucle

    public SymSTARTLOOP() {
        super("STARTLOOP", 0);

        String start_label = tac.newLabel();
        tac.push(tac.getStart_stack(), start_label);

        tac.generateCode(start_label + ":skip\n");

    }


}
