package fr.boivina.codebreaker;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * CodeBreaker algorithm.
 * Secret code is given at initialization and must be found with succesive guess.
 * Each guess returns a clue on base on guessed code and secret code digits match.
 */
public class CodeBreaker {

    private final List<Digit> secretCodeDigits;

    public CodeBreaker(String secretCode) {
        this.secretCodeDigits = convertCodeToListOfDigits(secretCode);
    }

    /**
     * For each digit, it will return a + sign for an exact match (matches a digit of the secret code both in value 
     * and in position) and will return a - sign for a digit match (matches a digit of the secret code in value but
     * does not have the correct position.)
     * Exact matches have priority over digit matches.
     * Once a digit has been used for an exact match in the secret code, it can no longer be used for any digit match.
     * @param code
     * @return formated output
     */
    public String guessSecretCode(String code) {

        List<Digit> codeDigits = convertCodeToListOfDigits(code);
        flagAllDigitsHavingAnExactMatch(codeDigits);
        flagAllDigitsHavingADigitMatch(codeDigits);

        return formatOutputUsingDigitsFlag(codeDigits);
    }

    private List<Digit> convertCodeToListOfDigits(String number) {
        return number.chars()
                .mapToObj(Character::getNumericValue)
                .map(Digit::new)
                .collect(Collectors.toList());
    }

    private void flagAllDigitsHavingAnExactMatch(List<Digit> guessedCodeDigits) {
        IntStream.range(0, secretCodeDigits.size())
                .filter(i -> guessedCodeDigits.get(i).equals(secretCodeDigits.get(i)))
                .forEach(i -> guessedCodeDigits.get(i).flagAsExactMatch());
    }

    /*
     * For each secret code digit verify that the digit is not an exact match (by checking the associated digit
     * in the guessed code) and that the digit is present in the guessed code.
     * In this case flag it as "Digit match".
     */
    private void flagAllDigitsHavingADigitMatch(List<Digit> guessedCodeDigits) {
        IntStream.range(0, secretCodeDigits.size())
                .filter(i -> !guessedCodeDigits.get(i).isExactMatch() && guessedCodeDigits.contains(secretCodeDigits.get(i)))
                .forEach(i -> guessedCodeDigits.get(guessedCodeDigits.indexOf(secretCodeDigits.get(i))).flagAsDigitMatch());
    }

    private String formatOutputUsingDigitsFlag(List<Digit> digits) {
        return digits.stream()
                .map(Digit::getState)
                .map(MatchState::getOutputFormat)
                .sorted()
                .collect(Collectors.joining());
    }
}