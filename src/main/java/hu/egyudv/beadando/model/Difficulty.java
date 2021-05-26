package hu.egyudv.beadando.model;

public enum Difficulty {
    LOW("LOW"),
    MEDIUM("MEDIUM"),
    HARD("HARD");

    public final String label;

    Difficulty(String label) {
        this.label = label;
    }

    public static Difficulty getByLabel(String label) {
        for (Difficulty difficulty : values()) {
            if (difficulty.label.equals(label)) {
                return difficulty;
            }
        }
        return null;
    }
}
