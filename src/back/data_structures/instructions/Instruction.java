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

    public void modInstruccion(Operation operation, String operand1, String operand2, String destiny){
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

    public boolean esCondicional(){
        return operation==Operation.IF || operation==Operation.IFIGUAL || operation==Operation.IFMAYOR ||
                operation==Operation.IFMENOR || operation==Operation.IFMAYORIGUAL ||
                operation==Operation.IFMENORIGUAL || operation==Operation.IFDIFERENTE;
    }

    public boolean esArtim(){
        return operation==Operation.SUMA || operation==Operation.RESTA || operation==Operation.MULTIPLICACION ||
                operation==Operation.DIVISION  || operation==Operation.AND || operation==Operation.OR;
    }

    public boolean esParam(){
        return operation == Operation.PARAM_C || operation == Operation.PARAM_S;
    }

    @Override
    public String toString() {
        return  " | " + operation + " | "  + operand1 + " | " + operand2 + " | "
                + destiny + " |";
    }
}
