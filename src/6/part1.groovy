List<List<String>> grid = new File('in.txt').readLines().reverse()

Solver solver = new Solver(grid)
print(solver.solve())


class Solver {

    List<List<String>> area
    Operator operator


    Solver(List<List<String>> area) {
        this.area = area
        def found = false
        for (int y = 0; y < area.size(); y++) {
            if (area[y].contains('^')) {
                this.operator = new Operator(area[y].indexOf('^'), y);
                found = true
            }
        }
        if (!found) {
            throw new IllegalArgumentException('No operator found')
        }
    }


    int solve() {
        def visited = [[operator.curr_x, operator.curr_y]] as HashSet
        while (operator.peek() != null) {
            String next = operator.peek()
            if (next != '.' && next != '^')
                operator.turnRight()
            operator.move()
            visited.add([operator.curr_x, operator.curr_y])
        }
        return visited.size()
    }


    class Operator {

        static final actions = [
                (Strategy.GO_UP)   : { int curr_x, int curr_y -> [curr_x, curr_y + 1] },
                (Strategy.GO_RIGHT): { int curr_x, int curr_y -> [curr_x + 1, curr_y] },
                (Strategy.GO_DOWN) : { int curr_x, int curr_y -> [curr_x, curr_y - 1] },
                (Strategy.GO_LEFT) : { int curr_x, int curr_y -> [curr_x - 1, curr_y] }
        ]

        private Strategy currStrategy = Strategy.GO_UP

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
            while (peek() != '.') {
                currStrategy = directions[(currStrategy.ordinal() + 1) % directions.length]
            }
        }
    }


    enum Strategy {
        GO_UP,
        GO_RIGHT,
        GO_DOWN,
        GO_LEFT
    }
}