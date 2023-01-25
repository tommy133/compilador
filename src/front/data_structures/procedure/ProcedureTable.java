package front.data_structures.procedure;

import front.data_structures.variable.VariableTable;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ProcedureTable {

    private static final String TABLE_NAME = "Taula de procedimients";
    public Writer writer;
    private ArrayList<Procedure> row_list ;
    private int num_proc = 0;


    public ProcedureTable() {
        try {
            this.writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("files_output/Tables/Taula_procediments.txt"), StandardCharsets.UTF_8));
            this.row_list = new ArrayList<>();

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
