package back.data_structures.instructions;

import back.data_structures.Operation;

//Instruccio en 3@C
public class Instruction {
    private Operation operation;
    private String operand1;
    private String operand2;
    private String destiny;


    public Instruction(Operation operation, String operand1, String operand2, String destiny) {
        this.operation = operation;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.destiny = destiny;

    }


    public Operation getOperation() {
        return operation;
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
        return  " | " + operation + " | "  + operand1 + " | " + operand2 + " | "
                + destiny + " |";
    }
}