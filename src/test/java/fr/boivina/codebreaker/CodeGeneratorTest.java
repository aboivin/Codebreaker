package fr.boivina.codebreaker;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CodeGeneratorTest {

    public static final int MAX_CODE = 9999;
    public static final int CODE_LENGTH = 4;

    @InjectMocks
    private CodeGenerator codeGenerator;

    @Mock
    private Random random;

    private void checkGeneratedNumberIsValid(int randomNumber, String expected) {

        // Given
        Mockito.when(this.random.nextInt(MAX_CODE)).thenReturn(randomNumber);

        // When
        String result = codeGenerator.generateCode(CODE_LENGTH);

        // Then
        Mockito.verify(random, Mockito.times(1)).nextInt(9999);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void generateNominalCase() {
        checkGeneratedNumberIsValid(1234, "1234");
    }

    @Test
    public void generateZero() {
        checkGeneratedNumberIsValid(0, "0000");
    }
}
