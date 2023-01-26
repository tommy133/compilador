/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

import front.data_types.TypeSub;


public class SymNUM extends SymBase {
    private int value;

    public SymNUM(int a) {
        super("F", 0);
        this.value = a;
    }

    public String getValue() {
        return Integer.toString(value);
    }

    public String getType() {
        return TypeSub.INTEGER.toString();
    }

}
