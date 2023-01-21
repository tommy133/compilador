/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.error;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;
import java.util.logging.Level;


public class ErrorConstAssign extends SintaxErrorException {

    @Override
    public int printError(String place, int[] column_line, String var_err) {
        try {

            writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("files_output/Errors.txt", true), StandardCharsets.UTF_8));
            writer.write(place + " [Error SEMÀNTIC]:"
                + "[" + column_line[0] + ":" + column_line[1] + "]"
                + " Intent de modificació d'una constant ja declarada. "
                + "'" + var_err + "'" + "\n");

            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(ErrorVarNotDec.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

}
