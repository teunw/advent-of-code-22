package days

class Day2 : Day(2) {

    private val wins = mapOf(
        0 to 1,
        1 to 2,
        2 to 0
    )

    private fun getMatchResultPartOne(opponent: Int, me: Int): Int {
        if (opponent == me) {
            return 3
        }
        val didIWin = wins[opponent] == me
        return if (didIWin) 6 else 0
    }

    private fun getChoicePartTwo(opponent: Int, outcome: Int) =
        when (outcome) {
            0 -> wins.entries.find { it.value == opponent }!!.key
            1 -> opponent + 3
            2 -> wins[opponent]!! + 6
            else -> throw Exception("Invalid move")
        } + 1

    override fun partOne(): Int {
        return inputList
            .sumOf {
                val chars = it.split(' ').map { it[0] }
                val opponent = chars[0].minus('A')
                val me = chars[1].minus('X')
                getMatchResultPartOne(opponent, me) + (me + 1)
            }
    }

    override fun partTwo(): Int {
        return inputList
            .sumOf { line ->
                val chars = line.split(' ').map { it[0] }
                val opponent = chars[0].minus('A')
                val outcome = chars[1].minus('X')
                getChoicePartTwo(opponent, outcome)
            }
    }
}
