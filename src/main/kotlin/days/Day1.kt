package days

import chunkBy

class Day1 : Day(1) {

    private fun getCalories(): List<Int> {
        return inputList
            .chunkBy("")
            .map { it.sumOf { line -> line.toInt() } }
            .sortedDescending()
    }

    override fun partOne() = this.getCalories().first()

    override fun partTwo() = this.getCalories().take(3).sum()
}
