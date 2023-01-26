package front.data_structures.procedure;

import front.data_structures.variable.VariableTable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ProcedureTable {

    private int num_proc;
    public Writer writer;
    private ArrayList<Procedure> row_list ;

    private static final String TABLE_NAME = "Taula de procedimients";
    private static final String FILE_PATH = "files_output/Tables/Taula_procediments.txt";

    public ProcedureTable() {
        try {
            this.num_proc = 0;
            this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE_PATH), StandardCharsets.UTF_8));
            this.row_list = new ArrayList<>();

            writeFile(Title());
            writeFile(TableHeader());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void addRow(Procedure proc) {
        row_list.add(proc);
    }


    public void closeFile() {
        for (Procedure procedure : row_list) {
            writeFile(AddTableRow(procedure));
        }

        try {

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void calculateLocalVarSize(VariableTable tv) {
        for (Procedure procedure : row_list) {

            for (int i = 0; i < tv.getRows_list().size(); i++) {
                if (tv.getRows_list().get(i).getSubprog().equalsIgnoreCase(procedure.getStart_label())) {
                    procedure.setTotal_store(procedure.getTotal_store() + tv.getRows_list().get(i).getStore());
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

    public int getNewNumProc() {
        return ++num_proc;
    }

    public Procedure getProc(String id){
        for(Procedure p : this.row_list){
            if(p.getStart_label().equals(id)){
                return p;
            }
        }
        return null;
    }


    private String Title() {
        return TABLE_NAME + "\n";
    }

    private String TableHeader() {
        return "\n"
                + "NP\t"
                + "Profunditat\t"
                + "Etiqueta inici\t"
                + "Parámetres\t"
                + "Ocupació variables locals\t"
                + "Tipus retorn\t";
    }

    private String AddTableRow(Procedure node) {
        return "\n"
                + node.getNum_proc() + "\t\t"
                + node.getDepth() + "\t\t\t"
                + node.getStart_label() + "\t\t\t"
                + node.getParametros().toString() + "\t\t\t"
                + node.getTotal_store() + "\t\t\t\t\t\t"
                + ((node.getType_return()!=null)? node.getType_return() : "") +"\n"
                + "\n";
    }


}
