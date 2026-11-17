package sockmatch;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SockMatcherTests {
    private static final Sock RED_WOOL_STRIPES = new Sock(42, "rouge", "laine", "rayures", 8);

    private static Stream<SockMatchTestCase> sockMatchTestCases() {
        return Stream.of(
                new SockMatchTestCase(
                        "two strictly identical socks",
                        RED_WOOL_STRIPES,
                        new Sock(42, "rouge", "laine", "rayures", 8),
                        true
                ),
                new SockMatchTestCase(
                        "same everything except color",
                        RED_WOOL_STRIPES,
                        new Sock(42, "bleu", "laine", "rayures", 8),
                        false
                ),
                new SockMatchTestCase(
                        "same everything except a size difference of 1",
                        RED_WOOL_STRIPES,
                        new Sock(43, "rouge", "laine", "rayures", 8),
                        false
                ),
                new SockMatchTestCase(
                        "same everything except a warmth difference of 2",
                        RED_WOOL_STRIPES,
                        new Sock(42, "rouge", "laine", "rayures", 6),
                        false
                ),
                new SockMatchTestCase(
                        "completely different socks",
                        RED_WOOL_STRIPES,
                        new Sock(36, "jaune", "coton", "pois", 1),
                        false
                )
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("sockMatchTestCases")
    void should_evaluate_pair_correctly(SockMatchTestCase testCase) {
        assertThat(SockMatcher.isPair(testCase.sockA(), testCase.sockB()))
                .as(testCase.description())
                .isEqualTo(testCase.expectedResult());
    }

    private record SockMatchTestCase(String description, Sock sockA, Sock sockB, boolean expectedResult) {
    }
}
