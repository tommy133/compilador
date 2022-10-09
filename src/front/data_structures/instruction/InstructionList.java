package front.data_structures.instruction;

import java.util.ArrayList;

public class InstructionList {

    private ArrayList<Instruction> inst_list;

    public InstructionList() {
        inst_list = new ArrayList<>();
    }


    public void addInst(String operator, String op1, String op2, String destination, String tac_format) {

        inst_list.add(new Instruction(operator, op1, op2, destination, tac_format));
    }

    //Borra una instrucció
    public void deleteInst(int i) {
        inst_list.remove(i);
    }


    public ArrayList<Instruction> getInst_list() {
        return inst_list;
    }

    @Override
    public String toString() {
            StringBuilder s = new StringBuilder();
            for (Instruction inst : inst_list) {
                s.append(inst.toString()).append("\n");
            }

            return s.toString();
    }
}
