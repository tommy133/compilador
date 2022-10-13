package front.scanner;

import java.io.*;
import java_cup.runtime.*;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;
import java.util.logging.Level;
import java.util.logging.Logger;
import front.parser.ParserSym;

%%
%cup
%public           
%class LexicalScanner 
%line 
%column
%eofval{
  return symbol(ParserSym.EOF);
%eofval}

id         = [A-Za-z_][A-Za-z_0-9]*
cadena     = \"(\\.|[^\"])*\"
enter       = [0-9]+
linia      = ['\r' | '\n' | '\r\n']
space         = [' ' | '\t']+

%{
  Writer bw;
      protected int n_linia = 0;

      private Symbol symbol(int type) {
        return new ComplexSymbol(ParserSym.terminalNames[type], type);
      }

      /*Creació simbol amb sobrecarrega tipus, tipus/valor*/

      private Symbol symbol(int type, Object value) {
        return new ComplexSymbol(ParserSym.terminalNames[type], type, value);
      }

      public int getLine(){
        return yyline + 1;
      }

      public int getColumn(){
        return yycolumn + 1;
      }

      public int [] getLineColumn() {
          int [] line_col = new int[2];

          line_col[0] = yyline + 1;
          line_col[1] = yycolumn + 1;

          return line_col;
      }

      public void writeToken(String token) {
        try {
          bw  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("files_output/Tokens.txt", true), "utf-8"));
          bw.write(token+"\n");
          bw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LexicalScanner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LexicalScanner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LexicalScanner.class.getName()).log(Level.SEVERE, null, ex);
        }
      }

%}

%%
":"       { writeToken(this.yytext()); return symbol(ParserSym.colons, this.yytext()); }
":="      { writeToken(this.yytext()); return symbol(ParserSym.assign, this.yytext()); }
";"       { writeToken(this.yytext()); return symbol(ParserSym.scolon, this.yytext());}

/*Operadors aritmetics*/
"+"       { writeToken(this.yytext()); return symbol(ParserSym.add, this.yytext()); }
"-"     { writeToken(this.yytext()); return symbol(ParserSym.sub, this.yytext()); }
"*"     { writeToken(this.yytext()); return symbol(ParserSym.mult, this.yytext()); }
"/"     { writeToken(this.yytext()); return symbol(ParserSym.div, this.yytext()); }
"%"     { writeToken(this.yytext()); return symbol(ParserSym.mod, this.yytext()); }

/*Operadors lògics*/
"&&"      { writeToken(this.yytext()); return symbol(ParserSym.op_and, this.yytext()); }
"||"    { writeToken(this.yytext()); return symbol(ParserSym.op_or, this.yytext()); }

/*Operadors relacionals*/
"<"       { writeToken(this.yytext()); return symbol(ParserSym.lt, this.yytext()); }
">="      { writeToken(this.yytext()); return symbol(ParserSym.gte, this.yytext()); }
"="       { writeToken(this.yytext()); return symbol(ParserSym.equals, this.yytext()); }
">"       { writeToken(this.yytext()); return symbol(ParserSym.gt, this.yytext()); }
"<="      { writeToken(this.yytext()); return symbol(ParserSym.lte, this.yytext()); }
"!="      { writeToken(this.yytext()); return symbol(ParserSym.noteq, this.yytext()); }

/*Tokens de separació*/

","       { writeToken(this.yytext()); return symbol(ParserSym.comma, this.yytext()); }
"("       { writeToken(this.yytext()); return symbol(ParserSym.lparen, this.yytext()); }
")"       { writeToken(this.yytext()); return symbol(ParserSym.rparen, this.yytext()); }

/*PARAULES RESERVADES*/

"prog"  { writeToken(this.yytext()); return symbol(ParserSym.prog, this.yytext()); }
"end"       { writeToken(this.yytext()); return symbol(ParserSym.end, this.yytext()); }
"func"     { writeToken(this.yytext()); return symbol(ParserSym.func, this.yytext());   }
"method"    { writeToken(this.yytext()); return symbol(ParserSym.method, this.yytext());   }
"integer"     { writeToken(this.yytext()); return symbol(ParserSym.integer, this.yytext());    }
"string"    { writeToken(this.yytext()); return symbol(ParserSym.string, this.yytext());   }
"logic"     { writeToken(this.yytext()); return symbol(ParserSym.logic, this.yytext());    }
"true"      { writeToken(this.yytext()); return symbol(ParserSym.True, this.yytext()); }
"false"      { writeToken(this.yytext()); return symbol(ParserSym.False, this.yytext()); }
"if"       { writeToken(this.yytext()); return symbol(ParserSym.If, this.yytext());       }
"endif"     { writeToken(this.yytext()); return symbol(ParserSym.endif, this.yytext()); }
"while"    { writeToken(this.yytext()); return symbol(ParserSym.While, this.yytext());   }
"repeat"    { writeToken(this.yytext()); return symbol(ParserSym.Repeat, this.yytext());   }
"until"   { writeToken(this.yytext()); return symbol(ParserSym.Until, this.yytext()); }
"endloop"   { writeToken(this.yytext()); return symbol(ParserSym.endloop, this.yytext()); }
"then"   { writeToken(this.yytext()); return symbol(ParserSym.then, this.yytext()); }
"loop"       { writeToken(this.yytext()); return symbol(ParserSym.loop, this.yytext()); }
"const"  { writeToken(this.yytext()); return symbol(ParserSym.Const, this.yytext()); }
"else"     { writeToken(this.yytext()); return symbol(ParserSym.Else, this.yytext()); }
"return"   { writeToken(this.yytext()); return symbol(ParserSym.Return, this.yytext()); }
"print"   { writeToken(this.yytext()); return symbol(ParserSym.print, this.yytext()); }
"read" { writeToken(this.yytext()); return symbol(ParserSym.read, this.yytext()); }

{id}            { writeToken(this.yytext()); return symbol(ParserSym.val_id, this.yytext()); }
{enter}   { writeToken(this.yytext()); return symbol(ParserSym.val_int, this.yytext()); }
{cadena}  { writeToken(this.yytext()); return symbol(ParserSym.val_str, this.yytext()); }

{space}            { } /*Ignorar*/
{linia}           { n_linia++; }

[^]              {

    Writer w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("files_output/Errors.txt", true),  "utf-8"));
    w.write("Error en l'anàlisi lèxic:" + "[" + getLine() + ":" + getColumn() + "]" + " Símbol desconegut: "+"'"+this.yytext() + "'" + ".\n");
    w.close();

}


