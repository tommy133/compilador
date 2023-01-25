package back.generator;


import back.data_structures.Parametro;
import back.data_structures.instructions.InstructionList;
import front.data_structures.procedure.Procedure;
import front.data_structures.symbol.Symbol;
import front.data_structures.variable.Variable;
import front.data_types.TypeSub;
import front.data_types.Types;

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
    private final String TSYM_PATH = "files_output/Tables/Taula_simbols.txt";

    private InstructionList instructionList = new InstructionList();
    private ArrayList<Variable> tv = new ArrayList<>();
    private ArrayList<Procedure> tp = new ArrayList<>();
    private ArrayList<Symbol> ts = new ArrayList<>();

    public ThreeAddressCodeSintetic() {
        loadTs();
        loadTv();
        loadTp();
        loadInstructions();
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
                            instructionList.addInst(PARAM_C, null, null, instruction.split(" ")[1].replace("\"", ""));
                            break;
                        case "param_s":
                            instructionList.addInst(PARAM_S, null, null, instruction.split(" ")[1]);
                            break;
                        case "rtn":
                            instructionList.addInst(RTN, instruction.split(" ")[2], null, instruction.split(" ")[1]);
                            break;

                        default:

                            if (instruction.contains(":skip")) { //Etiqueta
                                instructionList.addInst(SKIP, null, null, instruction.split(":")[0]);
                            } else if (instruction.split(" ")[1].equalsIgnoreCase("=") && !instruction.split(" ")[2].equals("call")) {

                                if (instruction.split(" ").length < 4) { //asignació simple (a = b).
                                    String [] splitCode = instruction.split(" ");
                                    if (instruction.contains("[")) {
                                        String [] splitAdress = instruction.split("[\\[\\]=\\s]+");
                                        if (splitCode[0].contains("[")){
                                            instructionList.addInst(ASIGNA, splitAdress[2], splitAdress[1], splitAdress[0]);
                                        }
                                        else instructionList.addInst(ASIGNA, splitAdress[1], splitAdress[2], splitAdress[0]);

                                    } else instructionList.addInst(ASIGNA, splitCode[2], null, splitCode[0]);

                                } else {
                                    switch (instruction.split(" ")[3]) {
                                        case "+" -> instructionList.addInst(SUMA, instruction.split(" ")[2], instruction.split(" ")[4], instruction.split(" ")[0]);
                                        case "-" -> instructionList.addInst(RESTA, instruction.split(" ")[2], instruction.split(" ")[4], instruction.split(" ")[0]);
                                        case "*" -> instructionList.addInst(MULTIPLICACION, instruction.split(" ")[2], instruction.split(" ")[4], instruction.split(" ")[0]);
                                        case "/" -> instructionList.addInst(DIVISION, instruction.split(" ")[2], instruction.split(" ")[4], instruction.split(" ")[0]);
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
                                    default:
                                        instructionList.addInst(IF,  instruction.split(" ")[1], null, instruction.split(" ")[4]);
                                }
                            } else if (instruction.split(" ")[2].equals("call")) {
                                instructionList.addInst(CALL, null, null, instruction.split(" ")[3]);
                                if (instruction.split(" ")[1].equals("=")){
                                    instructionList.addInst(ASIGNA, getReturnProc(instruction.split(" ")[3]), null, instruction.split(" ")[0]);
                                }
                            }
                    }
                }
            }
            br.close();
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
            br.close();
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
                            (split[3].equals("[]"))? new ArrayList<Parametro>() : extractParamsTs(split[3]),
                            Integer.parseInt(split[4]),
                            TypeSub.valueOf(split[5])
                    ));
                }

            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTs(){
        try {
            BufferedReader br = baseTable(TSYM_PATH);
            String sym;
            String [] split;

            while ((sym = br.readLine()) != null) {
                if (!sym.equals("")) {
                    split = sym.split("\\t+");
                    ts.add(new Symbol(
                            split[0],
                            Types.valueOf(split[2]),
                            split[3],
                            null
                    ));
                }

            }
            br.close();
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

    private ArrayList<Parametro> extractParamsTs(String params){
        params = params.substring(1, params.length() - 1);

        String[] stringArray = params.split(", ");

        ArrayList<Parametro> parametros = new ArrayList<>();

        for (String s : stringArray) {
            Symbol sym = getSymbol(s);
            parametros.add(new Parametro(sym.getId(), TypeSub.valueOf(sym.getSubtype().toUpperCase())));
        }
        return parametros;
    }

    public Symbol getSymbol(String id){
        for (Symbol s : ts){
            if (s.getId().equals(id)) return s;
        }
        return null;
    }

    public InstructionList getInstructionList() {
        return instructionList;
    }

    public void updateInstructionList(InstructionList instructionList){
        this.instructionList = instructionList;
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

    public void deleteVar(String id){
        for(int i = 0; i < tv.size(); i++){
            if(tv.get(i).getName().equals(id)){
                this.tv.remove(i);
            }
        }
    }

    public Procedure getProc(String id){
        for(Procedure p : this.tp){
            if(p.getStart_label().equals(id)){
                return p;
            }
        }
        return null;
    }

    public String getReturnProc(String id){
        Procedure proc = getProc(id);
        if (proc == null) return null;
        switch (proc.getType_return()){
            case INTEGER:
                return "retInt";
            case LOGIC:
                return "retBool";
            case STRING:
                return "retStr";
            default: return null;
        }
    }

}
