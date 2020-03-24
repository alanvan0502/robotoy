import kotlin.system.exitProcess

const val BOARD_HEIGHT = 5
const val BOARD_WIDTH = 5
const val EXIT_MESSAGE = "To exit program, type EXIT"
const val REPORT_MESSAGE = "To report current robot position, type REPORT"
const val MOVE_MESSAGE = "Move your robot with the following command MOVE, LEFT, RIGHT"
const val INVALID_COMMAND_MESSAGE = "Invalid command!"
const val INVALID_POSITION_MESSAGE = "Invalid robot position. Place again!"
const val INITIAL_PLACE_MESSAGE = "Place initial robot position. For example: PLACE 0,0,NORTH:"

fun main() {

    var robot: Robot? = null

    while (true) {
        println(INITIAL_PLACE_MESSAGE)
        val input = readLine()
        if (input?.matches(Regex("^PLACE\\s[0-9],[0-9],(NORTH|SOUTH|EAST|WEST)")) == true) {
            try {
                processPlaceInput(input)?.let {
                    robot = Robot.createRobot(it.first, it.second)
                }
                break
            } catch (e: InvalidPositionException) {
                println(INVALID_POSITION_MESSAGE)
                println(EXIT_MESSAGE)
            }
        } else {
            println(INVALID_COMMAND_MESSAGE)
            println(EXIT_MESSAGE)
            if (readLine() == "EXIT") {
                exitProcess(0)
            }
        }
    }

    while (true) {
        println("-_-_-_-_-_-_-_-")
        println(MOVE_MESSAGE)
        println(REPORT_MESSAGE)
        println(EXIT_MESSAGE)

        when (readLine()) {
            "EXIT" -> exitProcess(0)
            "MOVE" -> robot?.move()
            "LEFT" -> robot?.turnLeft()
            "RIGHT" -> robot?.turnRight()
            "REPORT" -> println(robot?.report())
            else -> {
                println(INVALID_COMMAND_MESSAGE)
            }
        }
    }
}