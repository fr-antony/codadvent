package sockmatch;

public final class SockMatcher {
    private static final int PATTERN_WEIGHT = 25;
    private static final int COLOR_WEIGHT = 20;
    private static final int MATERIAL_WEIGHT = 20;
    private static final int SIZE_WEIGHT = 20;
    private static final int SIZE_PENALTY_PER_POINT = 4;
    private static final int WARMTH_WEIGHT = 15;
    private static final int WARMTH_PENALTY_PER_POINT = 3;
    private static final int MATCH_BONUS = 5;
    private static final int PERFECT_MATCH_SCORE = 100;

    private static int computeCompatibilityScore(Sock a, Sock b) {
        int score = 0;

        boolean samePattern = a.pattern().equals(b.pattern());
        boolean sameColor = a.color().equals(b.color());
        boolean sameMaterial = a.material().equals(b.material());
        boolean sameSize = a.size() == b.size();
        boolean sameWarmth = a.warmth() == b.warmth();

        score += samePattern ? PATTERN_WEIGHT : 0;
        score += sameColor ? COLOR_WEIGHT : 0;
        score += sameMaterial ? MATERIAL_WEIGHT : 0;
        score += Math.max(0, SIZE_WEIGHT - Math.abs(a.size() - b.size()) * SIZE_PENALTY_PER_POINT);
        score += Math.max(0, WARMTH_WEIGHT - Math.abs(a.warmth() - b.warmth()) * WARMTH_PENALTY_PER_POINT);

        if (samePattern && sameColor && sameMaterial && sameSize && sameWarmth) {
            score += MATCH_BONUS;
        }

        return score;
    }

    public static boolean isPair(Sock a, Sock b) {
        return computeCompatibilityScore(a, b) == PERFECT_MATCH_SCORE;
    }
}
