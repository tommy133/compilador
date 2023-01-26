package front.symbols;


public class SymGSTARTLOOP extends SymBase {

    //Genera el bot a l'etiqueta d'inici de bucle

    public SymGSTARTLOOP() {
        super("SymGSTARTLOOP", 0);

        tac.generateCode("goto " + tac.getTop(tac.getStart_stack()) + "\n");
        String false_label = tac.getTop(tac.getFalse_stack());
        tac.generateCode(false_label + ":skip\n");
    }


}
