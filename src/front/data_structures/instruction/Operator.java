package front.data_structures.instruction;


public class Operator {

    private String name;

    public Operator(String s) {
        name = s;
    }


    public boolean isLiteral() {
        return name.charAt(0) == '"' || name.matches("[0-9]+");
    }


    public boolean isNumber() {
        return name.matches("[0-9]+");
    }

    public boolean isLogic() {
        return name.equals("true") || name.equals("false");
    }

    public boolean isString() {
        return !isLogic() && !isNumber();
    }



    @Override
    public String toString() {
        return name;
    }

}
