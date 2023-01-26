/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;

import front.data_structures.symbol.Symbol;
import front.data_types.TypeSub;
import front.error.ErrorArgTypes;
import front.error.ErrorProcExists;
import java.util.ArrayList;

import static front.data_types.Types.FUNCTION;
import static front.data_types.Types.METHOD;

public class SymDECFUNC extends SymBase {

    private final SymTYPE TYPE;
    private final SymID ID;
    private final SymARGLIST ARGLIST;
    private final SymSENTLIST SENTLIST;
    private final SymRETURN RETURN;

    public SymDECFUNC(SymTYPE a, SymID b, SymARGLIST c, SymSENTLIST d, SymRETURN e, int[] lc) throws ErrorArgTypes, ErrorProcExists {
        super("F", 0);
        this.TYPE = a;
        this.ID = b;
        this.ARGLIST = c;
        this.SENTLIST = d;
        this.RETURN = e;

        Symbol n = new Symbol(ID.getID(), FUNCTION, TYPE.getType(), (ArrayList<Symbol>) ts.getParams().clone());
        if(!this.TYPE.getType().equals(this.RETURN.getRETURNTYPE().getType())) {
            new ErrorArgTypes().printError(lc, ID.getID());
            throw new ErrorArgTypes();
        }

        if (ts.existInTs(n.getId())) {
            Symbol sym = ts.get(n.getId());
            if (sym.getType() == METHOD || sym.getType() == FUNCTION) {
                new ErrorProcExists().printError(lc, n.getId());
                throw new ErrorProcExists();
            } else {
                ts.insertElement(n);
            }
        } else {
            insertSymbolArgs(n.getArgs());
            ts.insertElement(n);
        }
        ts.emptyParams();
        tp.getProc(ID.getID()).setType_return(TypeSub.valueOf(TYPE.getType().toUpperCase()));
    }

    private void insertSymbolArgs(ArrayList<Symbol> args){
        if (args != null){
            for (Symbol s : args){
                ts.insertElement(s);
                tac.newVar(s.getId(), s.getSubtype());
            }
        }
    }

    public SymTYPE getType() {
        return TYPE;
    }

}
