package aoc2019;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Slf4j
enum Direction {
    North, South, East, West, Up, Right, Down, Left, NorthEast, NorthWest, SouthEast, SouthWest, Unknown;

    Direction opposite() {
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

    Direction bounceWall(Direction wall) {
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

    Direction turn(Direction newDirection) {
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

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Position implements Comparable<Position> {
    int x;
    int y;

    Position(Position p) {
        x = p.x;
        y = p.y;
    }

    @Override
    public int compareTo(Position p) {
        if (y < p.y) {
            return -1;
        } else if (y > p.y) {
            return 1;
        } else return Integer.compare(x, p.x);
    }

    int distance(Position p) {
        return Math.abs(x - p.x) + Math.abs(y - p.y);
    }

    Position adjacent(Direction direction) {
        Position adj = new Position(x, y);
        switch (direction) {
            case North:
            case Up:
                adj.y--;
                break;
            case East:
            case Right:
                adj.x++;
                break;
            case South:
            case Down:
                adj.y++;
                break;
            case West:
            case Left:
                adj.x--;
                break;
            case NorthEast:
                adj.x++;
                adj.y--;
                break;
            case NorthWest:
                adj.x--;
                adj.y--;
                break;
            case SouthEast:
                adj.x++;
                adj.y++;
                break;
            case SouthWest:
                adj.x--;
                adj.y++;
                break;
        }
        return adj;
    }

    @Deprecated
    Direction opposite(Direction direction) {
        Direction result;
        switch (direction) {
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

    @Deprecated
    Direction bounce(Direction direction) {
        Direction result;
        switch (direction) {
            case NorthEast:
                result = Direction.NorthWest;
                break;
            case NorthWest:
                result = Direction.NorthEast;
                break;
            case SouthEast:
                result = Direction.SouthWest;
                break;
            case SouthWest:
                result = Direction.SouthEast;
                break;
            default:
                result = Direction.Unknown;
                break;
        }
        return result;
    }


    Direction directionTo(Position position, boolean allowDiagonal) {
        if (position.x == x && position.y < y) {
            return Direction.North;
        } else if (allowDiagonal && position.x > x && position.y < y) {
            return Direction.NorthEast;
        } else if (position.x > x && position.y == y) {
            return Direction.East;
        } else if (allowDiagonal && position.x > x && position.y > y) {
            return Direction.SouthEast;
        } else if (position.x == x && position.y > y) {
            return Direction.South;
        } else if (allowDiagonal && position.x < x && position.y > y) {
            return Direction.SouthWest;
        } else if (position.x < x && position.y == y) {
            return Direction.West;
        } else if (allowDiagonal && position.x < x && position.y < y) {
            return Direction.NorthWest;
        } else {
            return Direction.Unknown;
        }
    }

    Position adjacent(char direction) {
        Position adj = new Position(x, y);
        switch (direction) {
            case 'N':
            case 'U':
                adj.y--;
                break;
            case 'E':
            case 'R':
                adj.x++;
                break;
            case 'S':
            case 'D':
                adj.y++;
                break;
            case 'W':
            case 'L':
                adj.x--;
                break;
        }
        return adj;
    }

    Set<Position> allAdjacent() {
        Set<Position> adjacent = new HashSet<>();
        adjacent.add(new Position(x, y - 1)); // up or north
        adjacent.add(new Position(x - 1, y)); // left or west
        adjacent.add(new Position(x + 1, y)); // right or east
        adjacent.add(new Position(x, y + 1)); // down or south
        return adjacent;
    }

    Set<Position> adjacentDiagonal(Direction dir) {
        Set<Position> adjacent = new HashSet<>();
        switch (dir) {
            case NorthEast:
                adjacent.add(new Position(this.adjacent(Direction.North)));
                adjacent.add(new Position(this.adjacent(Direction.East)));
                break;
            case NorthWest:
                adjacent.add(new Position(this.adjacent(Direction.North)));
                adjacent.add(new Position(this.adjacent(Direction.West)));
                break;
            case SouthEast:
                adjacent.add(new Position(this.adjacent(Direction.South)));
                adjacent.add(new Position(this.adjacent(Direction.East)));
                break;
            case SouthWest:
                adjacent.add(new Position(this.adjacent(Direction.South)));
                adjacent.add(new Position(this.adjacent(Direction.West)));
                break;
            default:
                log.error("Not a diagonal direction");
                break;
        }
        return adjacent;
    }
}