/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

import front.data_structures.procedure.ProcedureTable;
import front.data_structures.variable.VariableTable;
import front.code_generator.ThreeAddressCode;
import front.data_structures.symbol.SymbolTable;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;


public abstract class SymBase extends ComplexSymbol {
    private static int idAutoIncrement = 0;
    private int index;
    protected static SymbolTable ts = new SymbolTable();
    protected static VariableTable variableTable = new VariableTable();
    protected static ProcedureTable procedureTable = new ProcedureTable();
    protected static ThreeAddressCode tac = new ThreeAddressCode(variableTable);

    public SymBase(String variable, Integer valor) {
        super(variable, idAutoIncrement++, valor);
        index = idAutoIncrement;
    }

}
