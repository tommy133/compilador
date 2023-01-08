/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

import front.data_types.TypeSub;


public class SymLOGIC extends SymBase {

    private String value;

    public SymLOGIC(String a) {
        super("F", 0);
        this.value = a;
    }

    public String getType() {
        return TypeSub.LOGIC.toString();
    }

    public String getValue() {
        return value;
    }


}
