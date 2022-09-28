package front.scanner;

import front.parser.ParserSym;
import java_cup.runtime.Symbol;

import java.io.FileReader;

public class TestLexer {

  public static void main(String[] args) {

      LexicalScanner scanner;

      if (args.length < 1) {
          System.err.println("Indica un fitxer d' entrada.");
          System.exit(0);
      }

      try {
          String filename = args[0];
          scanner = new LexicalScanner(new FileReader(filename));
        System.out.println("Lexing ["+filename+"]");
                
         Symbol s;
        do {
          s = scanner.next_token();
          System.out.println("line:" + scanner.getLine() + " col:" + scanner.getColumn() + "--" + scanner.yytext() + "--");
          System.out.println("token: "+s);

        } while (s.sym != ParserSym.EOF);
        
        System.out.println("No errors.")    ;
      }
      catch (Exception e) {
        e.printStackTrace(System.out);
        System.exit(1);
      }
  }
}
