import groovy.transform.Immutable

def input = new File('in.txt').readLines().reverse()
def antennas = [] as List<Antenna>
for (int y = 0; y < input.size(); y++) {
    for (int x = 0; x < input[y].size(); x++) {
        if (input[y][x] != '.')
            antennas.add(new Antenna(input[y][x] as char, y, x))
    }
}

def groupedByFrequency = antennas.groupBy { it.freq }
def antiNodes = [] as HashSet<Coordinate>

groupedByFrequency.each { _, group ->
    group.each { antenna1 ->
        group.each { antenna2 ->
            def calculated = antenna1.findAntiNodes(antenna2, input.size(), input[0].size())
            antiNodes.addAll(calculated ?: [])
        }
    }
}

println(antiNodes.size())

@Immutable
class Antenna {
    char freq
    int y
    int x

    List<Coordinate> findAntiNodes(final Antenna other, int ylim, int xlim) {
        assert freq == other.freq
        if (this == other)
            return null

        def (xrange, yrange) = [0..<xlim, 0..<ylim]
        def (deltax, deltay) = [x - other.x, y - other.y]

        int mul = 0
        def res = []

        if (y + deltay * mul in yrange && x + deltax * mul in xrange) {
            while (y + deltay * mul in yrange && x + deltax * mul in xrange) {
                res.add(new Coordinate(y + deltay * mul, x + deltax * mul))
                mul++
            }
            return res
        }
        return null
    }
}

@Immutable
class Coordinate {
    int y
    int x
}
