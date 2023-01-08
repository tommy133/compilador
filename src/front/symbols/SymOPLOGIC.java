/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

import front.data_types.TypeSub;


public class SymOPLOGIC extends SymBase {

    private String operator;
    private String typeOperator;

    public SymOPLOGIC(String a) {
        super("OPLOGIC", 0);
        this.operator = a;
        this.typeOperator = TypeSub.LOGIC.toString();

    }

    public String getTypeOperator() {
        return typeOperator;
    }

    public String getOperator() {
        return operator;
    }


}
