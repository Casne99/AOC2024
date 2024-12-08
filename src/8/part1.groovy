def input = new File('in.txt').readLines().reverse()
def antennas = [] as List<Antenna>

for (int y = 0; y < input.size(); y++) {
    for (int x = 0; x < input[y].size(); x++) {
        if (input[y][x] != '.')
            antennas.add(new Antenna(input[y][x] as char, y, x))
    }
}

int ylim = input.size()
int xlim = input[0].size()

def groupedByFrequency = antennas.groupBy { it.freq }
def antiNodes = [] as HashSet

groupedByFrequency.each { _, group ->
    group.each { antenna1 ->
        group.each { antenna2 ->
            def antiNode = antenna1.findAntiNode(antenna2, ylim, xlim)
            if (antiNode != null)
                antiNodes.add(antiNode)
        }
    }
}

println(antiNodes.size())


class Antenna {

    public final char freq
    public final int y
    public final int x

    Antenna(char freq, int y, int x) {
        this.freq = freq
        this.y = y
        this.x = x
    }

    @Override
    boolean equals(Object obj) {
        if (obj == null) return false
        if (!(obj instanceof Antenna)) return false
        Antenna other = (Antenna) obj
        return freq == other.freq && y == other.y && x == other.x
    }

    @Override
    int hashCode() {
        return Objects.hash(freq, x, y)
    }


    List<Integer> findAntiNode(final Antenna other, int ylim, int xlim) {
        if (this == other)
            return null

        def (deltax, deltay) = [x - other.x, y - other.y]

        if (y + deltay in 0..<ylim && x + deltax in 0..<xlim)
            return [y + deltay, x + deltax]

        return null
    }
}