package front.symbols;

public class SymELSE extends SymBase {

    //Genera etiquetes i bots per a sentencia else

    public SymELSE() {
        super("SymELSE", 0);


        String end_label = tac.newLabel();
        tac.push(tac.getEnd_stack(), end_label);

        tac.generateCode("goto " + end_label + "\n");

        String false_label = tac.getTop(tac.getFalse_stack());
        tac.generateCode(false_label + ":skip\n");
    }

}
