namespace SockMatch;

public static class SockMatcher
{
    private const int PatternWeight = 25;
    private const int ColorWeight = 20;
    private const int MaterialWeight = 20;
    private const int SizeWeight = 20;
    private const int SizePenaltyPerPoint = 4;
    private const int WarmthWeight = 15;
    private const int WarmthPenaltyPerPoint = 3;
    private const int MatchBonus = 5;
    private const int PerfectMatchScore = 100;

    private static int ComputeCompatibilityScore(Sock a, Sock b)
    {
        var score = 0;

        var samePattern = a.Pattern == b.Pattern;
        var sameColor = a.Color == b.Color;
        var sameMaterial = a.Material == b.Material;
        var sameSize = a.Size == b.Size;
        var sameWarmth = a.Warmth == b.Warmth;

        score += samePattern ? PatternWeight : 0;
        score += sameColor ? ColorWeight : 0;
        score += sameMaterial ? MaterialWeight : 0;
        score += Math.Max(0, SizeWeight - Math.Abs(a.Size - b.Size) * SizePenaltyPerPoint);
        score += Math.Max(0, WarmthWeight - Math.Abs(a.Warmth - b.Warmth) * WarmthPenaltyPerPoint);

        if (samePattern && sameColor && sameMaterial && sameSize && sameWarmth)
            score += MatchBonus;

        return score;
    }

    public static bool IsPair(Sock a, Sock b) => ComputeCompatibilityScore(a, b) == PerfectMatchScore;
}
