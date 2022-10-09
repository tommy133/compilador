package front.symbols;


public class SymSTARTPROC extends SymBase {

    //Genera les etiquetes que van al inici dels metodes i funcions

    public SymSTARTPROC() {
        super("STARTPROC", 0);

        String startLabel = tac.getTemp_id();
        tac.setCur_prog(startLabel);
        tac.generateCode(startLabel + ":skip\n");
        tac.push(tac.getStart_stack(), startLabel);
    }


}
