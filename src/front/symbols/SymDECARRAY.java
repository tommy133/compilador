package front.symbols;

import front.data_structures.Dimension;
import front.data_structures.symbol.Symbol;
import front.data_structures.Subrange;
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

    public SymDECARRAY(SymTYPE a, SymID b, SymDECLISTDIM c, int[] lc) throws ErrorRangeTypes, ErrorVarExists {
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
                throw new ErrorRangeTypes();
            }

            IDLISTDIM = IDLISTDIM.getDECLISTDIM();
        }

        Collections.reverse(dimensions);

        Symbol s = new Symbol(ID.getID(), ARRAY, TYPE.getType(), dimensions,null);
        if (!ts.exist(ID.getID())) {
            ts.insertElement(s);
        } else {
            new ErrorVarExists().printError(lc, ID.getID());
            throw new ErrorVarExists();
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
