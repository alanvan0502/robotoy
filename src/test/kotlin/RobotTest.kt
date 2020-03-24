import org.junit.After
import org.junit.Before
import org.junit.Test

internal class RobotTest {

    private var robot: Robot? = null

    @Before
    fun setUp() {
        robot = Robot.createRobot(Coordinate(0,0), Heading.N)
    }

    @Test(expected = InvalidPositionException::class)
    fun createAtInvalidInitialPosition_throwsException() {
        Robot.createRobot(Coordinate(-1, 0), Heading.S)
    }

    @Test
    fun createAtValidInitialPosition_correctPosition() {
        val testRobot = Robot.createRobot(Coordinate(4,3), Heading.E)
        assert(testRobot.report()=="4,3,EAST")
    }

    @Test
    fun moveRobot_validMove() {
        robot?.move()
        robot?.move()
        robot?.move()
        assert(robot?.report()=="0,3,NORTH")
    }

    @Test
    fun turnLeft_validMove() {
        assert(robot?.report()=="0,0,NORTH")
        robot?.turnLeft()
        assert(robot?.report()=="0,0,WEST")
        robot?.turnLeft()
        assert(robot?.report()=="0,0,SOUTH")
        robot?.turnLeft()
        assert(robot?.report()=="0,0,EAST")
        robot?.turnLeft()
        assert(robot?.report()=="0,0,NORTH")
    }

    @Test
    fun turnRight_validMove() {
        assert(robot?.report()=="0,0,NORTH")
        robot?.turnRight()
        assert(robot?.report()=="0,0,EAST")
        robot?.turnRight()
        assert(robot?.report()=="0,0,SOUTH")
        robot?.turnRight()
        assert(robot?.report()=="0,0,WEST")
        robot?.turnRight()
        assert(robot?.report()=="0,0,NORTH")
    }

    @Test
    fun turnLeftAndRight_validMove() {
        assert(robot?.report()=="0,0,NORTH")
        robot?.turnRight()
        assert(robot?.report()=="0,0,EAST")
        robot?.turnLeft()
        assert(robot?.report()=="0,0,NORTH")
    }

    @Test
    fun move_InvalidMoveIgnored() {
        assert(robot?.report()=="0,0,NORTH")
        robot?.move()
        assert(robot?.report()=="0,1,NORTH")
        robot?.turnLeft()
        assert(robot?.report()=="0,1,WEST")
        robot?.move()
        assert(robot?.report()=="0,1,WEST")
        robot?.turnLeft()
        assert(robot?.report()=="0,1,SOUTH")
        robot?.move()
        assert(robot?.report()=="0,0,SOUTH")
        robot?.turnLeft()
        assert(robot?.report()=="0,0,EAST")
        robot?.move()
        robot?.move()
        robot?.move()
        assert(robot?.report()=="3,0,EAST")
    }

    @After
    fun cleanUp() {
        robot = null
    }
}