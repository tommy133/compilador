/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.error;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ErrorSintax extends SintaxErrorException {

    @Override
    public int printError(int[] column_line, String var_error) {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("files_output/Errors.txt", true), StandardCharsets.UTF_8));
            
            writer.write("[Error SINTACTIC]:["
                + column_line[0] + ":" + column_line[1] +
                "] Sintaxis incorrecte.\n");

            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(ErrorSintax.class.getName()).log(Level.SEVERE, null, ex);

        }

        return 0;
    }

}
