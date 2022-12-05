//package back;
//
//
//import org.uib.GIN.Compiladors.smartlang.data_structures.Instruccion;
//import org.uib.GIN.Compiladors.smartlang.data_structures.Instruccion.Operacion;
//import org.uib.GIN.Compiladors.smartlang.data_structures.Variable;
//import org.uib.GIN.Compiladors.smartlang.front.Simbol;
//
//import java.util.ArrayList;
//
//
//public class Optimizador {
//    private ArrayList<Instruccion> instrucciones;
//    private Instruccion act, sig; //Instruccion actual y siguiente
//    private ThreeAdressCode tac;
//    public Optimizador(ThreeAdressCode tac) {
//        this.tac = tac;
//        this.instrucciones = tac.getCode();
//    }
//
//    public void optimizar() {
//
//        int despues,antes;
//        do {
//            antes = instrucciones.size();
//            conjunto();
//            despues = instrucciones.size();
//        } while (antes != despues);
//
//        tac.setCode(instrucciones);
//    }
//
//    private void conjunto() {
//        int i = 0;
//        while (i != instrucciones.size() - 1) {
//
//            act = instrucciones.get(i);
//            sig = instrucciones.get(i + 1);
//
//            if (RbrancamentsAdjacents(i, act, sig)) {}
//            else if (RbracamentBrancament(i, act, sig)){}
//            else if (RasignacionDiferides(i, act, sig)){}
//            else if (RcodiInacessible(i, act, sig)){i++;}
//            else if (ROperacioConstant(i,act)){}
//            else if (Rreducciodeforca(i,act)){}
//            else {i++;}
//        }
//    }
//
//    private boolean RcodiInacessible(int i, Instruccion act, Instruccion sig){
//        int aux;
//        if (act.getOperacion() == Operacion.GOTO && !act.getDestino().equals("run")) {
//            aux = i + 1;
//            boolean salir = true;
//            while (salir) {
//                Instruccion ini = instrucciones.get(aux);
//               if(ini.getOperacion() == Operacion.SKIP){
//                   int j = 0;
//                   while(salir && j < instrucciones.size()-1){
//
//                       Instruccion ins = instrucciones.get(j);
//                       if(!ins.getOperacion().equals(Operacion.SKIP) && ins.getDestino().equals(ini.getDestino()) ){
//                            salir = false;
//                       }
//                       j++;
//                   }
//               }
//               if(salir){
//                   instrucciones.remove(aux);
//               }
//
//            }
//            return true;
//        }
//        return false;
//    }
//
//    private boolean RasignacionDiferides(int i, Instruccion act, Instruccion sig){
//
//        if (act.getOperacion() == Operacion.ASIGNA && sig.getOperacion() == Operacion.ASIGNA) {
//
//            if (act.getDestino().equals(sig.getDestino())) {
//                instrucciones.remove(i);
//                return true;
//            }
//
//            if (act.getDestino().equals(sig.getOperador1())) {
//                tac.borrarVariable(act.getDestino());
//                act.modInstruccion(act.getOperacion(), act.getOperador1(), act.getOperador2(), sig.getDestino());
//                instrucciones.remove(i + 1);
//                return true;
//            }
//
//            Instruccion ant = instrucciones.get(i-1);
//            if (!ant.esArtim()) {
//                Instruccion ter = instrucciones.get(i + 2);
//                if (ter.esArtim() || ter.esCondicional()) {
//                    boolean retorno = false;
//                    if (esTemporal(sig.getDestino()) && ter.getOperador2().equals(sig.getDestino())) {
//                        tac.borrarVariable(sig.getDestino());
//                        instrucciones.remove(i + 1);
//                        ter.modInstruccion(ter.getOperacion(), ter.getOperador1(), sig.getOperador1(), ter.getDestino());
//                        retorno = true;
//                    }
//                    if (esTemporal(act.getDestino()) && ter.getOperador1().equals(act.getDestino())) {
//                        tac.borrarVariable(act.getDestino());
//                        instrucciones.remove(i);
//                        ter.modInstruccion(ter.getOperacion(), act.getOperador1(), ter.getOperador2(), ter.getDestino());
//                        retorno = true;
//                    }
//                    if(retorno) {
//                        return true;
//                    }
//                }
//            }
//        }
//
//        if (act.getOperacion() == Operacion.ASIGNA && (sig.esArtim() || sig.esCondicional() || sig.esParam())) {
//
//            if(esTemporal(act.getDestino())){
//                if (act.getDestino().equals(sig.getOperador1())) {
//                    tac.borrarVariable(act.getDestino());
//                    sig.modInstruccion(sig.getOperacion(), act.getOperador1(), sig.getOperador2(), sig.getDestino());
//                    instrucciones.remove(i);
//                    return true;
//                }
//
//                 if (act.getDestino().equals(sig.getOperador2())) {
//                     tac.borrarVariable(act.getDestino());
//                     sig.modInstruccion(sig.getOperacion(), sig.getOperador1(), act.getOperador1(), sig.getDestino());
//                     instrucciones.remove(i);
//                     return true;
//                 }
//                if (act.getDestino().equals(sig.getDestino())) {
//                    tac.borrarVariable(act.getDestino());
//                    sig.modInstruccion(sig.getOperacion(), sig.getOperador1(), sig.getOperador2(), act.getOperador1());
//                    instrucciones.remove(i);
//                    return true;
//                }
//
//            }
//        }
//
//
//        return false;
//    }
//
//    private boolean RbrancamentsAdjacents(int i, Instruccion act, Instruccion sig) {
//
//        if (act.esCondicional() && sig.getOperacion() == Operacion.GOTO) {
//            if (act.getOperador1() != null && act.getOperador2() != null || act.getOperacion()==Operacion.IF) {
//                String op1 = act.getOperador1();
//                String op2 = act.getOperador2();
//                String dest = sig.getDestino();
//
//                switch (act.getOperacion()) {
//                    case IF:
//                        act.modInstruccion(Operacion.IFDIFERENTE, op1, "-1", dest);
//                        break;
//
//                    case IFDIFERENTE:
//                        act.modInstruccion(Operacion.IFIGUAL, op1, op2, dest);
//                        break;
//
//                    case IFIGUAL:
//                        act.modInstruccion(Operacion.IFDIFERENTE, op1, op2, dest);
//                        break;
//
//                    case IFMAYOR:
//                        act.modInstruccion(Operacion.IFMENORIGUAL, op1, op2, dest);
//                        break;
//
//                    case IFMAYORIGUAL:
//                        act.modInstruccion(Operacion.IFMENOR, op1, op2, dest);
//                        break;
//
//                    case IFMENOR:
//                        act.modInstruccion(Operacion.IFMAYORIGUAL, op1, op2, dest);
//                        break;
//
//                    case IFMENORIGUAL:
//                        act.modInstruccion(Operacion.IFMAYOR, op1, op2, dest);
//                        break;
//                }
//                instrucciones.remove(i + 1);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private boolean RbracamentBrancament(int i, Instruccion act, Instruccion sig) {
//        if (sig.getOperacion() == Operacion.GOTO && !sig.getDestino().equals("run") && act.getOperacion() == Operacion.SKIP) {
//            String[] eti = {act.getDestino(), sig.getDestino()};
//            instrucciones.remove(i);
//            for (int j = 0; j < instrucciones.size() - 1; j++) {
//                Instruccion ins = instrucciones.get(j);
//                if (ins.getDestino().equals(eti[0])) {
//                    ins.modInstruccion(ins.getOperacion(), ins.getOperador1(), ins.getOperador2(), eti[1]);
//                }
//            }
//            return true;
//        }
//        return false;
//    }
//
//    private boolean ROperacioConstant(int i, Instruccion act) {
//        Variable op1 = tac.getVar(act.getOperador1());
//        if (op1 == null) {
//            Variable op2 = tac.getVar(act.getOperador2());
//            if (op2 == null) {
//                switch (act.getOperacion()) {
//                    case SUMA:
//                        Variable dest = tac.getVar(act.getDestino());
//                        if (dest.getTipo() == Simbol.Tipus.Cadena) {
//                            String aux = act.getOperador1() + act.getOperador2();
//                            act.modInstruccion(Operacion.ASIGNA, aux, null, act.getDestino());
//                        } else {
//                            int aux = Integer.valueOf(act.getOperador1()) + Integer.valueOf(act.getOperador2());
//                            act.modInstruccion(Operacion.ASIGNA, String.valueOf(aux), null, act.getDestino());
//                        }
//                        return true;
//                    case RESTA:
//                        int aux = Integer.valueOf(act.getOperador1()) - Integer.valueOf(act.getOperador2());
//                        act.modInstruccion(Operacion.ASIGNA, String.valueOf(aux), null, act.getDestino());
//                        return true;
//                    case DIVISION:
//                        aux = Integer.valueOf(act.getOperador1()) / Integer.valueOf(act.getOperador2());
//                        act.modInstruccion(Operacion.ASIGNA, String.valueOf(aux), null, act.getDestino());
//                        return true;
//                    case MULTIPLICACION:
//                        aux = Integer.valueOf(act.getOperador1()) * Integer.valueOf(act.getOperador2());
//                        act.modInstruccion(Operacion.ASIGNA, String.valueOf(aux), null, act.getDestino());
//                        return true;
//                    case AND:
//                        if (act.getOperador1().equals("-1") && act.getOperador2().equals("-1")) {
//                            act.modInstruccion(Operacion.ASIGNA, "-1", null, act.getDestino());
//                        } else {
//                            act.modInstruccion(Operacion.ASIGNA, "0", null, act.getDestino());
//                        }
//                        return true;
//                    case OR:
//                        if (act.getOperador1().equals("0") && act.getOperador2().equals("0")) {
//                            act.modInstruccion(Operacion.ASIGNA, "0", null, act.getDestino());
//                        } else {
//                            act.modInstruccion(Operacion.ASIGNA, "-1", null, act.getDestino());
//                        }
//                        return true;
//                    case IF:
//                    case IFIGUAL:
//                        if (act.getOperador1().equals(act.getOperador2())) {
//                            act.modInstruccion(Operacion.GOTO, null, null, act.getDestino());
//                        } else {
//                            instrucciones.remove(i);
//                        }
//                        return true;
//                    case IFMAYOR:
//                        if (Integer.valueOf(act.getOperador1()) > Integer.valueOf(act.getOperador2())) {
//                            act.modInstruccion(Operacion.GOTO, null, null, act.getDestino());
//                        } else {
//                            instrucciones.remove(i);
//                        }
//                        return true;
//                    case IFMENOR:
//                        if (Integer.valueOf(act.getOperador1()) < Integer.valueOf(act.getOperador2())) {
//                            act.modInstruccion(Operacion.GOTO, null, null, act.getDestino());
//                        } else {
//                            instrucciones.remove(i);
//                        }
//                        return true;
//                    case IFMAYORIGUAL:
//                        if (Integer.valueOf(act.getOperador1()) >= Integer.valueOf(act.getOperador2())) {
//                            act.modInstruccion(Operacion.GOTO, null, null, act.getDestino());
//                        } else {
//                            instrucciones.remove(i);
//                        }
//                        return true;
//                    case IFMENORIGUAL:
//                        if (Integer.valueOf(act.getOperador1()) <= Integer.valueOf(act.getOperador2())) {
//                            act.modInstruccion(Operacion.GOTO, null, null, act.getDestino());
//                        } else {
//                            instrucciones.remove(i);
//                        }
//                        return true;
//                    case IFDIFERENTE:
//                        if (!act.getOperador1().equals(act.getOperador2())) {
//                            act.modInstruccion(Operacion.GOTO, null, null, act.getDestino());
//                        } else {
//                            instrucciones.remove(i);
//                        }
//                        return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    private boolean Rreducciodeforca(int i, Instruccion act) {
//
//        if(act.getOperacion().equals(Operacion.MULTIPLICACION)){
//            Variable op1 = tac.getVar(act.getOperador1());
//            if (op1 == null) {
//                int x = Integer.valueOf(act.getOperador1());
//                if (x != 0 && ((x & (x - 1)) == 0)) {
//                    x = x / 2;
//                    act.modInstruccion(Operacion.DESPLAZAR_BITS,String.valueOf(x),act.getOperador2(),act.getDestino());
//                    return true;
//                }
//            }
//            Variable op2 = tac.getVar(act.getOperador2());
//            if (op2 == null) {
//                int x = Integer.valueOf(act.getOperador2());
//                if (x != 0 && ((x & (x - 1)) == 0)) {
//                    x = x / 2;
//                    act.modInstruccion(Operacion.DESPLAZAR_BITS,String.valueOf(x),act.getOperador1(),act.getDestino());
//                    return true;
//                }
//            }
//        } else if(act.getOperacion().equals(Operacion.DIVISION)){
//            Variable op2 = tac.getVar(act.getOperador2());
//            if (op2 == null) {
//                int x = Integer.valueOf(act.getOperador2());
//                if (x != 0 && ((x & (x - 1)) == 0)) {
//                    x = -(x / 2);
//                    act.modInstruccion(Operacion.DESPLAZAR_BITS,String.valueOf(x),act.getOperador1(),act.getDestino());
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    private boolean esTemporal(String variable){
//        if(variable.startsWith("t")){
//            try {
//                String aux = variable.substring(1);
//                Integer.parseInt(aux);
//                return true;
//            }catch(NumberFormatException e){
//                return false;
//            }
//        }else{
//            return false;
//        }
//    }
//}
//
