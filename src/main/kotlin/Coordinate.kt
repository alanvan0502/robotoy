data class Coordinate (
    val x: Int,
    val y: Int
) {
    override fun toString(): String {
        return "$x,$y"
    }
}

fun Coordinate.isWithinBoard(): Boolean {
    return x >= 0 && x <= BOARD_WIDTH - 1 && y >= 0 && y <= BOARD_HEIGHT - 1
}