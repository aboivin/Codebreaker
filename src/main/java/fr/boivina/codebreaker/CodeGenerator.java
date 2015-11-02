package fr.boivina.codebreaker;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * Class handling code generation.
 */
public class CodeGenerator {

    /**
     * Generate a String representing a natual number with the specified number of digits.
     * @param codeLength
     * @return code
     */
    public String generateCode(int codeLength) {
        Random random = new Random();
        int roll = random.nextInt((int) Math.pow(10, codeLength) -1);

        return StringUtils.leftPad("" + roll, codeLength, "0");
    }
}
