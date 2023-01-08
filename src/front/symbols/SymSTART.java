/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SymSTART extends SymBase {

    private SymMETFUNC METFUNC;
    private SymDECMP DECMP;


    //Inicialitza estructures de dades, crida al optimitzador i al ensamblador

    public SymSTART(SymMETFUNC a, SymDECMP b) {
        super("START", 0);
        this.METFUNC = a;
        this.DECMP = b;

        procedureTable.calculateLocalVarSize(variableTable);

        ts.closeFile();
        variableTable.closeFile(); //Escritura de les taules als corresponents fitxers
        procedureTable.closeFile();
        tac.closeFile();

    }

}
