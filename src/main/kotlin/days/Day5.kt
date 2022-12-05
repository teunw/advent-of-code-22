package days

import chunkBy

class Day5 : Day(5) {

    fun parseInput(): Pair<List<MutableList<Char>>, Array<Array<Int>>> {
        val (stackText, instructionText) = inputList.chunkBy { it.isBlank() }

        val stackIndexes = stackText
            .last()
            .mapIndexed { index, s -> if (s.isDigit()) index else null }
            .filterNotNull()
        val stacks = stackIndexes
            .map { index -> stackText.map { line -> line[index] } }
            .map { stack -> stack.filter { it.isLetter() }.toMutableList() }

        val instructionRegex = Regex("move ([0-9]+) from ([0-9]+) to ([0-9]+)")
        val instructions = instructionText.map { instruction ->
            instructionRegex.find(instruction)!!.groupValues.drop(1).map { it.toInt() }.toTypedArray()
        }.toTypedArray()

        return Pair(stacks, instructions)
    }

    override fun partOne(): String {
        val (stackss, instructions) = parseInput()

        return instructions.fold(stackss) { stacks, instruction ->
            val stackCopy = stacks.toMutableList()
            val stackFrom = stackCopy[instruction[1] - 1]
            val stackTo = stackCopy[instruction[2] - 1]
            val elements = stackFrom.take(instruction[0])
            repeat(elements.size) { stackFrom.removeFirst() }
            stackTo.addAll(0, elements.reversed())
            return@fold stackCopy
        }
            .map { it.firstOrNull() ?: "" }
            .joinToString("")
    }

    override fun partTwo(): String {
        val (stackss, instructions) = parseInput()

        return instructions.fold(stackss) { stacks, instruction ->
            val stackCopy = stacks.toMutableList()
            val stackFrom = stackCopy[instruction[1] - 1]
            val stackTo = stackCopy[instruction[2] - 1]
            val elements = stackFrom.take(instruction[0])
            repeat(elements.size) { stackFrom.removeFirst() }
            stackTo.addAll(0, elements)
            return@fold stackCopy
        }
            .map { it.firstOrNull() ?: "" }
            .joinToString("")
    }
}
