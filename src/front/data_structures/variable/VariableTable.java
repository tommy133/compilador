package front.data_structures.variable;

import front.data_types.TypeSub;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class VariableTable {

    private int int_store;
    private int str_store;
    private int logic_store;
    private int null_store;

    private static final String TABLE_NAME = "Taula de variables";

    public Writer writer;
    public BufferedReader br;
    private ArrayList<Variable> rows_list = new ArrayList<>();


    public VariableTable() {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("files_output/Tables/Taula_variables.txt"), StandardCharsets.UTF_8));
            setStore();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeFile(Title());
        writeFile(TableHeader());


    }

    private void setStore() throws IOException {
        br = new BufferedReader(new FileReader("src/shared/stores.txt"));
        int_store = Integer.parseInt(br.readLine().split(" ")[2]);
        str_store = Integer.parseInt(br.readLine().split(" ")[2]);
        logic_store = Integer.parseInt(br.readLine().split(" ")[2]);
        null_store = Integer.parseInt(br.readLine().split(" ")[2]);
        br.close();
    }


    public void addRow(Variable var) {

        for (int i = 0; i < rows_list.size(); i++) { //Cerca i Si ja existeix no la torna a afegir
            if (rows_list.get(i).getName().equals(var.getName())) {
                return;
            }
        }

        rows_list.add(var);
    }

    public Variable getVar(String id){
        for(Variable v : this.rows_list){
            if(v.getName().equals(id)){
                return v;
            }
        }
        return null;
    }


    public void closeFile() {
        try {
            for (int i = 0; i < rows_list.size(); i++) {
                writeFile(AddTableRow(rows_list.get(i)));
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeFile(String string) {
        try {
            writer.write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int calculateStore(String type, String s) {
        TypeSub enum_type = TypeSub.valueOf(type.toUpperCase());
        switch (enum_type) {
            case INTEGER:
                return int_store;
            case STRING:
                return str_store * s.length();
            case LOGIC:
                return logic_store;
            case NULL:
                return null_store;
        }
        return -1;
    }

    private String Title() {
        return TABLE_NAME + "\n";
    }

    private String TableHeader() {
        return "\n"
                + "Nombre\t\t\t"
                + "NV\t\t\t"
                + "Subprograma\t\t\t"
                + "Ocupació\t\t\t"
                + "Desplaçament\t\t\t"
                + "Tipus subjacent\t\t\t"
                + "Valor";

    }

    private String AddTableRow(Variable node) {
        return "\n"
                + node.getName() + "\t\t\t"
                + node.getN_var() + "\t\t\t"
                + node.getSubprog() + "\t\t\t"
                + node.getStore() + "\t\t\t"
                + node.getOffset() + "\t\t\t"
                + node.getType() + "\t\t\t"
                + node.getValue()
                + "\n";
    }

    public ArrayList<Variable> getRows_list() {
        return rows_list;
    }
}
