package back.generator;


import back.data_structures.instructions.InstructionList;
import front.data_structures.procedure.Procedure;
import front.data_structures.variable.Variable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static back.data_structures.Operation.*;

public class ThreeAddressCodeSintetic {
    private final String TAC_PATH = "files_output/codiIntermitg.txt";
    private final String TVAR_PATH = "files_output/Tables/Taula_variables.txt";
    private final String TPROC_PATH = "files_output/Tables/Taula_procediments.txt";

    private InstructionList instructionList = new InstructionList();
    private ArrayList<Variable> tv = new ArrayList<>();
    private ArrayList<Procedure> tp = new ArrayList<>();

    public ThreeAddressCodeSintetic() {
        //loadInstructions();
        //loadTv();
    }

    private void loadInstructions() {

        try {
            FileReader fr = new FileReader(TAC_PATH);
            BufferedReader br = new BufferedReader(fr);
            String instruction;

            while ((instruction = br.readLine()) != null) {


                if (!instruction.equalsIgnoreCase("")) {

                    switch (instruction.split(" ")[0]) {

                        case "pmb":
                            instructionList.addInst(PMB,  null, null,  instruction.split(" ")[1]);
                            break;

                        case "goto":
                            instructionList.addInst(GOTO, null, null, instruction.split(" ")[1]);
                            break;
                        case "call":
                            instructionList.addInst(CALL, null, null, instruction.split(" ")[1]);
                            break;
                        case "param":
                            if (instruction.split(" ")[1] instanceof String){
                                instructionList.addInst(PARAM_C, instruction.split(" ")[1], null, null);
                            } else {
                                instructionList.addInst(PARAM_S, instruction.split(" ")[1], null, null);
                            }

                            break;

                        case "rtn":
                            instructionList.addInst(RTN, null, null, instruction.split(" ")[1]);
                            break;

                        default:

                            if (instruction.contains(":skip")) { //Etiqueta
                                instructionList.addInst(SKIP, null, null, instruction.split(":")[0]);
                            } else if (instruction.split(" ")[1].equalsIgnoreCase("=") && !instruction.split(" ")[2].equals("call")) {

                                if (instruction.split(" ").length < 4) { //asignació simple (a = b).
                                    instructionList.addInst(ASIGNA, instruction.split(" ")[2], null, instruction.split(" ")[0]);
                                } else {
                                    switch (instruction.split(" ")[3]) {
                                        case "+" -> instructionList.addInst(SUMA, instruction.split(" ")[2], instruction.split(" ")[4], instruction.split(" ")[0]);
                                        case "-" -> instructionList.addInst(RESTA, instruction.split(" ")[2], instruction.split(" ")[4], instruction.split(" ")[0]);
                                        case "*" -> instructionList.addInst(MULTIPLICACION, instruction.split(" ")[2], instruction.split(" ")[4], instruction.split(" ")[0]);
                                        case "/" -> instructionList.addInst(DIVISION, instruction.split(" ")[2], instruction.split(" ")[4], instruction.split(" ")[0]);
                                        case "%" -> instructionList.addInst(MODULO, instruction.split(" ")[2], instruction.split(" ")[4], instruction.split(" ")[0]);
                                        default -> //Es una assignació de string.
                                                instructionList.addInst(ASIGNA, instruction.split("=")[1].replaceAll("\"", ""), null, instruction.split(" ")[0]);
                                    }
                                }
                            } else if (instruction.split(" ")[0].equalsIgnoreCase("if")) {
                                switch (instruction.split(" ")[2]) {
                                    case ">=":
                                        instructionList.addInst(IFMAYORIGUAL,  instruction.split(" ")[1], instruction.split(" ")[3], instruction.split(" ")[5]);
                                        break;
                                    case "=":
                                        instructionList.addInst(IFIGUAL,  instruction.split(" ")[1], instruction.split(" ")[3], instruction.split(" ")[5]);
                                        break;
                                    case ">":
                                        instructionList.addInst(IFMAYOR, instruction.split(" ")[1], instruction.split(" ")[3], instruction.split(" ")[5]);
                                        break;
                                    case "<=":
                                        instructionList.addInst(IFMENORIGUAL,  instruction.split(" ")[1], instruction.split(" ")[3], instruction.split(" ")[5]);
                                        break;
                                    case "<":
                                        instructionList.addInst(IFMENOR,  instruction.split(" ")[1], instruction.split(" ")[3], instruction.split(" ")[5]);
                                        break;
                                    case "!=":
                                        instructionList.addInst(IFDIFERENTE,  instruction.split(" ")[1], instruction.split(" ")[3], instruction.split(" ")[5]);
                                }
                            } else if (instruction.split(" ")[2].equals("call")) {
                                instructionList.addInst(CALL, instruction.split(" ")[0], null, instruction.split(" ")[3]);
                            }
                    }
                }
            }

            System.out.println(instructionList.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTv(){
        try {
            BufferedReader br = baseTv();
            String variable;
            String [] split;

            while ((variable = br.readLine()) != null) {
                if (!variable.equals("")) {
                    split = variable.split("\\t+");
                    tv.add(new Variable(
                            split[0],
                            Integer.parseInt(split[1]),
                            split[2],
                            Integer.parseInt(split[3]),
                            Integer.parseInt(split[4]),
                            split[5],
                            split[6]
                    ));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTp(){

    }

    private BufferedReader baseTv() {
        try {
            FileReader fr = new FileReader(TVAR_PATH);
            BufferedReader br = new BufferedReader(fr);

            for (int i=0; i<3 && (br.readLine()) != null; i++){
            }

            return br;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
