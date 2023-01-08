package front.symbols;

public class SymSUBRANGE {
    private SymID id1, id2;
    private SymNUM num1, num2;

    private String type;

    public SymSUBRANGE(SymID id1, SymID id2) {
        this.id1 = id1;
        this.id2 = id2;
        type = id1.getType();

    }

    public SymSUBRANGE(SymNUM num1, SymNUM num2) {
        this.num1 = num1;
        this.num2 = num2;
        type = num1.getType();
    }

    public SymNUM getNum1() {
        return num1;
    }

    public SymNUM getNum2() {
        return num2;
    }

    public String getType() {
        return type;
    }
}
