fun <T>List<T>.chunkBy(by: T): List<List<T>> {
    val chunks = mutableListOf<List<T>>()
    val currentChunk = mutableListOf<T>()
    for (item in this) {
        if (item == by) {
            chunks.add(currentChunk.toList())
            currentChunk.clear()
        } else {
            currentChunk.add(item)
        }
    }
    return chunks.toList()
}
