package front.symbols;

import front.data_structures.Dimension;
import front.data_structures.Subrange;
import front.data_structures.symbol.Symbol;
import front.data_types.TypeSub;
import front.error.ErrorRangeTypes;
import front.error.ErrorVarExists;

import java.util.ArrayList;
import java.util.Collections;


public class SymDECARRAY extends SymBase {
    private final SymTYPE TYPE;
    private final SymID ID;
    private SymDECLISTDIM IDLISTDIM;


    public SymDECARRAY(SymTYPE a, SymID b, SymDECLISTDIM c, int[] lc) {
        super("DECARRAY", 0);
        this.TYPE = a;
        this.ID = b;
        this.IDLISTDIM = c;

        ArrayList<Dimension> dimensions = new ArrayList();
        while (IDLISTDIM != null) {
            if (IDLISTDIM.getSUBRANGE().getType().equalsIgnoreCase(String.valueOf(TypeSub.INTEGER))) {
                int val1 = Integer.parseInt(IDLISTDIM.getSUBRANGE().getNum1().getValue());
                int val2 = Integer.parseInt(IDLISTDIM.getSUBRANGE().getNum2().getValue());
                Subrange subrange = new Subrange(val1, val2);
                dimensions.add(new Dimension(subrange));
            }
            else {
                new ErrorRangeTypes().printError(lc, ID.getID());
            }

            IDLISTDIM = IDLISTDIM.getDECLISTDIM();
        }

        Collections.reverse(dimensions);

        Symbol s = new Symbol(ID.getID(), TYPE.getType(), dimensions);
        if (!ts.existInTs(ID.getID())) {
            ts.insertElement(s);
        } else {
            new ErrorVarExists().printError(lc, ID.getID());
        }



    }



    public SymID getID() {
        return ID;
    }
}
