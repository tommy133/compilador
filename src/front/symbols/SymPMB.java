package front.symbols;

import front.data_structures.procedure.Procedure;


public class SymPMB extends SymBase {

    //Genera el preamble de cada metode/funcio

    public SymPMB() {
        super("PMB", 0);

        String startLabel = tac.getTemp_id();
        tac.setTemp_id(null);
        procedureTable.addRow(new Procedure(procedureTable.getNewNumProc(), 0, startLabel, ts.getNTempVars(), 0));
        tac.generateCode("pmb " + startLabel + "\n");
    }

}
