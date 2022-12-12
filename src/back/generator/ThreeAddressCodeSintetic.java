package back.generator;


import back.data_structures.Parametro;
import back.data_structures.instructions.InstructionList;
import front.data_structures.procedure.Procedure;
import front.data_structures.variable.Variable;
import front.data_types.TypeSub;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static back.data_structures.Operation.*;

public class ThreeAddressCodeSintetic {
    private final String TAC_PATH = "files_output/codiIntermitg.txt";
    private final String TVAR_PATH = "files_output/Tables/Taula_variables.txt";
    private final String TPROC_PATH = "files_output/Tables/Taula_procediments.txt";

    private InstructionList instructionList = new InstructionList();
    private ArrayList<Variable> tv = new ArrayList<>();
    private ArrayList<Procedure> tp = new ArrayList<>();

    public ThreeAddressCodeSintetic() {
        loadInstructions();
        loadTv();
        loadTp();
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
                        case "param_c":
                            instructionList.addInst(PARAM_C, null, null, instruction.split(" ")[1]);
                            break;
                        case "param_s":
                            instructionList.addInst(PARAM_S, null, null, instruction.split(" ")[1]);
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
            BufferedReader br = baseTable(TVAR_PATH);
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
                            (split.length==7)? split[6] : null //variable pot tenir valor o no
                    ));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTp(){
        try {
            BufferedReader br = baseTable(TPROC_PATH);
            String proc;
            String [] split;

            while ((proc = br.readLine()) != null) {
                if (!proc.equals("")) {
                    split = proc.split("\\t+");

                    tp.add(new Procedure(
                            Integer.parseInt(split[0]),
                            Integer.parseInt(split[1]),
                            split[2],
                            new ArrayList<Parametro>(), //FIXME
                            Integer.parseInt(split[4]),
                            TypeSub.valueOf(split[5])
                    ));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedReader baseTable(String path) {
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);

            for (int i=0; i<3 && (br.readLine()) != null; i++){
            }

            return br;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public InstructionList getInstructionList() {
        return instructionList;
    }

    public ArrayList<Variable> getTv() {
        return tv;
    }

    public ArrayList<Procedure> getTp() {
        return tp;
    }

    public Variable getVar(String id){
        for(Variable v : this.tv){
            if(v.getName().equals(id)){
                return v;
            }
        }
        return null;
    }

    public Procedure getProc(String id){
        for(Procedure p : this.tp){
            if(p.getStart_label().equals(id)){
                return p;
            }
        }
        return null;
    }

}
