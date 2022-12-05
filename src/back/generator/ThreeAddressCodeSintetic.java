package back.generator;


import back.data_structures.instructions.InstructionList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ThreeAddressCodeSintetic {
    private final String TAC_PATH = "files_output/codiIntermitg.txt";
    private final String TVAR_PATH = "files_output/Tables/Taula_variables.txt";
    private final String TPROC_PATH = "files_output/Tables/Taula_procediments.txt";

    private InstructionList instructionList = new InstructionList();

    public ThreeAddressCodeSintetic() {
        createInstructions();
    }

    private void createInstructions() {

        try {
            FileReader fr = new FileReader(TAC_PATH);
            BufferedReader br = new BufferedReader(fr);
            String instruction;

            while ((instruction = br.readLine()) != null) {


                if (!instruction.equalsIgnoreCase("")) {

                    switch (instruction.split(" ")[0]) {

                        case "pmb":
                            instructionList.addInst("pmb",  null, null,  instruction.split(" ")[1]);
                            break;

                        case "goto":
                            instructionList.addInst("goto", null, null, instruction.split(" ")[1]);
                            break;
                        case "call":
                            instructionList.addInst("call", null, null, instruction.split(" ")[1]);
                            break;
                        case "param":
                            instructionList.addInst("param", instruction.split(" ")[1], null, null);
                            break;

                        case "rtn":
                            instructionList.addInst("rtn", null, null, instruction.split(" ")[1]);
                            break;

                        default:

                            if (instruction.contains(":skip")) { //Etiqueta
                                instructionList.addInst("skip", null, null, instruction.split(":")[0]);
                            } else if (instruction.split(" ")[1].equalsIgnoreCase("=") && !instruction.split(" ")[2].equals("call")) {

                                if (instruction.split(" ").length < 4) { //Longitud  menor que 4, es una asignación simple (a = b).
                                    instructionList.addInst("copy", instruction.split(" ")[2], null, instruction.split(" ")[0]);
                                } else {
                                    switch (instruction.split(" ")[3]) {
                                        case "+" -> instructionList.addInst("add", instruction.split(" ")[2], instruction.split(" ")[4], instruction.split(" ")[0]);
                                        case "-" -> instructionList.addInst("sub", instruction.split(" ")[2], instruction.split(" ")[4], instruction.split(" ")[0]);
                                        case "*" -> instructionList.addInst("mul", instruction.split(" ")[2], instruction.split(" ")[4], instruction.split(" ")[0]);
                                        case "/" -> instructionList.addInst("div", instruction.split(" ")[2], instruction.split(" ")[4], instruction.split(" ")[0]);
                                        case "%" -> instructionList.addInst("mod", instruction.split(" ")[2], instruction.split(" ")[4], instruction.split(" ")[0]);
                                        default -> //Es una assignació de string.
                                                instructionList.addInst("copy", instruction.split("=")[1].replaceAll("\"", ""), null, instruction.split(" ")[0]);
                                    }
                                }
                            } else if (instruction.split(" ")[0].equalsIgnoreCase("if")) {
                                switch (instruction.split(" ")[2]) {
                                    case "&&":
                                        break;
                                    case "!!":
                                        break;
                                    case ">=":
                                        instructionList.addInst("if::"  + "GE",  instruction.split(" ")[1], instruction.split(" ")[3], instruction.split(" ")[5]);
                                        break;
                                    case "=":
                                        instructionList.addInst("if::"  + "EQ",  instruction.split(" ")[1], instruction.split(" ")[3], instruction.split(" ")[5]);
                                        break;
                                    case ">":
                                        instructionList.addInst("if::"  + "GT", instruction.split(" ")[1], instruction.split(" ")[3], instruction.split(" ")[5]);
                                        break;
                                    case "<=":
                                        instructionList.addInst("if::" +  "LE",  instruction.split(" ")[1], instruction.split(" ")[3], instruction.split(" ")[5]);
                                        break;
                                    case "<":
                                        instructionList.addInst("if::"  + "LT",  instruction.split(" ")[1], instruction.split(" ")[3], instruction.split(" ")[5]);
                                        break;
                                    case "!=":
                                        instructionList.addInst("if::"  + "NE",  instruction.split(" ")[1], instruction.split(" ")[3], instruction.split(" ")[5]);
                                }
                            } else if (instruction.split(" ")[2].equals("call")) {
                                instructionList.addInst("call", instruction.split(" ")[0], null, instruction.split(" ")[3]);
                            }
                    }
                }
            }

            //System.out.println(instructionList.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
