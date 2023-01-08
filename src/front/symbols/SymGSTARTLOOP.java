package front.symbols;


public class SymGSTARTLOOP extends SymBase {

    //Genera el bot a l'etiqueta d'inici de bucle

    public SymGSTARTLOOP() {
        super("SymGSTARTLOOP", 0);

        tac.generateCode("goto " + tac.getTop(tac.getStart_stack()) + "\n");
        String falseLabel = tac.getTop(tac.getFalse_stack());
        tac.generateCode(falseLabel + ":skip\n");
    }


}
