/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

import front.data_types.TypeSub;

public class SymANDOR extends SymBase {

    private final String operation;
    private final String typeOperation;

    public SymANDOR(String a) {
        super("ANDOR", 0);
        operation = a;
        typeOperation = TypeSub.LOGIC.toString();
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public String getOperation() {
        return operation;
    }


}
