package days

class Day1 : Day(1) {

    private fun getCalories(): List<Int> =
        inputString
            .split("\n\n")
            .map { it.split('\n').sumOf { line -> line.toInt() } }
            .sortedDescending()

    override fun partOne() = this.getCalories().first()

    override fun partTwo() = this.getCalories().take(3).sum()
}
