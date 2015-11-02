package fr.boivina.codebreaker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class CodeBreakerTest {

    private void checkCodeBreakerOutputGivenSecretAndGuessedCode(String secretCode, String guessedCode, String output) {
        CodeBreaker codeBreaker = new CodeBreaker(secretCode);
        String result = codeBreaker.guessSecretCode(guessedCode);
        assertThat(result).isEqualTo(output);
    }

    @Test
    public void noMatch() {
        checkCodeBreakerOutputGivenSecretAndGuessedCode("0000", "1111", "");
    }

    @Test
    public void fourDigitMatch() {
        checkCodeBreakerOutputGivenSecretAndGuessedCode("1234", "4321", "----");
    }

    @Test
    public void twoExactMatchTwoDigitMatch() {
        checkCodeBreakerOutputGivenSecretAndGuessedCode("1234", "4231", "++--");
    }

    @Test
    public void twoExactOneDigitMatch() {
        checkCodeBreakerOutputGivenSecretAndGuessedCode("1234", "1245", "++-");
    }

    @Test
    public void oneDigitMatch() {
        checkCodeBreakerOutputGivenSecretAndGuessedCode("1234", "2002", "-");
    }

    @Test
    public void fourExactMatch() {
        checkCodeBreakerOutputGivenSecretAndGuessedCode("1234", "1234", "++++");
    }

    @Test
    public void endingByZero() {
        checkCodeBreakerOutputGivenSecretAndGuessedCode("0000", "1230", "+");
    }

    @Test
    public void startWithZero() {
        checkCodeBreakerOutputGivenSecretAndGuessedCode("0000", "0000", "++++");
    }

    @Test
    public void exactMatchPriority() {
        checkCodeBreakerOutputGivenSecretAndGuessedCode("1234", "2255", "+");
    }

    @Test
    public void multipleGuess() {
        CodeBreaker codeBreaker = new CodeBreaker("2234");
        codeBreaker.guessSecretCode("1234");
        String result = codeBreaker.guessSecretCode("2234");
        assertThat(result).isEqualTo("++++");
    }
}
