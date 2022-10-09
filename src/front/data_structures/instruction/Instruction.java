package front.data_structures.instruction;


//Instruccio en 3@C
public class Instruction {

    private String operator;
    private Operator operand1;
    private Operator operand2;
    private Operator dest;

    private String tac_format;

    public Instruction(String operator, String oper1, String op2, String dest, String tac_format) {
        this.operator = operator;
        this.operand1 = new Operator(oper1);
        this.operand2 = new Operator(op2);
        this.dest = new Operator(dest);


        this.tac_format = tac_format;
    }


    public String getOperator() {
        return operator;
    }

    public Operator getOperand1() {
        return operand1;
    }

    public Operator getOperand2() {
        return operand2;
    }

    public Operator getDest() {
        return dest;
    }

    public String getTac_format() {
        return tac_format;
    }

    public void modInstruction(String operator, String op1, String op2, String dest, String tac_format) {
        this.operator = operator;
        this.operand1 = new Operator(op1);
        this.operand2 = new Operator(op2);
        this.dest = new Operator(dest);


        this.tac_format = tac_format;
    }

    @Override
    public String toString() {
        return  " | " + operator + " | "  + operand1 + " | " + operand2 + " | "
                + dest + " | (" + tac_format + ")";
    }
}
