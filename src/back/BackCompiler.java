//package back;
//
//import back.generator.EnsamblerCode;
//import front.code_generator.ThreeAddressCode;
//
//import java.io.*;
//
//public class BackCompiler {
//    private ThreeAddressCode tac;
//    private EnsamblerCode ens;
//    private Optimizador opt;
//
//    public BackCompiler () {
//        this.tac = // ThreeAddressCode();
//        this.ens = new EnsamblerCode(tac);
//        this.opt = new Optimizador(tac);
//    }
//
//    public void generateBack(String path) throws IOException {
//        writeFile(path + "codi_intermig.txt", tac.viewCode());
//        writeFile(path + "tv.txt", tac.viewtv());
//        writeFile(path + "tp.txt", tac.viewtp());
//        ens.generate();
//        writeFile(path + "codi_ensamblador.X68", ens.getCode());
//        this.opt.optimizar();
//        writeFile(path + "codi_intermig_optimizado.txt", tac.viewCode());
//        ens.generate();
//        writeFile(path + "codi_ensamblador_optimizado.X68", ens.getCode());
//    }
//
//    private static void writeFile(String canonicalFilename, String text)
//            throws IOException
//    {
//        Writer out = new BufferedWriter(new OutputStreamWriter(
//                new FileOutputStream(canonicalFilename), "UTF-8"));
//        try {
//            out.write(text);
//        } finally {
//            out.close();
//        }
//    }
//}
