package front.symbols;


public class SymSTARTPROC extends SymBase {

    //Genera les etiquetes que van al inici dels metodes i funcions

    public SymSTARTPROC() {
        super("STARTPROC", 0);

        String start_label = tac.getTemp_id();
        tac.setCur_prog(start_label);
        tac.generateCode(start_label + ":skip\n");
        tac.push(tac.getStart_stack(), start_label);
    }


}
