package front.data_structures;

public class Subrange {
    private final int val1;
    private final int val2;

    public Subrange(int val1, int val2) {
        this.val1 = val1; //limit inferior
        this.val2 = val2; //limit superior
    }

    public int getVal1() {
        return val1;
    }

    public int getVal2() {
        return val2;
    }
}
