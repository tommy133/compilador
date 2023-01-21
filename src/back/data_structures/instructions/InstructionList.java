package back.data_structures.instructions;

import back.data_structures.Operation;

import java.util.ArrayList;

public class InstructionList {
    private ArrayList<Instruction> inst_list;

    public InstructionList() {
        inst_list = new ArrayList<>();
    }

    public Instruction getInstruction(int idx){
        return inst_list.get(idx);
    }

    public void addInst(Operation operation, String op1, String op2, String destination) {

        inst_list.add(new Instruction(operation, op1, op2, destination));
    }

    //Borra una instrucció
    public void deleteInst(int i) {
        inst_list.remove(i);
    }


    public ArrayList<Instruction> getInst_list() {
        return inst_list;
    }

    public int getSize(){return inst_list.size();}

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Instruction inst : inst_list) {
            s.append(inst.toString()).append("\n");
        }

        return s.toString();
    }
}
