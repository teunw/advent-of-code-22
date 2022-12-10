package days

import java.util.*

data class Point2D(val x: Int, val y: Int) : Comparable<Point2D> {
    fun copy(newX: Int? = null, newY: Int? = null): Point2D {
        return Point2D(newX ?: this.x, newY ?: this.y)
    }

    override fun compareTo(other: Point2D): Int {
        return ((this.x - other.x)) - ((other.y - this.y) * 999)
    }
}

fun <T>List<T>.takeUntil(predicate: (T) -> Boolean): List<T> {
    val newList = mutableListOf<T>()
    for (it in this) {
        if (predicate(it)) {
            newList.add(it)
        } else {
            newList.add(it)
            break;
        }
    }
    return newList.toList()
}

class Day8 : Day(8) {

    override fun partOne(): Int {
        val trees = inputList.flatMapIndexed { x: Int, line: String ->
            line.mapIndexed { y, char -> Point2D(x, y) to char.digitToInt() }
        }.toHashSet()
        val rightEdge = trees.maxOf { it.first.x }
        val bottomEdge = trees.maxOf { it.first.y }

        val visibleTrees = trees.filter { (point, height) ->
            point.x == 0 ||
                    point.y == 0 ||
                    point.x == rightEdge ||
                    point.y == bottomEdge ||
                    isTallerThanNeighbors(trees, point, height)
        }.sortedBy { it.first }

        return visibleTrees.size
    }

    private fun isTallerThanNeighbors(trees: HashSet<Pair<Point2D, Int>>, point: Point2D, height: Int): Boolean {
        val tallerTrees = trees.filter { it.second >= height }
        val row = tallerTrees.filter { it.first.x == point.x && point.y != it.first.y }
        val column = tallerTrees.filter { it.first.y == point.y && point.x != it.first.x }

        val top = row.filter { it.first.y < point.y }
        val bottom = row.filter { it.first.y > point.y }
        val left = column.filter { it.first.x < point.x }
        val right = column.filter { it.first.x > point.x }

        return listOf(left, right, top, bottom).any { it.isEmpty() }
    }

    private fun getScore(trees: SortedSet<Pair<Point2D, Int>>, point: Point2D, height: Int): Int {
        val column = trees.filter {
            it.first.x == point.x && point != it.first
        }
        val row = trees.filter {
            it.first.y == point.y && point != it.first
        }

        val top = column.filter { it.first.y < point.y }.reversed()
            .takeUntil { it.second < height }.count()
        val bottom = column.filter { it.first.y > point.y }
            .takeUntil { it.second < height }.count()
        val left = row.filter { it.first.x < point.x }.reversed()
            .takeUntil { it.second < height }.count()
        val right = row.filter { it.first.x > point.x }
            .takeUntil { it.second < height }.count()

        return listOf(top, bottom, left, right).filter { it > 0 }
            .fold(1) { acc, int -> acc.times(int) }
    }

    override fun partTwo(): Int {
        val trees = inputList.flatMapIndexed { y: Int, line: String ->
            line.mapIndexed { x, char -> Point2D(x, y) to char.digitToInt() }
        }.toSortedSet { o1, o2 -> o1.first.compareTo(o2.first) }

        val visibleTrees = trees.map { (point, height) ->
            point to getScore(trees, point, height)
        }.sortedBy { it.first }

        return visibleTrees.maxBy { it.second }.second
    }
}
