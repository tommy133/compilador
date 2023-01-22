/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;


import back.Optimizador;
import back.generator.EnsamblerCode;
import back.generator.ThreeAddressCodeSintetic;
import front.error.SintaxErrorException;
import front.parser.Parser;
import front.scanner.LexicalScanner;
import java_cup.runtime.ComplexSymbolFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Compiler {
    private static LexicalScanner scanner = null;
    private static ThreeAddressCodeSintetic tac;
    private static EnsamblerCode ens;
    private static Optimizador opt;

    public static void main(String[] args) {
        try {
            if (args.length < 1) {
                System.err.println("Indica un fitxer d' entrada.");
                System.exit(0);
            }

            clean("Tokens.txt");
            clean("codiIntermitg.txt");
            clean("codi_intermig_optimizado.txt");
            clean("Errors.txt");
            clean("codi_ensamblador.X68");
            clean("codi_ensamblador_optimizado.X68");

            scanner = new LexicalScanner(new FileReader(args[0]));
            Parser parser = new Parser(scanner, new ComplexSymbolFactory());
            parser.parse();
            if(parser.getSintacticErrors()){
                throw new SintaxErrorException() {
                    @Override
                    public int printError(String place, int[] lc, String variableError) {
                        return 0;
                    }
                };
            }
            if (parser != null) {
                System.out.println(">> OK Parsing ["+args[0]+"]");
                generateBack("files_output/");

                System.out.println("COMPILACIÓ COMPLETADA AMB ÈXIT");
            } else {
                System.out.println(">>>>>> Errors detectats");
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Fitxer no trobat!!");
            Logger.getLogger(Compiler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SintaxErrorException ex) {
            System.out.println("Error de compilacion, comprueba errors.txt para mas info.");
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

    private static void generateBack(String path) throws IOException {
        tac = new ThreeAddressCodeSintetic();
        ens = new EnsamblerCode(tac);
        opt = new Optimizador(tac);

        ens.generate();
        writeFile(path + "codi_ensamblador.X68", ens.getCode());
        opt.optimizar();
        writeFile(path + "codi_intermig_optimizado.txt", tac.getInstructionList().toString());
        ens.generate();
        writeFile(path + "codi_ensamblador_optimizado.X68", ens.getCode());
    }

    private static void writeFile(String canonicalFilename, String text)
            throws IOException
    {
        Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(canonicalFilename), "UTF-8"));
        try {
            out.write(text);
        } finally {
            out.close();
        }
    }
}
