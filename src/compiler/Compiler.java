/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;


import front.scanner.LexicalScanner;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Compiler {

    public static void main(String[] args) {

        LexicalScanner scanner = null;


        try {
            if (args.length < 1) {
                System.err.println("Indica un fitxer d' entrada.");
                System.exit(0);
            }

            clean("Tokens.txt");

            scanner = new LexicalScanner(new FileReader(args[0]));
            //Parser parser = new Parser(scanner, new ComplexSymbolFactory());
            //parser.parse();

            System.out.println("COMPILACIÓ COMPLETADA AMB ÈXIT");

        } catch (FileNotFoundException ex) {
            System.out.println("Fitxer no trobat!!");
            Logger.getLogger(Compiler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Compiler.class.getName()).log(Level.SEVERE, null, ex);
            int[] line_column = new int[2];
            line_column[0] = scanner.getLine();
            line_column[1] = scanner.getColumn();

        }
    }

    //Neteja els fitxers de sortida
    private static void clean(String file) {
        Writer writer;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("files_output/" + file), StandardCharsets.UTF_8));
            writer.write("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
