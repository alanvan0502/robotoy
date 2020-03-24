class Robot {

    private lateinit var currentPosition: Coordinate
    private lateinit var currentHeading: Heading

    companion object {
        fun createRobot(position: Coordinate, heading: Heading): Robot {
            if (!position.isWithinBoard()) {
                throw InvalidPositionException()
            }
            return Robot().apply {
                currentPosition = position
                currentHeading = heading
            }
        }
    }

    fun move() {
        val newPosition = when (currentHeading) {
            Heading.N -> Coordinate(currentPosition.x, currentPosition.y + 1)
            Heading.E -> Coordinate(currentPosition.x + 1, currentPosition.y)
            Heading.S -> Coordinate(currentPosition.x, currentPosition.y - 1)
            Heading.W -> Coordinate(currentPosition.x - 1, currentPosition.y)
        }
        moveToPosition(newPosition)
    }

    fun turnLeft() {
        currentHeading = when (currentHeading) {
            Heading.N -> Heading.W
            Heading.W -> Heading.S
            Heading.S -> Heading.E
            Heading.E -> Heading.N
        }
    }

    fun turnRight() {
        currentHeading = when (currentHeading) {
            Heading.N -> Heading.E
            Heading.E -> Heading.S
            Heading.S -> Heading.W
            Heading.W -> Heading.N
        }
    }

    fun report(): String {
        return "$currentPosition,${currentHeading.value}"
    }

    private fun moveToPosition(newPosition: Coordinate) {
        if (newPosition.isWithinBoard()) {
            currentPosition = newPosition
        }
    }
}