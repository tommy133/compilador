package front.data_structures;

public class Dimension {
    private int idx;
    private Subrange subrange;

    public Dimension(int idx, Subrange subrange) {
        this.idx = idx;
        this.subrange = subrange;
    }

    public Dimension(Subrange subrange) {
        this.subrange = subrange;
    }

    public int getIdx() {
        return idx;
    }

    public Subrange getSubrange() {
        return subrange;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }
}
