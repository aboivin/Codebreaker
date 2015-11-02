package fr.boivina.codebreaker;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class CodeGeneratorTest {

    @Test
    public void generateRandomSecretNumber() {

        CodeGenerator codeGenerator = new CodeGenerator();
        String result = codeGenerator.generateCode(4);
        System.out.println(result);
        assertThat(result).hasSize(4);
        assertThat(NumberUtils.isNumber(result)).isTrue();
    }
}
