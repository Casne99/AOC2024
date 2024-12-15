import groovy.transform.Immutable

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
def antiNodes = [] as HashSet<Coordinate>

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

@Immutable
class Antenna {

    char freq
    int y
    int x

    Coordinate findAntiNode(final Antenna other, final int ylim, final int xlim) {
        if (this == other)
            return null

        def (deltax, deltay) = [x - other.x, y - other.y]

        if (y + deltay in 0..<ylim && x + deltax in 0..<xlim)
            return new Coordinate(y + deltay, x + deltax)

        return null
    }
}

@Immutable
class Coordinate {
    int y
    int x
}
