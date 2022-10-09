/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.symbols;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SymSTART extends SymBase {

    private SymMETFUNC METFUNC;
    private SymDECMP DECMP;


    //Inicialitza estructures de dades, crida al optimitzador i al ensamblador

    public SymSTART(SymMETFUNC a, SymDECMP b) {
        super("START", 0);
        this.METFUNC = a;
        this.DECMP = b;

        procedureTable.calculateLocalVarSize(variableTable);

        ts.closeFile();
        variableTable.closeFile();
        procedureTable.closeFile();
        tac.closeFile();

        createInstructions();

    }



    private void createInstructions() {

        try {
            FileReader fr = new FileReader(tac.getFilePath());
            BufferedReader br = new BufferedReader(fr);
            String instruction;

            while ((instruction = br.readLine()) != null) {


                if (!instruction.equalsIgnoreCase("")) {

                    switch (instruction.split(" ")[0]) {

                        case "pmb":
                            instructionList.addInst("pmb",  null, null,  instruction.split(" ")[1], instruction);
                            break;

                        case "goto":
                            instructionList.addInst("goto", null, null, instruction.split(" ")[1], instruction);
                            break;
                        case "call":
                            instructionList.addInst("call", null, null, instruction.split(" ")[1], instruction);
                            break;
                        case "param":
                            instructionList.addInst("param", instruction.split(" ")[1], null, null, instruction);
                            break;

                        case "rtn":
                            instructionList.addInst("rtn", null, null, instruction.split(" ")[1], instruction);
                            break;

                        default:

                            if (instruction.contains(":skip")) { //Etiqueta
                                instructionList.addInst("skip", null, null, instruction.split(":")[0], instruction);
                            } else if (instruction.split(" ")[1].equalsIgnoreCase("=") && !instruction.split(" ")[2].equals("call")) {

                                if (instruction.split(" ").length < 4) { //Longitud  menor que 4, es una asignación simple (a = b).
                                    instructionList.addInst("copy", instruction.split(" ")[2], null, instruction.split(" ")[0], instruction);
                                } else {
                                    switch (instruction.split(" ")[3]) {
                                        case "+" -> instructionList.addInst("add", instruction.split(" ")[2], instruction.split(" ")[4], instruction.split(" ")[0], instruction);
                                        case "-" -> instructionList.addInst("sub", instruction.split(" ")[2], instruction.split(" ")[4], instruction.split(" ")[0], instruction);
                                        case "*" -> instructionList.addInst("mul", instruction.split(" ")[2], instruction.split(" ")[4], instruction.split(" ")[0], instruction);
                                        case "/" -> instructionList.addInst("div", instruction.split(" ")[2], instruction.split(" ")[4], instruction.split(" ")[0], instruction);
                                        case "%" -> instructionList.addInst("mod", instruction.split(" ")[2], instruction.split(" ")[4], instruction.split(" ")[0], instruction);
                                        default -> //Es una assignació de string.
                                                instructionList.addInst("copy", instruction.split("=")[1].replaceAll("\"", ""), null, instruction.split(" ")[0], instruction);
                                    }
                                }
                            } else if (instruction.split(" ")[0].equalsIgnoreCase("if")) {
                                switch (instruction.split(" ")[2]) {
                                    case "&&":
                                        break;
                                    case "!!":
                                        break;
                                    case ">=":
                                        instructionList.addInst("if::"  + "GE",  instruction.split(" ")[1], instruction.split(" ")[3], instruction.split(" ")[5], instruction);
                                        break;
                                    case "=":
                                        instructionList.addInst("if::"  + "EQ",  instruction.split(" ")[1], instruction.split(" ")[3], instruction.split(" ")[5], instruction);
                                        break;
                                    case ">":
                                        instructionList.addInst("if::"  + "GT", instruction.split(" ")[1], instruction.split(" ")[3], instruction.split(" ")[5], instruction);
                                        break;
                                    case "<=":
                                        instructionList.addInst("if::" +  "LE",  instruction.split(" ")[1], instruction.split(" ")[3], instruction.split(" ")[5], instruction);
                                        break;
                                    case "<":
                                        instructionList.addInst("if::"  + "LT",  instruction.split(" ")[1], instruction.split(" ")[3], instruction.split(" ")[5], instruction);
                                        break;
                                    case "!=":
                                        instructionList.addInst("if::"  + "NE",  instruction.split(" ")[1], instruction.split(" ")[3], instruction.split(" ")[5], instruction);
                                }
                            } else if (instruction.split(" ")[2].equals("call")) {
                                instructionList.addInst("call", instruction.split(" ")[0], null, instruction.split(" ")[3], instruction);
                            }
                    }
                }
            }

            System.out.println(instructionList.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
