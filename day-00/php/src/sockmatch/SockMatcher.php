<?php

declare(strict_types=1);

namespace SockMatch;

final class SockMatcher
{
    private const PATTERN_WEIGHT = 25;
    private const COLOR_WEIGHT = 20;
    private const MATERIAL_WEIGHT = 20;
    private const SIZE_WEIGHT = 20;
    private const SIZE_PENALTY_PER_POINT = 4;
    private const WARMTH_WEIGHT = 15;
    private const WARMTH_PENALTY_PER_POINT = 3;
    private const MATCH_BONUS = 5;
    private const PERFECT_MATCH_SCORE = 100;

    public static function computeCompatibilityScore(Sock $a, Sock $b): int
    {
        $score = 0;

        $samePattern = $a->pattern === $b->pattern;
        $sameColor = $a->color === $b->color;
        $sameMaterial = $a->material === $b->material;
        $sameSize = $a->size === $b->size;
        $sameWarmth = $a->warmth === $b->warmth;

        $score += $samePattern ? self::PATTERN_WEIGHT : 0;
        $score += $sameColor ? self::COLOR_WEIGHT : 0;
        $score += $sameMaterial ? self::MATERIAL_WEIGHT : 0;
        $score += max(0, self::SIZE_WEIGHT - abs($a->size - $b->size) * self::SIZE_PENALTY_PER_POINT);
        $score += max(0, self::WARMTH_WEIGHT - abs($a->warmth - $b->warmth) * self::WARMTH_PENALTY_PER_POINT);

        if ($samePattern && $sameColor && $sameMaterial && $sameSize && $sameWarmth) {
            $score += self::MATCH_BONUS;
        }

        return $score;
    }

    public static function isPair(Sock $a, Sock $b): bool
    {
        return self::computeCompatibilityScore($a, $b) === self::PERFECT_MATCH_SCORE;
    }
}
