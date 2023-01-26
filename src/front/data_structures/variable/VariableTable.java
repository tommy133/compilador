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
    private final ArrayList<Variable> rows_list = new ArrayList<>();

    private static final String TABLE_FILE_PATH = "files_output/Tables/Taula_variables.txt";
    private static final String STORES_FILE_PATH = "src/shared/stores.txt";

    public VariableTable() {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(TABLE_FILE_PATH), StandardCharsets.UTF_8));
            setStore();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writeFile(Title());
        writeFile(TableHeader());


    }

    private void setStore() throws IOException {
        br = new BufferedReader(new FileReader(STORES_FILE_PATH));
        int_store = Integer.parseInt(br.readLine().split(" ")[2]);
        str_store = Integer.parseInt(br.readLine().split(" ")[2]);
        logic_store = Integer.parseInt(br.readLine().split(" ")[2]);
        null_store = Integer.parseInt(br.readLine().split(" ")[2]);
        br.close();
    }


    public void addRow(Variable var) {

        for (Variable variable : rows_list) { //Cerca i Si ja existeix no la torna a afegir
            if (variable.getName().equals(var.getName())) {
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

    private void writeFile(String string) {
        try {
            writer.write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int calculateStore(String type, String s) {
        TypeSub enum_type = TypeSub.valueOf(type.toUpperCase());
        return switch (enum_type) {
            case INTEGER -> int_store;
            case STRING -> str_store * s.length();
            case LOGIC -> logic_store;
            case NULL -> null_store;
        };
    }

    public void closeFile() {
        try {
            for (Variable variable : rows_list) {
                writeFile(AddTableRow(variable));
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
