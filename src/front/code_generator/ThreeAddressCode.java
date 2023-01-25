package front.code_generator;


import front.data_structures.Stack;
import front.data_structures.variable.Variable;
import front.data_structures.variable.VariableTable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class ThreeAddressCode {

    private int n_var, label_num, disp = 0;
    private BufferedWriter bw;
    private final Stack<String> true_stack, false_stack, end_stack, start_stack;
    private final ArrayList<String> operands, instruction_list;
    private String temp_id = null, cur_prog, cur_type = "";
    private final VariableTable tv;

    private static final String FILE_PATH = "files_output/codiIntermitg.txt";


    public ThreeAddressCode(VariableTable tv) {

        this.n_var = 0;

        this.true_stack = new Stack<>();
        this.false_stack = new Stack<>();
        this.end_stack = new Stack<>();
        this.start_stack = new Stack<>();

        this.tv = tv;
        this.operands = new ArrayList<>();
        this.instruction_list = new ArrayList<>();

        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE_PATH, true), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        }
    }

    public void assign(String dest, String val) {

        instruction_list.add(dest + " = " + val + "\n");
    }

    public String newTempVar(String type) {

        if (type.equals("")) type = cur_type;

        tv.addRow(new Variable
            ("t" +
                    n_var,
                    n_var,
                    cur_prog,
                tv.calculateStore(type, ""),
                    disp,
            type,"")
        );

        incDisplacement(tv.calculateStore(type, ""));
        cur_type = type;
        
        return "t" + n_var++;
    }


    public String newTempVar(String type, String val) {
        if (type.equals("")) {
            type = cur_type;
        }

        tv.addRow(new Variable
            ("t" + n_var,
                    n_var,
                    cur_prog,
                tv.calculateStore(type, val),
                    disp,
                type,
            val)
        );

        cur_type = type;
        incDisplacement(tv.calculateStore(type, val));
        return "t" + n_var++;
    }

    public String newVar(String name, String type) {

        tv.addRow(new Variable
            (name,
                    n_var,
                    cur_prog,
                tv.calculateStore(type, ""),
                    disp,
            type, "")
        );

        incDisplacement(tv.calculateStore(type, ""));
        n_var++;

        return name;
    }


    public String newVar(String name, String type, String value) {
        
        tv.addRow(new Variable
            (name,
                    n_var,
                    cur_prog,
                tv.calculateStore(type, value),
                    disp,
                type,
            value)
        );

        incDisplacement(tv.calculateStore(type, value));
        n_var++;

        return name;
    }

    public String newVarArray(String name, String type, int length) {
        int store = tv.calculateStore(type, "") * length;
        tv.addRow(new Variable
                (name,
                        n_var,
                        cur_prog,
                        store,
                        disp,
                        type, "")
        );

        incDisplacement(store);
        n_var++;

        return name;
    }

    public void removeGotoElse() {

        for (int i = instruction_list.size() - 1; i >= 0; i--) {

            if (instruction_list.get(i).startsWith("goto")) {
                instruction_list.remove(i);

                break;
            }
        }
    }

    public void closeFile() {

        try {
            for (String s : instruction_list) {
                bw.write(s);
            }
            bw.close();
        } catch (IOException e) {e.printStackTrace();}
    }

    public String newLabel(){return "e" + label_num++;}

    //Procediment que afegeix una instruccio de 3@C
    public void generateCode(String code){
        instruction_list.add(code);}

    public Variable getVar(String id){
        return this.tv.getVar(id);
    }


    public String getTemp_id(){return temp_id;}
    

    public void setTemp_id(String temp_id){this.temp_id = temp_id;}



    public Stack<String> getTrue_stack(){return true_stack;}

    public Stack<String> getFalse_stack(){return false_stack;}


    public Stack<String> getEnd_stack(){return end_stack;}
    

    public Stack<String> getStart_stack(){return start_stack;}


    public void pop(Stack<String> stack){stack.Pop();}


    public String getTop(Stack<String> stack){return stack.Peek();}


    public void push(Stack<String> stack, String label){stack.Push(label);}

    public ArrayList<String> getOperands() {
        return operands;
    }



    public String getCur_prog() {
        return cur_prog;
    }

    public void setCur_prog(String cur_prog){this.cur_prog = cur_prog;}
    

    public void incDisplacement(int val){
        disp += val;}


    public void resetDisplacement(){
        disp = 0;}
    



}
