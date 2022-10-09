package front.parser;

import front.scanner.LexicalScanner;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.Symbol;
import java_cup.runtime.SymbolFactory;

import java.io.*;


public class TestParser {


  private static SymbolFactory sf = new ComplexSymbolFactory();

  public static void main(String[] args) {
      String filename = args[0];
      LexicalScanner scanner;
      Parser parser = null;

    try {


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

}
