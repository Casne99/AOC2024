List<String> grid = new File('in.txt').readLines().reverse()


def ans = 0


for (int y = 0; y < grid.size(); y++) {
    String original = grid.get(y)
    for (String s : putObstacles(grid.get(y))) {
        grid.set(y, s)
        Solver solver = new Solver(grid)
        if (solver.loops()) {
            ans++
        }
        grid.set(y, original)
    }
}


println(ans)

class Solver {

    List<String> area
    Operator operator


    Solver(List<String> area) {
        this.area = area
        def found = false
        for (int y = 0; y < area.size(); y++) {
            if (area[y].contains('^')) {
                this.operator = new Operator(area[y].indexOf('^'), y)
                found = true
            }
        }
        if (!found) {
            throw new IllegalArgumentException('No operator found')
        }
    }


    boolean loops() {
        HashSet<Triple> visited = [new Triple(operator.currStrategy, operator.curr_x, operator.curr_y)] as HashSet<Triple>
        while (operator.peek() != null) {
            String next = operator.peek()
            if (next != '.' && next != '^') {
                def directions = Strategy.values()
                while (operator.peek() != '.' && operator.peek() != '^' && operator.peek() != null) {
                    operator.currStrategy = directions[(operator.currStrategy.ordinal() + 1) % directions.length]
                    visited.add(new Triple(operator.currStrategy, operator.curr_x, operator.curr_y))
                }
            }
            operator.move()
            if (visited.contains(new Triple(operator.currStrategy, operator.curr_x, operator.curr_y)))
                return true
            visited.add(new Triple(operator.currStrategy, operator.curr_x, operator.curr_y))
        }
        return false
    }


    class Operator {

        static final actions = [
                (Strategy.GO_UP)   : { int curr_x, int curr_y -> [curr_x, curr_y + 1] },
                (Strategy.GO_RIGHT): { int curr_x, int curr_y -> [curr_x + 1, curr_y] },
                (Strategy.GO_DOWN) : { int curr_x, int curr_y -> [curr_x, curr_y - 1] },
                (Strategy.GO_LEFT) : { int curr_x, int curr_y -> [curr_x - 1, curr_y] }
        ]

        Strategy currStrategy = Strategy.GO_UP

        int curr_x
        int curr_y

        Operator(int x, int y) {
            curr_x = x
            curr_y = y
        }

        String peek() {
            def (int x, int y) = getNextCoords()
            return x < area[0].size() && y < area.size() && x > -1 && y > -1 ? area[y][x] : null
        }

        void move() {
            def (int x, int y) = getNextCoords()
            curr_x = x
            curr_y = y
        }

        private List getNextCoords() {
            def where = actions[currStrategy]
            def nextCoords = where.call(curr_x, curr_y)
            def y = nextCoords[1]
            def x = nextCoords[0]
            return [x, y]
        }

        void turnRight() {
            def directions = Strategy.values()
            while (peek() != '.' && peek() != '^') {
                currStrategy = directions[(currStrategy.ordinal() + 1) % directions.length]
            }
        }
    }
}

enum Strategy {
    GO_UP,
    GO_RIGHT,
    GO_DOWN,
    GO_LEFT
}


static String putObstacle(String s, int index) {
    StringBuilder sb = new StringBuilder()
    for (int i = 0; i < s.size(); i++) {
        if (i == index)
            sb.append('O')
        else
            sb.append(s.charAt(i))
    }
    return sb.toString()
}

static List<String> putObstacles(String s) {
    def res = []
    for (int i = 0; i < s.size(); i++) {
        if (s[i] == '.')
            res.add(putObstacle(s, i))
    }
    return res
}

class Triple {

    Strategy direction
    int x
    int y

    Triple(Strategy strategy, int x, int y) {
        this.direction = strategy
        this.x = x
        this.y = y
    }

    @Override
    boolean equals(Object obj) {
        if (obj == null) return false
        if (!(obj instanceof Triple)) return false
        Triple o = (Triple) obj
        return direction == o.direction && x == o.x && y == o.y
    }

    @Override
    int hashCode() {
        return Objects.hash(direction, x, y)
    }

}