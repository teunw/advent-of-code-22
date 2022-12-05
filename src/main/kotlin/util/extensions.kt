fun <T>List<T>.chunkBy(by: T): List<List<T>> {
    return this.chunkBy { it!!.equals(by) }
}
fun <T>List<T>.chunkBy(predicate: (T) -> Boolean): List<List<T>> {
    val chunks = mutableListOf<List<T>>()
    val currentChunk = mutableListOf<T>()
    for (item in this) {
        if (predicate(item)) {
            chunks.add(currentChunk.toList())
            currentChunk.clear()
        } else {
            currentChunk.add(item)
        }
    }
    chunks.add(currentChunk.toList())
    currentChunk.clear()
    return chunks.toList()
}
