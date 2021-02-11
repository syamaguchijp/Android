
enum class Season {
    SPRING,
    SUMMER,
    AUTUMN,
    WINTER
}

enum class SeasonInt(val value: Int) {
    SPRING(0),
    SUMMER(1),
    AUTUMN(2),
    WINTER(3)
}

fun main() {
    val season = Season.SPRING
    println(season)
    
    val seasonInt = SeasonInt.SPRING
    println(seasonInt.value)
}