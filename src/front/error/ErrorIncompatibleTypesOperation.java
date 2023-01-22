package front.error;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ErrorIncompatibleTypesOperation extends SintaxErrorException {
    @Override
    public int printError(int[] column_line, String var_err) {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("files_output/Errors.txt", true), StandardCharsets.UTF_8));

            writer.write("[Error SEMÀNTIC]:"
                    + "[" + column_line[0] + ":" + column_line[1] + "]"
                    + " Els tipus de les variable o variables no son compatibles per aquesta operació.\n");

            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(ErrorVarNotDec.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }
}
