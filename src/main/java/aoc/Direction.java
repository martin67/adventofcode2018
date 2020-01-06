package aoc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum Direction {
    North, South, East, West, Up, Right, Down, Left, NorthEast, NorthWest, SouthEast, SouthWest, Unknown;

    public Direction opposite() {
        Direction result;
        switch (this) {
            case North:
                result = Direction.South;
                break;
            case Up:
                result = Direction.Down;
                break;
            case East:
                result = Direction.West;
                break;
            case Right:
                result = Direction.Left;
                break;
            case South:
                result = Direction.North;
                break;
            case Down:
                result = Direction.Up;
                break;
            case West:
                result = Direction.East;
                break;
            case Left:
                result = Direction.Right;
                break;
            case NorthEast:
                result = Direction.SouthWest;
                break;
            case NorthWest:
                result = Direction.SouthEast;
                break;
            case SouthEast:
                result = Direction.NorthWest;
                break;
            case SouthWest:
                result = Direction.NorthEast;
                break;
            default:
                result = Direction.Unknown;
                break;
        }
        return result;
    }

    public Direction bounceWall(Direction wall) {
        Direction result;
        switch (this) {
            case NorthEast:
                if (wall == Direction.North) {
                    result = Direction.SouthEast;
                } else if (wall == Direction.East) {
                    result = Direction.NorthWest;
                } else {
                    log.error("Bounce from northeast, no wall");
                    result = Direction.Unknown;
                }
                break;
            case NorthWest:
                if (wall == Direction.North) {
                    result = Direction.SouthWest;
                } else if (wall == Direction.West) {
                    result = Direction.NorthEast;
                } else {
                    log.error("Bounce from northwest, no wall");
                    result = Direction.Unknown;
                }
                break;
            case SouthEast:
                if (wall == Direction.South) {
                    result = Direction.NorthEast;
                } else if (wall == Direction.East) {
                    result = Direction.SouthWest;
                } else {
                    log.error("Bounce from southeast, no wall");
                    result = Direction.Unknown;
                }
                break;
            case SouthWest:
                if (wall == Direction.South) {
                    result = Direction.NorthWest;
                } else if (wall == Direction.West) {
                    result = Direction.SouthEast;
                } else {
                    log.error("Bounce from southwest, no wall");
                    result = Direction.Unknown;
                }
                break;
            default:
                result = Direction.Unknown;
                break;
        }
        return result;
    }

    public Direction turn(Direction newDirection) {
        switch (this) {
            case North:
                switch (newDirection) {
                    case East:
                        return Right;
                    case West:
                        return Left;
                    case Left:
                        return West;
                    case Right:
                        return East;
                    default:
                        log.error("Wrong turn");
                        return Unknown;
                }
            case East:
                switch (newDirection) {
                    case South:
                        return Right;
                    case North:
                        return Left;
                    case Left:
                        return North;
                    case Right:
                        return South;
                    default:
                        log.error("Wrong turn");
                        return Unknown;
                }
            case South:
                switch (newDirection) {
                    case West:
                        return Right;
                    case East:
                        return Left;
                    case Left:
                        return East;
                    case Right:
                        return West;
                    default:
                        log.error("Wrong turn");
                        return Unknown;
                }
            case West:
                switch (newDirection) {
                    case North:
                        return Right;
                    case South:
                        return Left;
                    case Left:
                        return South;
                    case Right:
                        return North;
                    default:
                        log.error("Wrong turn");
                        return Unknown;
                }
            default:
                log.error("Illegal currentDirection");
                return Unknown;
        }
    }
}
