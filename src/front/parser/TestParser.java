package front.parser;

import front.scanner.LexicalScanner;
import java_cup.Main;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;
import java_cup.runtime.SymbolFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;


public class TestParser {


  private static SymbolFactory sf = new ComplexSymbolFactory();

  public static void main(String[] args) {
      String filename = args[0];
      LexicalScanner scanner;
      Parser parser = null;

      if(args.length == 1){
          System.out.println("Hola");
          String[] values = {"-parser", "Parser", "src/front/parser/Parser.cup"};
          try {
              Main.main(values);
          } catch (Exception e) {
              e.printStackTrace();
          }
          System.exit(0);
      }

    try {
        clean("Tokens.txt");
        clean("codiIntermitg.txt");

        System.out.println(">>>>>>>>>>>> Parsing ["+filename+"]");
        scanner = new LexicalScanner(new FileReader(filename));
        parser = new Parser(scanner, sf);
        parser.parse();
    } catch (Exception e) {
        e.printStackTrace();
    }

      if (parser != null) {
        System.out.println(">> OK Parsing ["+filename+"]");
    } else {
        System.out.println(">>>>>> Errors detectats");
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
