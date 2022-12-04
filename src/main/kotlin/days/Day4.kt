package days

class Day4 : Day(4) {

    private fun containsTheOther(rangeA: IntRange, rangeB: IntRange): Boolean {
        if (rangeA.contains(rangeB.first) && rangeA.contains(rangeB.last)) {
            return true
        }
        if (rangeB.contains(rangeA.first) && rangeB.contains(rangeA.last)) {
            return true
        }
        return false
    }

    private fun parseLines(): List<Pair<IntRange, IntRange>> =
        inputList
            .map { line -> line.split(',').map { it.split('-') } }
            .map { Pair(IntRange(it[0][0].toInt(), it[0][1].toInt()), IntRange(it[1][0].toInt(), it[1][1].toInt())) }

    override fun partOne(): Int {
        return parseLines()
            .count { containsTheOther(it.first, it.second) }
    }

    override fun partTwo(): Int {
        return parseLines()
            .count { ranges -> ranges.first.any { ranges.second.contains(it) } }
    }
}
