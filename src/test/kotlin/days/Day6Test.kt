package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day6Test {

    private val day = Day6()

    @Test
    fun testPartOne() {
        assertThat(day.partOne(), `is`(10))
    }

    @Test
    fun testPartTwo() {
        assertThat(day.partTwo(), `is`(29))
    }
}
