namespace SockMatch.Tests;

public class SockMatcherTests
{
    private static readonly Sock RedWoolStripes = new(42, "rouge", "laine", "rayures", 8);

    public static TheoryData<Sock, Sock, bool> SockMatchTestCases => new()
    {
        {RedWoolStripes, RedWoolStripes with { }, true},
        {RedWoolStripes, RedWoolStripes with {Color = "bleu"}, false},
        {RedWoolStripes, RedWoolStripes with {Size = 43}, false},
        {RedWoolStripes, RedWoolStripes with {Warmth = 6}, false},
        {RedWoolStripes, new Sock(36, "jaune", "coton", "pois", 1), false},
    };

    [Theory]
    [MemberData(nameof(SockMatchTestCases))]
    public void ShouldEvaluatePairCorrectly(Sock sockA, Sock sockB, bool expectedResult)
        => Assert.Equal(expectedResult, SockMatcher.IsPair(sockA, sockB));
}
