/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;


public class SymSENT extends SymBase {

    private SymOUTPUT OUTPUT;
    private SymINPUT INPUT;
    private SymCONDIF CONDIF;
    private SymWHILE WHILE;
    private SymREPUNTIL REPUNTIL;
    private SymCALLMET CALLMET;
    private SymASSIGN ASSIGN;
    private SymDECVAR DECVAR;
    private SymDECARRAY DECARRAY;

    public SymSENT(SymOUTPUT a) {
        super("SENT", 0);
        this.OUTPUT = a;
    }

    public SymSENT(SymCONDIF a) {
        super("SENT", 0);
        this.CONDIF = a;
    }

    public SymSENT(SymINPUT a) {
        super("SENT", 0);
        this.INPUT = a;
    }

    public SymSENT(SymWHILE a) {
        super("SENT", 0);
        this.WHILE = a;
    }

    public SymSENT(SymREPUNTIL a) {
        super("SENT", 0);
        this.REPUNTIL = a;
    }

    public SymSENT(SymCALLMET a) {
        super("SENT", 0);
        this.CALLMET = a;
    }

    public SymSENT(SymASSIGN a) {
        super("SENT", 0);
        this.ASSIGN = a;
    }

    public SymSENT(SymDECVAR a) {
        super("SENT", 0);
        this.DECVAR = a;
    }

    public SymSENT(SymDECARRAY a) {
        super("SENT", 0);
        this.DECARRAY = a;
    }
}
