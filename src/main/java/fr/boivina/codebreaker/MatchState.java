package fr.boivina.codebreaker;

/**
 * Possible state of a digit during matching.
 */
public enum MatchState {

    EXACT_MATCH("+"), DIGIT_MATCH("-"), NONE("");

    private final String outputFormat;

    MatchState(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public String getOutputFormat() {
        return outputFormat;
    }
}
