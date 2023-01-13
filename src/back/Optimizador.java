package back;


import back.data_structures.Operation;
import back.data_structures.instructions.Instruction;
import back.data_structures.instructions.InstructionList;
import back.generator.ThreeAddressCodeSintetic;
import front.data_structures.variable.Variable;
import front.data_types.TypeSub;


public class Optimizador {
    private InstructionList instrucciones;
    private Instruction act, sig; //Instruccion actual y siguiente
    private ThreeAddressCodeSintetic tac;
    public Optimizador(ThreeAddressCodeSintetic tac) {
        this.tac = tac;
        this.instrucciones = tac.getInstructionList();
    }

    public void optimizar() {

        int despues,antes;
        do {
            antes = instrucciones.getSize();
            conjunto();
            despues = instrucciones.getSize();
        } while (antes != despues);

        tac.updateInstructionList(instrucciones);
    }

    private void conjunto() {
        int i = 0;
        while (i != instrucciones.getSize() - 1) {

            act = instrucciones.getInstruction(i);
            sig = instrucciones.getInstruction(i + 1);

            if (RbrancamentsAdjacents(i, act, sig)) {}
            else if (RbracamentBrancament(i, act, sig)){}
            else if (RasignacionDiferides(i, act, sig)){}
            else if (RcodiInacessible(i, act, sig)){i++;}
            else if (ROperacioConstant(i,act)){}
            else if (Rreducciodeforca(i,act)){}
            else {i++;}
        }
    }

    private boolean RcodiInacessible(int i, Instruction act, Instruction sig){
        int aux;
        if (act.getOperation() == Operation.GOTO && !act.getDestiny().equals("main")) {
            aux = i + 1;
            boolean salir = true;
            while (salir) {
                Instruction ini = instrucciones.getInstruction(aux);
               if(ini.getOperation() == Operation.SKIP){
                   int j = 0;
                   while(salir && j < instrucciones.getSize()-1){

                       Instruction ins = instrucciones.getInstruction(j);
                       if(!ins.getOperation().equals(Operation.SKIP) && ins.getDestiny().equals(ini.getDestiny()) ){
                            salir = false;
                       }
                       j++;
                   }
               }
               if(salir){
                   instrucciones.deleteInst(aux);
               }

            }
            return true;
        }
        return false;
    }

    private boolean RasignacionDiferides(int i, Instruction act, Instruction sig){

        if (act.getOperation() == Operation.ASIGNA && sig.getOperation() == Operation.ASIGNA) {

            if (act.getDestiny().equals(sig.getDestiny())) {
                instrucciones.deleteInst(i);
                return true;
            }

            if (act.getDestiny().equals(sig.getOperand1())) {
                tac.deleteVar(act.getDestiny());
                act.modInstruccion(act.getOperation(), act.getOperand1(), act.getOperand2(), sig.getDestiny());
                instrucciones.deleteInst(i + 1);
                return true;
            }

            Instruction ant = instrucciones.getInstruction(i-1);
            if (!ant.esArtim()) {
                Instruction ter = instrucciones.getInstruction(i + 2);
                if (ter.esArtim() || ter.esCondicional()) {
                    boolean retorno = false;
                    if (esTemporal(sig.getDestiny()) && ter.getOperand2().equals(sig.getDestiny())) {
                        tac.deleteVar(sig.getDestiny());
                        instrucciones.deleteInst(i + 1);
                        ter.modInstruccion(ter.getOperation(), ter.getOperand1(), sig.getOperand1(), ter.getDestiny());
                        retorno = true;
                    }
                    if (esTemporal(act.getDestiny()) && ter.getOperand1().equals(act.getDestiny())) {
                        tac.deleteVar(act.getDestiny());
                        instrucciones.deleteInst(i);
                        ter.modInstruccion(ter.getOperation(), act.getOperand1(), ter.getOperand2(), ter.getDestiny());
                        retorno = true;
                    }
                    if(retorno) {
                        return true;
                    }
                }
            }
        }

        if (act.getOperation() == Operation.ASIGNA && (sig.esArtim() || sig.esCondicional() || sig.esParam())) {

            if(esTemporal(act.getDestiny())){
                if (act.getDestiny().equals(sig.getOperand1())) {
                    tac.deleteVar(act.getDestiny());
                    sig.modInstruccion(sig.getOperation(), act.getOperand1(), sig.getOperand2(), sig.getDestiny());
                    instrucciones.deleteInst(i);
                    return true;
                }

                 if (act.getDestiny().equals(sig.getOperand2())) {
                     tac.deleteVar(act.getDestiny());
                     sig.modInstruccion(sig.getOperation(), sig.getOperand1(), act.getOperand1(), sig.getDestiny());
                     instrucciones.deleteInst(i);
                     return true;
                 }
                if (act.getDestiny().equals(sig.getDestiny())) {
                    tac.deleteVar(act.getDestiny());
                    sig.modInstruccion(sig.getOperation(), sig.getOperand1(), sig.getOperand2(), act.getOperand1());
                    instrucciones.deleteInst(i);
                    return true;
                }

            }
        }


        return false;
    }

