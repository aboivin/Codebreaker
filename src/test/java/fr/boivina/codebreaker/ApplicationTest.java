package fr.boivina.codebreaker;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTest {

    @InjectMocks
    private Application app;

    @Mock
    private CodeBreaker codeBreaker;

    @Mock
    private CodeGenerator codeGenerator;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void codeGeneration() {

        // Given
        Mockito.when(codeGenerator.generateCode(4)).thenReturn("1234");

        // When
        app.initCodeBreaker();

        // Then
        Mockito.verify(codeGenerator, Mockito.times(1)).generateCode(4);
    }

    @Test
    public void nominalCase() throws IOException {

        // Given
        Mockito.when(codeBreaker.guessSecretCode("1234")).thenReturn("++++");
        ByteArrayInputStream inContent = new ByteArrayInputStream("1234".getBytes());
        System.setIn(inContent);

        // When
        app.guessCodeUntilSecretCodeIsFound();

        // Then
        System.setIn(System.in);
        Mockito.verify(codeBreaker, Mockito.times(1)).guessSecretCode("1234");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream(outContent.toByteArray())));
        assertThat(bufferedReader.readLine()).isEqualTo("Enter a valid code. (4 digits natural number)");
        assertThat(bufferedReader.readLine()).isEqualTo("Ouptut : ++++");
    }

    @Test
    public void incorrectParameter() throws IOException {

        // Given
        ByteArrayInputStream in = new ByteArrayInputStream("abc 12345 -123 1234".getBytes());
        System.setIn(in);
        Mockito.when(codeBreaker.guessSecretCode("1234")).thenReturn("++++");

        // When
        app.guessCodeUntilSecretCodeIsFound();

        // Then
        System.setIn(System.in);
        Mockito.verify(codeBreaker, Mockito.times(1)).guessSecretCode("1234");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new ByteArrayInputStream(outContent.toByteArray())));
        assertThat(bufferedReader.readLine()).isEqualTo("Enter a valid code. (4 digits natural number)");
        assertThat(bufferedReader.readLine()).isEqualTo("Enter a valid code. (4 digits natural number)");
        assertThat(bufferedReader.readLine()).isEqualTo("Enter a valid code. (4 digits natural number)");
        assertThat(bufferedReader.readLine()).isEqualTo("Enter a valid code. (4 digits natural number)");
        assertThat(bufferedReader.readLine()).isEqualTo("Ouptut : ++++");

    }
}