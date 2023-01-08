package back;

import back.generator.EnsamblerCode;
import back.generator.ThreeAddressCodeSintetic;

import java.io.*;

public class TestBack {
    private static ThreeAddressCodeSintetic tac;
    private static EnsamblerCode ens;

    public static void generateBack(String path) throws IOException {

        ens.generate();
        writeFile(path + "codi_ensamblador.X68", ens.getCode());
        //this.opt.optimizar();
        //writeFile(path + "codi_intermig_optimizado.txt", tac.viewCode());
        //ens.generate();
        //writeFile(path + "codi_ensamblador_optimizado.X68", ens.getCode());
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

    public static void main(String[] args) {

        try {
           tac = new ThreeAddressCodeSintetic();
           ens = new EnsamblerCode(tac);
           generateBack("files_output/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
