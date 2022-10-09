/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

public class SymOPARITH extends SymBase {

    private String type;

    public SymOPARITH(String a) {
        super("OPARITH", 0);
        this.type = a;

    }

    public String getType() {
        return type;
    }


}
