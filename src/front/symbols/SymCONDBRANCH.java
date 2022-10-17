package front.symbols;


public class SymCONDBRANCH extends SymBase {

    //Genera els bots després d'evaluar el condicional en bucles i condicionals

    public SymCONDBRANCH(boolean inverter) {
        super("CONDBRANCH", 0);

        if (inverter){
            String falseLabel = tac.getTop(tac.getFalse_stack());
            tac.generateCode("goto " + falseLabel + "\n");

            String trueLabel = tac.getTop(tac.getTrue_stack());
            tac.generateCode("goto " + trueLabel + "\n");
            tac.pop(tac.getTrue_stack());

            tac.generateCode(trueLabel + ":skip\n");
            tac.setTemp_id(null);
        } else {
            String trueLabel = tac.getTop(tac.getTrue_stack());
            tac.generateCode("goto " + trueLabel + "\n");
            tac.pop(tac.getTrue_stack());

            String falseLabel = tac.getTop(tac.getFalse_stack());
            tac.generateCode("goto " + falseLabel + "\n");

            tac.generateCode(trueLabel + ":skip\n");
            tac.setTemp_id(null);
        }

    }

}
