package front.data_structures.variable;

import front.data_types.TypeSub;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class VariableTable {

    private static final int INT_STORE = 4;
    private static final int STR_STORE = 4;
    private static final int LOGIC_STORE = 4;
    private static final int NULL_STORE = 1;

    private static final String TABLE_NAME = "Taula de variables";  //TODO Victor: Generar una tabla decente que se vea bien la correspondencia, idem per tp i tsimbols

    public Writer writer;
    private ArrayList<Variable> rows_list = new ArrayList<>();


    public VariableTable() {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("files_output/Tables/Taula_variables.txt"), StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writeFile(Title());
        writeFile(TableHeader());


    }


    public void addRow(Variable var) {

        for (int i = 0; i < rows_list.size(); i++) { //Cerca i Si ja existeix no la torna a afegir
            if (rows_list.get(i).getName().equals(var.getName())) {
                return;
            }
        }

        rows_list.add(var);
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
                return INT_STORE;
            case STRING:
                return STR_STORE * s.length();
            case LOGIC:
                return LOGIC_STORE;
            case NULL:
                return NULL_STORE;
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