    private boolean RbrancamentsAdjacents(int i, Instruction act, Instruction sig) {

        if (act.esCondicional() && sig.getOperation() == Operation.GOTO) {
            if (act.getOperand1() != null && act.getOperand2() != null || act.getOperation()==Operation.IF) {
                String op1 = act.getOperand1();
                String op2 = act.getOperand2();
                String dest = sig.getDestiny();

                switch (act.getOperation()) {
                    case IF:
                        act.modInstruccion(Operation.IFDIFERENTE, op1, "-1", dest);
                        break;

                    case IFDIFERENTE:
                        act.modInstruccion(Operation.IFIGUAL, op1, op2, dest);
                        break;

                    case IFIGUAL:
                        act.modInstruccion(Operation.IFDIFERENTE, op1, op2, dest);
                        break;

                    case IFMAYOR:
                        act.modInstruccion(Operation.IFMENORIGUAL, op1, op2, dest);
                        break;

                    case IFMAYORIGUAL:
                        act.modInstruccion(Operation.IFMENOR, op1, op2, dest);
                        break;

                    case IFMENOR:
                        act.modInstruccion(Operation.IFMAYORIGUAL, op1, op2, dest);
                        break;

                    case IFMENORIGUAL:
                        act.modInstruccion(Operation.IFMAYOR, op1, op2, dest);
                        break;
                }
                instrucciones.deleteInst(i + 1);
                return true;
            }
        }
        return false;
    }

    private boolean RbracamentBrancament(int i, Instruction act, Instruction sig) {
        if (sig.getOperation() == Operation.GOTO && !sig.getDestiny().equals("run") && act.getOperation() == Operation.SKIP) {
            String[] eti = {act.getDestiny(), sig.getDestiny()};
            instrucciones.deleteInst(i);
            for (int j = 0; j < instrucciones.getSize() - 1; j++) {
                Instruction ins = instrucciones.getInstruction(j);
                if (ins.getDestiny().equals(eti[0])) {
                    ins.modInstruccion(ins.getOperation(), ins.getOperand1(), ins.getOperand2(), eti[1]);
                }
            }
            return true;
        }
        return false;
    }

