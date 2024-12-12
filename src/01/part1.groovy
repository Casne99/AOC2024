def lists = new File('in.txt').readLines().collect {
    def splitted = it.split('\\s+')
    [splitted[0] as int, splitted[1] as int]
}.transpose().collect { it.toSorted() }


def ans = (0..<lists[0].size()).collect {
    mod(lists[0][it] - lists[1][it])
}.sum()

println("Answer is ${ans}")

static int mod(final int i) {
    i > 0 ? i : -i
}