fun processPlaceInput(input: String): Pair<Coordinate, Heading>? {
    return try {
        val heading = when {
            input.contains("NORTH") -> Heading.N
            input.contains("SOUTH") -> Heading.S
            input.contains("EAST") -> Heading.E
            input.contains("WEST") -> Heading.W
            else -> null
        }
        heading ?: return null
        val coordinate = input.removePrefix("PLACE").split(",").let {
            Coordinate(it[0].trim().toInt(), it[1].trim().toInt())
        }
        return Pair(coordinate, heading)
    } catch (e: Throwable) {
        null
    }
}