package front.symbols;

public class SymELSE extends SymBase {

    //Genera etiquetes i bots per a sentencia else

    public SymELSE() {
        super("SymELSE", 0);


        String endLabel = tac.newLabel();
        tac.push(tac.getEnd_stack(), endLabel);

        tac.generateCode("goto " + endLabel + "\n");

        String falseLabel = tac.getTop(tac.getFalse_stack());
        tac.generateCode(falseLabel + ":skip\n");
    }

}
