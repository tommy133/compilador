package front.symbols;


public class SymCONDLABEL extends SymBase {

    //Genera les etiquetes corresponents a ambdues branques del condicional

    public SymCONDLABEL() {
        super("CONDLABEL", 0);

        tac.generateCode("if ");
        String true_label = tac.newLabel();
        tac.push(tac.getTrue_stack(), true_label);

        String false_label = tac.newLabel();
        tac.push(tac.getFalse_stack(), false_label);
    }

}
