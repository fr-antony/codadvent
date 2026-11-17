<?php

use SockMatch\Sock;
use SockMatch\SockMatcher;

function redWoolStripes(array $overrides = []): Sock
{
    return new Sock(
        size: $overrides['size'] ?? 42,
        color: $overrides['color'] ?? 'rouge',
        material: $overrides['material'] ?? 'laine',
        pattern: $overrides['pattern'] ?? 'rayures',
        warmth: $overrides['warmth'] ?? 8,
    );
}

dataset('sockMatchTestCases', [
    'two strictly identical socks' => [redWoolStripes(), redWoolStripes(), true],
    'same everything except color' => [redWoolStripes(), redWoolStripes(['color' => 'bleu']), false],
    'same everything except a size difference of 1' => [redWoolStripes(), redWoolStripes(['size' => 43]), false],
    'same everything except a warmth difference of 2' => [redWoolStripes(), redWoolStripes(['warmth' => 6]), false],
    'completely different socks' => [redWoolStripes(), new Sock(36, 'jaune', 'coton', 'pois', 1), false],
]);

it('should evaluate the pair correctly', function (Sock $sockA, Sock $sockB, bool $expectedResult) {
    expect(SockMatcher::isPair($sockA, $sockB))->toBe($expectedResult);
})->with('sockMatchTestCases');
