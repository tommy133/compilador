/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package front.error;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ErrorFatalSintax extends SintaxErrorException {

    @Override
    public int printError(String place, int[] column_line, String var_error) {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("files_output/Errors.txt", true), StandardCharsets.UTF_8));
            
            writer.write(place + " [Error SINTACTIC]:  Error fatal de compilació, el process de compilació se ha abortat.\n");

            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(ErrorFatalSintax.class.getName()).log(Level.SEVERE, null, ex);

        }

        return 0;
    }

}
