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
            def antiNode = antenna1.findAntiNodes(antenna2, input.size(), input[0].size())
            if (antiNode != null)
                antiNode.each { it -> antiNodes.add(it)}
        }
    }
}


groups.each { _, group -> if (group.size() > 1)
    group.each { antenna -> antiNodes.add([antenna.y, antenna.x])}
}

println(antiNodes)
int ans = antiNodes.size()




println(ans)

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

    List<List<Integer>> findAntiNodes(final Antenna other, int ylim, int xlim) {
        if (this == other)
            return null

        int deltax = x - other.x
        int deltay = y - other.y

        int mul = 0
        def res = []
        if (deltay == 0) {
            while (x + deltax * mul > -1 && x + deltax * mul < xlim) {
                res.add([y, x + deltax * mul])
                mul++
            }
            return res
        }

        if (deltax == 0)
            while (y + deltay * mul > -1 && y + deltay * mul < ylim) {
                res.add([y + deltay * mul, x])
                mul++
            }

        if (y + deltay > -1 && y + deltay < ylim && x + deltax > -1 && x + deltax < xlim) {
            while (y + deltay * mul > -1 && y + deltay * mul < ylim && x + deltax * mul > -1 && x + deltax * mul < xlim) {
                res.add([y + deltay * mul, x + deltax * mul])
                mul++
            }
            return res
        } else return null
    }
}