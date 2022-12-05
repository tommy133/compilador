package back;

import back.generator.ThreeAddressCodeSintetic;

public class TestBack {

    public static void main(String[] args) {

        try {
           new ThreeAddressCodeSintetic();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