    private boolean ROperacioConstant(int i, Instruction act) {
        Variable op1 = tac.getVar(act.getOperand1());
        if (op1 == null) {
            Variable op2 = tac.getVar(act.getOperand2());
            if (op2 == null) {
                switch (act.getOperation()) {
                    case SUMA:
                        Variable dest = tac.getVar(act.getDestiny());
                        if (dest.getType().equalsIgnoreCase(String.valueOf(TypeSub.STRING))) {
                            String aux = act.getOperand1() + act.getOperand2();
                            act.modInstruccion(Operation.ASIGNA, aux, null, act.getDestiny());
                        } else {
                            int aux = Integer.valueOf(act.getOperand1()) + Integer.valueOf(act.getOperand2());
                            act.modInstruccion(Operation.ASIGNA, String.valueOf(aux), null, act.getDestiny());
                        }
                        return true;
                    case RESTA:
                        int aux = Integer.valueOf(act.getOperand1()) - Integer.valueOf(act.getOperand2());
                        act.modInstruccion(Operation.ASIGNA, String.valueOf(aux), null, act.getDestiny());
                        return true;
                    case DIVISION:
                        aux = Integer.valueOf(act.getOperand1()) / Integer.valueOf(act.getOperand2());
                        act.modInstruccion(Operation.ASIGNA, String.valueOf(aux), null, act.getDestiny());
                        return true;
                    case MULTIPLICACION:
                        aux = Integer.valueOf(act.getOperand1()) * Integer.valueOf(act.getOperand2());
                        act.modInstruccion(Operation.ASIGNA, String.valueOf(aux), null, act.getDestiny());
                        return true;
                    case AND:
                        if (act.getOperand1().equals("-1") && act.getOperand2().equals("-1")) {
                            act.modInstruccion(Operation.ASIGNA, "-1", null, act.getDestiny());
                        } else {
                            act.modInstruccion(Operation.ASIGNA, "0", null, act.getDestiny());
                        }
                        return true;
                    case OR:
                        if (act.getOperand1().equals("0") && act.getOperand2().equals("0")) {
                            act.modInstruccion(Operation.ASIGNA, "0", null, act.getDestiny());
                        } else {
                            act.modInstruccion(Operation.ASIGNA, "-1", null, act.getDestiny());
                        }
                        return true;
                    case IF:
                    case IFIGUAL:
                        if (act.getOperand1().equals(act.getOperand2())) {
                            act.modInstruccion(Operation.GOTO, null, null, act.getDestiny());
                        } else {
                            instrucciones.deleteInst(i);
                        }
                        return true;
                    case IFMAYOR:
                        if (Integer.valueOf(act.getOperand1()) > Integer.valueOf(act.getOperand2())) {
                            act.modInstruccion(Operation.GOTO, null, null, act.getDestiny());
                        } else {
                            instrucciones.deleteInst(i);
                        }
                        return true;
                    case IFMENOR:
                        if (Integer.valueOf(act.getOperand1()) < Integer.valueOf(act.getOperand2())) {
                            act.modInstruccion(Operation.GOTO, null, null, act.getDestiny());
                        } else {
                            instrucciones.deleteInst(i);
                        }
                        return true;
                    case IFMAYORIGUAL:
                        if (Integer.valueOf(act.getOperand1()) >= Integer.valueOf(act.getOperand2())) {
                            act.modInstruccion(Operation.GOTO, null, null, act.getDestiny());
                        } else {
                            instrucciones.deleteInst(i);
                        }
                        return true;
                    case IFMENORIGUAL:
                        if (Integer.valueOf(act.getOperand1()) <= Integer.valueOf(act.getOperand2())) {
                            act.modInstruccion(Operation.GOTO, null, null, act.getDestiny());
                        } else {
                            instrucciones.deleteInst(i);
                        }
                        return true;
                    case IFDIFERENTE:
                        if (!act.getOperand1().equals(act.getOperand2())) {
                            act.modInstruccion(Operation.GOTO, null, null, act.getDestiny());
                        } else {
                            instrucciones.deleteInst(i);
                        }
                        return true;
                }
            }
        }
        return false;
    }

    private boolean Rreducciodeforca(int i, Instruction act) {

        if(act.getOperation().equals(Operation.MULTIPLICACION)){
            Variable op1 = tac.getVar(act.getOperand1());
            if (op1 == null) {
                int x = Integer.valueOf(act.getOperand1());
                if (x != 0 && ((x & (x - 1)) == 0)) {
                    x = x / 2;
                    act.modInstruccion(Operation.DESPLAZAR_BITS,String.valueOf(x),act.getOperand2(),act.getDestiny());
                    return true;
                }
            }
            Variable op2 = tac.getVar(act.getOperand2());
            if (op2 == null) {
                int x = Integer.valueOf(act.getOperand2());
                if (x != 0 && ((x & (x - 1)) == 0)) {
                    x = x / 2;
                    act.modInstruccion(Operation.DESPLAZAR_BITS,String.valueOf(x),act.getOperand1(),act.getDestiny());
                    return true;
                }
            }
        } else if(act.getOperation().equals(Operation.DIVISION)){
            Variable op2 = tac.getVar(act.getOperand2());
            if (op2 == null) {
                int x = Integer.valueOf(act.getOperand2());
                if (x != 0 && ((x & (x - 1)) == 0)) {
                    x = -(x / 2);
                    act.modInstruccion(Operation.DESPLAZAR_BITS,String.valueOf(x),act.getOperand1(),act.getDestiny());
                    return true;
                }
            }
        }
        return false;
    }

    private boolean esTemporal(String variable){
        if(variable.startsWith("t")){
            try {
                String aux = variable.substring(1);
                Integer.parseInt(aux);
                return true;
            }catch(NumberFormatException e){
                return false;
            }
        }else{
            return false;
        }
    }
}

