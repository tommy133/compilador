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
        tac.generateCode(paramType()+" " + ID + "\n");
        tac.generateCode("call read \n");
        tac.generateCode(tac.newVar(ID.getID(), ID.getType()) +" = " + "retInt" + "\n"); //FIXME discernir entre string/int
        tac.setTemp_id(null);
    }

    private String paramType(){
        if (ID.getType().equalsIgnoreCase("string")) return "param_c";
        return "param_s";
    }
}
