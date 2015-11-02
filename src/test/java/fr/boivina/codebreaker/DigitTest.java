package fr.boivina.codebreaker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class DigitTest {

    @Test
    public void testDigitEqualsAndHashCode() {

        Digit a = new Digit(new Integer(2));
        Digit b = new Digit(new Integer(2));
        Digit c = new Digit(new Integer(2));
        c.flagAsExactMatch();

        assertThat(a).isEqualTo(b);
        assertThat(b).isEqualTo(a);
        assertThat(c).isNotEqualTo(a);
        assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }
}
