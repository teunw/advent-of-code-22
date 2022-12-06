package days

class Day6 : Day(6) {

    override fun partOne(): Int {
        for (i in 0..inputString.length) {
            val setSize = inputString.substring(i, i + 4).toSet().size
            if (setSize == 4) {
                return i + 4
            }
        }
        throw Error("Not found :(")
    }

    override fun partTwo(): Int {
        for (i in 0..inputString.length - 14) {
            val setSize = inputString.substring(i, i + 14).toSet().size
            if (setSize == 14) {
                return i + 14
            }
        }
        throw Error("Not found :(")
    }
}
