package fr.boivina.codebreaker;

import com.sun.org.apache.bcel.internal.classfile.Code;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;

/**
 * Class handling code generation.
 */
public class CodeGenerator {

    private Random random;

    public CodeGenerator(Random random) {
        this.random = random;
    }

    /**
     * Generate a String representing a natual number with the specified number of digits.
     * @param codeLength
     * @return code
     */
    public String generateCode(int codeLength) {
        int roll = random.nextInt((int) Math.pow(10, codeLength) -1);
        return StringUtils.leftPad(String.valueOf(roll), codeLength, "0");
    }
}
