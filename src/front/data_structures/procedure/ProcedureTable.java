package front.data_structures.procedure;

import front.data_structures.variable.VariableTable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ProcedureTable {

    private static final String TABLE_NAME = "Taula de procedimients";
    public Writer writer;
    private ArrayList<Procedure> row_list = new ArrayList<>();
    private int num_proc = 0;


    public ProcedureTable() {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("files_output/Tables/Taula_procediments.txt"), StandardCharsets.UTF_8));

            writeFile(Title());
            writeFile(TableHeader());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNewNumProc() {
        return ++num_proc;
    }


    public void addRow(Procedure proc) {
        row_list.add(proc);
    }


    public void closeFile() {
        for (int i = 0; i < row_list.size(); i++) {
            writeFile(AddTableRow(row_list.get(i)));
        }

        try {

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void calculateLocalVarSize(VariableTable tv) {
        for (int i = 0; i < row_list.size(); i++) {

            for (int j = 0; j < tv.getRows_list().size(); j++) {
                if (tv.getRows_list().get(j).getSubprog(). equalsIgnoreCase(row_list.get(i).getStart_label())) {
                    row_list.get(i).setTotal_store(row_list.get(i).getTotal_store() + tv.getRows_list().get(j).getStore());
                }
            }
        }
    }

    private void writeFile(String string) {
        try {
            writer.write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Procedure> getRow_list() {
        return row_list;
    }


    private String Title() {
        return TABLE_NAME + "\n";
    }

    private String TableHeader() {
        return "\n"
                + "NP\t"
                + "Profunditat\t"
                + "Etiqueta inici\t"
                + "Número de parámetres\t"
                + "Ocupació variables locals\t";
    }

    private String AddTableRow(Procedure node) {
        return "\n"
                + node.getNum_proc() + "\t\t"
                + node.getDepth() + "\t\t\t"
                + node.getStart_label() + "\t\t\t\t"
                + node.getN_params() + "\t\t\t\t\t\t\t"
                + node.getTotal_store()
                + "\n";
    }


}
