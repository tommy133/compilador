package front.data_structures.symbol;


import front.data_structures.Stack;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;



public class SymbolTable {

    private static final String TABLE_NAME = "Taula de símbols";
    private Stack<HashMap> stack;
    public Writer writer;
    private ArrayList<Symbol> temp; //llista de variables temporals

    private static final String FILE_PATH = "files_output/Tables/Taula_simbols.txt";

    public SymbolTable() {
        try {
            stack = new Stack<>();
            temp = new ArrayList<>();
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE_PATH), StandardCharsets.UTF_8));
        } catch (IOException ex) {
            Logger.getLogger(SymbolTable.class.getName()).log(Level.SEVERE, null, ex);
        }

        writeFile(Title());
        writeFile(TableHeader());
        incScope();
    }

    public void incScope() {
        stack.Push(new HashMap<>());
    }   //aumentar ámbit

    public void dicScope() {
        stack.Pop();
    }

   //Inserta un element en l ámbit actual
    public void insertElement(Symbol a) {
        stack.Peek().put(a.getId(), a);
        writeFile(AddTableRow(a));
    }

    public Symbol get(String a) {
        if (existInTs(a)) {
            for (int i = stack.Size() - 1; i >= 0; i--) {
                if (stack.Get(i).containsKey(a)) {
                    return (Symbol) stack.Get(i).get(a);
                }
            }
        }

        if (temp != null) {

            for (int i = 0; i < temp.size(); i++) {

                if (temp.get(i).getId().equals(a)) {
                    return temp.get(i);
                }
            }
        }
        return null;
    }

    //Comprova si esta el node a la taula de simbols
    public boolean existInTs(String a) {

        for (int i = stack.Size() - 1; i >= 0; i--) {
            if (stack.Get(i).containsKey(a)) {
                return true;
            }
        }
        return existInTemp(a);
    }

    public void addParams(Symbol a) {
        temp.add(a);
    }


    public ArrayList<Symbol> getParams() {
        Collections.reverse(temp);
        return temp;
    }

    public void emptyParams() {
        temp.clear();
    }


    public boolean existInTemp(String a) {

        if (temp == null) {
            return false;
        }

        for (Symbol temp : temp) {
            if (temp.getId().equals(a)) {
                return true;
            }
        }
        return false;
    }

    private void writeFile(String string) {
        try {
            writer.write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeFile() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String Title() {
        return TABLE_NAME + "\n";
    }

    private String TableHeader() {
        return """

                Identificador\tÀmbit\tTipus\t\tTipus subjacent\t\tArguments\t
                """;
    }

    private String AddTableRow(Symbol node) {
        if (node.getArgs()!=null) Collections.reverse(node.getArgs());
        return  "\n"
                +  node.getId() + "\t\t"
                + stack.Size() + "\t\t"
                + node.getType() + "\t\t"
                + node.getSubtype() + "\t"
                + ((node.getArgs()!=null)? node.getArgs().toString() : "") +"\n"
                + "\n";

    }
}
