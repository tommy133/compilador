/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;


public class SymINPUT extends SymBase {

    private SymID ID;

    public SymINPUT(SymID a) {
        super("F", 0);
        this.ID = a;
        tac.generateCode("param " + ID + "\n");
        tac.generateCode("call read \n");
        tac.setTemp_id(null);
    }


}
