export interface Sock {
    size: number;
    color: string;
    material: string;
    pattern: string;
    warmth: number;
}

export class SockMatcher {
    private static readonly PATTERN_WEIGHT = 25;
    private static readonly COLOR_WEIGHT = 20;
    private static readonly MATERIAL_WEIGHT = 20;
    private static readonly SIZE_WEIGHT = 20;
    private static readonly SIZE_PENALTY_PER_POINT = 4;
    private static readonly WARMTH_WEIGHT = 15;
    private static readonly WARMTH_PENALTY_PER_POINT = 3;
    private static readonly MATCH_BONUS = 5;
    private static readonly PERFECT_MATCH_SCORE = 105;

    public static isPair(a: Sock, b: Sock): boolean {
        return SockMatcher.computeCompatibilityScore(a, b) === SockMatcher.PERFECT_MATCH_SCORE;
    }

    private static computeCompatibilityScore(a: Sock, b: Sock): number {
        let score = 0;

        const samePattern = a.pattern === b.pattern;
        const sameColor = a.color === b.color;
        const sameMaterial = a.material === b.material;
        const sameSize = a.size === b.size;
        const sameWarmth = a.warmth === b.warmth;

        score += samePattern ? SockMatcher.PATTERN_WEIGHT : 0;
        score += sameColor ? SockMatcher.COLOR_WEIGHT : 0;
        score += sameMaterial ? SockMatcher.MATERIAL_WEIGHT : 0;
        score += Math.max(0, SockMatcher.SIZE_WEIGHT - Math.abs(a.size - b.size) * SockMatcher.SIZE_PENALTY_PER_POINT);
        score += Math.max(0, SockMatcher.WARMTH_WEIGHT - Math.abs(a.warmth - b.warmth) * SockMatcher.WARMTH_PENALTY_PER_POINT);

        if (samePattern && sameColor && sameMaterial && sameSize && sameWarmth) {
            score += SockMatcher.MATCH_BONUS;
        }

        return score;
    }
}
