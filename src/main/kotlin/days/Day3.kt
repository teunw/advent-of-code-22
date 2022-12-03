package days

class Day3 : Day(3) {

    fun getPriority(char: Char): Int =
        if (char.isUpperCase()) char.minus('A') + 27 else char.minus('a') + 1

    override fun partOne(): Int {
        return inputList
            .map { Pair(it.substring(0, it.length / 2), it.substring(it.length / 2)) }
            .map { chars -> chars.first.first { chars.second.contains(it) } }
            .sumOf { getPriority(it) }
    }

    override fun partTwo(): Int {
        return inputList
            .map { it.toSet() }
            .chunked(3)
            .map {
                it.reduce { accumulator, bag -> accumulator.intersect(bag) }.first()
            }
            .sumOf { getPriority(it) }
    }
}
