<?php

declare(strict_types=1);

namespace SockMatch;

final class Sock
{
    public function __construct(
        public readonly int $size,
        public readonly string $color,
        public readonly string $material,
        public readonly string $pattern,
        public readonly int $warmth,
    ) {
    }
}
