package front.symbols;


public class SymCONDLABEL extends SymBase {

    //Genera les etiquetes corresponents a ambdues branques del condicional

    public SymCONDLABEL() {
        super("CONDLABEL", 0);

        tac.generateCode("if ");
        String trueLabel = tac.newLabel();
        tac.push(tac.getTrue_stack(), trueLabel);
        String falseLabel = tac.newLabel();
        tac.push(tac.getFalse_stack(), falseLabel);
    }

}
