package front.symbols;

import front.data_structures.symbol.Symbol;
import front.data_types.Subrange;
import front.data_types.TypeSub;
import front.error.ErrorRangeTypes;
import front.error.ErrorVarExists;

import java.util.ArrayList;
import java.util.Collections;

import static front.data_types.Types.ARRAY;


public class SymDECARRAY extends SymBase {
    private SymTYPE TYPE;
    private SymID ID;
    private SymDECLISTDIM IDLISTDIM;


    public SymDECARRAY(SymTYPE a, SymID b, SymDECLISTDIM c, int[] lc) {
        super("DECARRAY", 0);
        this.TYPE = a;
        this.ID = b;
        this.IDLISTDIM = c;

        ArrayList<Subrange> subranges = new ArrayList();
        while (IDLISTDIM != null) {
            if (IDLISTDIM.getSUBRANGE().getType().equalsIgnoreCase(String.valueOf(TypeSub.INTEGER))) {
                int val1 = Integer.parseInt(IDLISTDIM.getSUBRANGE().getNum1().getValue());
                int val2 = Integer.parseInt(IDLISTDIM.getSUBRANGE().getNum2().getValue());
                Subrange subrange = new Subrange(val1, val2);
                subranges.add(subrange);
            }
            else {
                new ErrorRangeTypes().printError(lc, ID.getID());
            }

            IDLISTDIM = IDLISTDIM.getDECLISTDIM();
        }

        Collections.reverse(subranges);

        Symbol s = new Symbol(ID.getID(), ARRAY, TYPE.getType(), subranges,null);
        if (!ts.exist(ID.getID())) {
            ts.insertElement(s);
        } else {
            new ErrorVarExists().printError(lc, ID.getID());
        }



    }




    public SymTYPE getTYPE() {
        return TYPE;
    }

    public SymDECLISTDIM getIDLISTDIM() {
        return IDLISTDIM;
    }

    public SymID getID() {
        return ID;
    }
}
