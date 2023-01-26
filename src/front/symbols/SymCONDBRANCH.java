package front.symbols;


public class SymCONDBRANCH extends SymBase {

    //Genera els bots després d'evaluar el condicional en bucles i condicionals

    public SymCONDBRANCH(boolean inverter) {
        super("CONDBRANCH", 0);

        if (inverter){
            String false_label = tac.getTop(tac.getFalse_stack());
            tac.generateCode("goto " + false_label + "\n");

            String true_label = tac.getTop(tac.getTrue_stack());
            tac.generateCode("goto " + true_label + "\n");
            tac.pop(tac.getTrue_stack());

            tac.generateCode(true_label + ":skip\n");
        } else {
            String true_label = tac.getTop(tac.getTrue_stack());
            tac.generateCode("goto " + true_label + "\n");
            tac.pop(tac.getTrue_stack());

            String false_label = tac.getTop(tac.getFalse_stack());
            tac.generateCode("goto " + false_label + "\n");

            tac.generateCode(true_label + ":skip\n");
        }
        tac.setTemp_id(null);
    }

}
