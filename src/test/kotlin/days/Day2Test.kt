package days

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.jupiter.api.Test

class Day2Test {

    private val dayTwo = Day2()

    @Test
    fun testPartOne() {
        assertThat(dayTwo.partOne(), `is`(15))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayTwo.partTwo(), `is`(12))
    }
}
