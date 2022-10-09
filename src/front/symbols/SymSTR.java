/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

import front.data_types.TypeSub;


public class SymSTR extends SymBase {

    private String val;

    public SymSTR(String a) {
        super("STR", 0);
        this.val = a;
    }

    public String getType() {
        return TypeSub.STRING.toString();
    }

    public String getVal() {
        return val;
    }


}
