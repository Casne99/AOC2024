def input = new File('in.txt').readLines().reverse()
def antennas = []
for (int y = 0; y < input.size(); y++) {
    for (int x = 0; x < input[y].size(); x++) {
        if (input[y][x] != '.')
            antennas.add(new Antenna(input[y][x] as char, y, x))
    }
}

def groups = antennas.groupBy { it.id }
def antiNodes = [] as HashSet

groups.each { _, group ->
    group.each { antenna1 ->
        group.each { antenna2 ->
            def antiNode = antenna1.findAntiNode(antenna2, input.size(), input[0].size())
            if (antiNode != null)
                antiNodes.add(antiNode)
        }
    }
}

println(antiNodes.size())



class Antenna {

    public final char id
    public final int y
    public final int x

    Antenna(char id, int y, int x) {
        this.id = id
        this.y = y
        this.x = x
    }

    @Override
    boolean equals(Object obj) {
        if (obj == null) return false
        if (!(obj instanceof Antenna)) return false
        Antenna other = (Antenna) obj
        return id == other.id && y == other.y && x == other.x
    }

    @Override
    int hashCode() {
        return Objects.hash(id, x, y)
    }

    @Override
    String toString() {
        return "ID: $id | X: $x | Y: $y"
    }

    List<List<Integer>> findAntiNode(final Antenna other, int ylim, int xlim) {
        if (this == other)
            return null

        int deltax = x - other.x
        int deltay = y - other.y

        def res = []
        if (deltay == 0)
            while (x + deltax > -1 && x + deltax < xlim) {
                res
            }
                // return [y, x + deltax]

        if (deltax == 0)
            if (y + deltay > -1 && y + deltay < ylim)
                return [y + deltay, x]
            else return null

        if (y + deltay > -1 && y + deltay < ylim && x + deltax > -1 && x + deltax < xlim)
            return [y + deltay, x + deltax]
        else return null

    }
}