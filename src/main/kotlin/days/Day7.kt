package days

import kotlin.io.path.Path

class Day7 : Day(7) {

    private fun getDirSizes(): Pair<MutableMap<String, Int>, List<Pair<String, Int>>> {
        var files = mutableMapOf<String, Int>()
        var dirs = mutableListOf<String>("/")
        var currentDir = ""
        var listingDirectory = false;
        inputList.forEach { command ->
            if (listingDirectory) {
                if (command.startsWith("$")) {
                    listingDirectory = false
                }
                if (command.startsWith("dir")) {
                    dirs.add((currentDir + "/" + command.substring("dir ".length)).replace("//", "/"))
                }
                if (!command.startsWith("$") && !command.startsWith("dir")){
                    val (size, fileName) = command.split(" ")
                    val file = (currentDir + "/" + fileName).replace("//", "/")
                    files[file] = size.toInt()
                }
            }
            if (command.startsWith("$ cd")) {
                if (command == "$ cd ..") {
                    currentDir = currentDir.substring(0, currentDir.lastIndexOf("/"))
                } else {
                    currentDir += "/" + command.substring("$ cd ".length)
                    currentDir = currentDir.replace("//", "/")
                }
            }
            if (command.startsWith("$ ls")) {
                listingDirectory = true
                return@forEach
            }
        }
        files = files.toSortedMap().toMutableMap()

        val dirSizes = dirs.map { dir ->
            dir to files.filter { it.key.startsWith(dir) }.values.sum()
        }

        return Pair(files, dirSizes)
    }

    override fun partOne(): Int {
        return getDirSizes().second
            .filter { it.second < 100000 }.sumOf { it.second }
    }

    override fun partTwo(): Int {
        val (_, dirSizes) = getDirSizes()
        val totalSpace = 70000000
        val unusedSpace = totalSpace - dirSizes.first { it.first == "/" }.second
        val spaceNeeded = 30000000 - unusedSpace;
        val dirToDelete = dirSizes.filter { it.second > spaceNeeded }.minBy { it.second }

        return dirToDelete.second
    }
}
