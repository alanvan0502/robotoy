import kotlin.system.exitProcess

const val BOARD_HEIGHT = 5
const val BOARD_WIDTH = 5

fun main() {

    var robot: Robot? = null

    while (true) {
        println("Place initial robot position. For example: PLACE 0,0,NORTH:")
        val input = readLine()
        if (input?.matches(Regex("^PLACE\\s[0-9],[0-9],(NORTH|SOUTH|EAST|WEST)")) == true) {
            try {
                processPlaceInput(input)?.let {
                    robot = Robot.createRobot(it.first, it.second)
                }
                break
            } catch (e: InvalidPositionException) {
                println("Invalid robot position. Place again!")
            }
        } else {
            println("Invalid place command!")
        }
    }

    while (true) {
        println("-_-_-_-_-_-_-_-")
        println("Move your robot with the following command MOVE, LEFT, RIGHT")
        println("To report current robot position, type REPORT")
        println("To exit program, type EXIT")

        when (readLine()) {
            "EXIT" -> exitProcess(0)
            "MOVE" -> robot?.move()
            "LEFT" -> robot?.turnLeft()
            "RIGHT" -> robot?.turnRight()
            "REPORT" -> println(robot?.report())
            else -> {
                println("Invalid command!")
            }
        }
    }
}