/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

public class SymOPLOGIC extends SymBase {

    private String operator;

    public SymOPLOGIC(String a) {
        super("OPLOGIC", 0);
        this.operator = a;

    }

    public String getOperator() {
        return operator;
    }


}
