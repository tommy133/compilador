package front.symbols;

import front.data_structures.symbol.Symbol;
import front.data_types.Subrange;
import front.data_types.TypeSub;
import front.data_types.Types;
import front.error.ErrorRangeTypes;
import front.error.ErrorVarExists;

import static front.data_types.Types.ARRAY;


public class SymDECARRAY extends SymBase {
    private SymTYPE TYPE;
    private SymID ID;
    private SymIDLISTDIM IDLISTDIM;


    public SymDECARRAY(SymTYPE a, SymID b, SymIDLISTDIM c,  int[] lc) {
        super("DECARRAY", 0);
        this.TYPE = a;
        this.ID = b;
        this.IDLISTDIM = c;

        if (IDLISTDIM.getSUBRANGE().getType().equalsIgnoreCase(String.valueOf(TypeSub.INTEGER))){
            int val1 = Integer.parseInt(IDLISTDIM.getSUBRANGE().getNum1().getValue());
            int val2 = Integer.parseInt(IDLISTDIM.getSUBRANGE().getNum2().getValue());
            Subrange subrange = new Subrange(val1, val2);
            Symbol s = new Symbol(ID.getID(), ARRAY, TYPE.getType(), subrange,null);
            if (!ts.exist(ID.getID())) {
                ts.insertElement(s);
                System.out.println(1);
            } else {
                new ErrorVarExists().printError(lc, ID.getID());
            }
        } else {
            new ErrorRangeTypes().printError(lc, ID.getID());
        }

    }


    public SymTYPE getTYPE() {
        return TYPE;
    }

    public SymIDLISTDIM getIDLISTDIM() {
        return IDLISTDIM;
    }

    public SymID getID() {
        return ID;
    }
}
