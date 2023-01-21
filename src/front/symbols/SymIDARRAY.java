package front.symbols;

import front.data_structures.Dimension;
import front.data_structures.Subrange;
import front.data_types.TypeSub;
import front.error.ErrorRangeTypes;

import java.util.ArrayList;
import java.util.Collections;

public class SymIDARRAY extends SymBase{
    private SymID ID;
    private SymIDLISTDIM IDLISTDIM;

    public SymIDARRAY(SymID ID, SymIDLISTDIM IDLISTDIM) {
        super("IDARRAY", 0);
        this.ID = ID;
        this.IDLISTDIM = IDLISTDIM;
    }

    public SymID getID() {
        return ID;
    }

    public SymIDLISTDIM getIDLISTDIM() {
        return IDLISTDIM;
    }

    public ArrayList<Integer> getIdxs(){
        ArrayList idxs = new ArrayList<Integer>();
        while (IDLISTDIM != null) {
            idxs.add(Integer.parseInt(IDLISTDIM.getOPERANDX().getSUBTYPE().getValor()));

            IDLISTDIM = IDLISTDIM.getIDLISTDIM();
        }

        Collections.reverse(idxs);

        return idxs;
    }

}
