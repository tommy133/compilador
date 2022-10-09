/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package front.error;

import java.io.Writer;

public abstract class SintaxErrorException extends Exception {
    Writer writer;
    public abstract int printError(int[] lc, String variableError);
}

