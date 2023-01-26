/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;


public class SymINPUT extends SymBase {

    private final SymID ID;

    public SymINPUT(SymID a) {
        super("F", 0);
        this.ID = a;

        tac.generateCode(paramType()+" " + ID + "\n");
        tac.generateCode("call " + (paramType().equals("param_c")?"getStr":"getInt") + "\n");
        tac.generateCode(tac.newVar(ID.getID(), ID.getType()) +" = " + returnType() + "\n");
        tac.setTemp_id(null);
    }

    private String paramType(){
        if (ID.getType().equalsIgnoreCase("string")) return "param_c";
        return "param_s";
    }

    private String returnType() {
        switch (ID.getType()){
            case "string": return "retStr";
            case "integer": return "retInt";
            case "logic": return "retBool";
        }
        return null;
    }
}
