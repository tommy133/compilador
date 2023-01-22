package front.symbols;

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
        SymIDLISTDIM IDLISTDIMLOCAL = this.IDLISTDIM;
        ArrayList idxs = new ArrayList<Integer>();
        while (IDLISTDIMLOCAL != null) {
            SymSUBTYPE idx = IDLISTDIMLOCAL.getOPERANDX().getSUBTYPE();
            if (idx.getID() == null){
                idxs.add(Integer.parseInt(idx.getValor()));
            } else {
                String value = tac.getVar(idx.getID().getID()).getValue();
                idxs.add(Integer.parseInt(value));
            }

            IDLISTDIMLOCAL = IDLISTDIMLOCAL.getIDLISTDIM();
        }

        Collections.reverse(idxs);

        return idxs;
    }

}
