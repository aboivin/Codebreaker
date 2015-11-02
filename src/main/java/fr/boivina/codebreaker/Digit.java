package fr.boivina.codebreaker;

public class Digit {

    private final Integer value;

    private MatchState state = MatchState.NONE;

    public Digit(Integer value) {
        this.value = value;
    }

    public boolean isExactMatch() {
        return state == MatchState.EXACT_MATCH;
    }

    public MatchState getState() {
        return state;
    }

    public void flagAsExactMatch() {
        this.state = MatchState.EXACT_MATCH;
    }

    public void flagAsDigitMatch() {
        this.state = MatchState.DIGIT_MATCH;
    }

    /*
     * Test equality based on both value and state.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Digit digit = (Digit) o;

        if (value != null ? !value.equals(digit.value) : digit.value != null) return false;
        return state == digit.state;

    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }
}