package back.data_structures.instructions;

//Instruccio en 3@C
public class Instruction {
    private String operator;
    private String operand1;
    private String operand2;
    private String destiny;


    public Instruction(String operator, String operand1, String operand2, String destiny) {
        this.operator = operator;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.destiny = destiny;

    }


    public String getOperator() {
        return operator;
    }

    public String getOperand1() {
        return operand1;
    }

    public String getOperand2() {
        return operand2;
    }

    public String getDestiny() {
        return destiny;
    }


    @Override
    public String toString() {
        return  " | " + operator + " | "  + operand1 + " | " + operand2 + " | "
                + destiny + " | (";
    }
}
