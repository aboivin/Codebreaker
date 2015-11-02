package fr.boivina.codebreaker;

import org.apache.commons.lang3.StringUtils;

import java.util.Random;
import java.util.Scanner;

public class Application {

    private static final int CODE_LENGTH = 4;

    private CodeBreaker codeBreaker;

    private CodeGenerator codeGenerator = new CodeGenerator(new Random());

    public static void main(String[] args) {
        Application app = new Application();
        app.initCodeBreaker();
        app.guessCodeUntilSecretCodeIsFound();
    }

    public void initCodeBreaker() {
        String secretCode = codeGenerator.generateCode(CODE_LENGTH);
        codeBreaker = new CodeBreaker(secretCode);
    }

    public void guessCodeUntilSecretCodeIsFound() {
        String output;
        do {
            String guessedCode = askForTheGuessedCode();
            output = codeBreaker.guessSecretCode(guessedCode);
            System.out.println("Ouptut : "+output);
        } while(outputIsStillInvalid(output));
    }

    private String askForTheGuessedCode() {
        String code;
        Scanner reader = new Scanner(System.in);
        do {
            System.out.println("Enter a valid code. ("+ CODE_LENGTH + " digits natural number)");
            code = reader.next();
        } while (codeIsStillInvalid(code));
        return code;
    }

    private boolean outputIsStillInvalid(String output) {
        String endGameOutput = StringUtils.repeat(MatchState.EXACT_MATCH.getOutputFormat(),CODE_LENGTH);
        return !endGameOutput.equals(output);
    }

    private boolean codeIsStillInvalid(String code) {
        return codeIsNotANaturalNumber(code) || codeHasIncorrectLength(code);
    }

    private boolean codeIsNotANaturalNumber(String code) {
        boolean result;
        try {
            int value = Integer.parseInt(code);
            result = value < 0;
        } catch(NumberFormatException e){
            result = true;
        }
        return result;
    }

    private boolean codeHasIncorrectLength(String code) {
        return code.length() != CODE_LENGTH;
    }
}