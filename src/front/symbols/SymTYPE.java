/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;


public class SymTYPE extends SymBase {
    private String TYPE;

    public SymTYPE(String a) {
        super("TYPE", 0);
        this.TYPE = a;
    }

    public String getType() {
        return TYPE;
    }


}
