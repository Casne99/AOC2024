List<Long> input = new File('in.txt').readLines().get(0).split(' ')
        .collect { it -> it as long }


Map<Long, List<Long>> cache = [:]
println("here")

long ans = 0

3.times {
    def e = input.findAll { e ->  cache[e as long] != null }
    int s = input.size()
    e.each { l ->
        ans += (cache[l as long] * input.count { (it as long) == (l as long) })
    }
    input.removeAll(e)
    println("Filtered ${e.size()} elems out of ${s}")
    println("${new HashSet<>(input).size()}")
    25.times {
        input = input.collect { a -> step(a) }.flatten()
    }
    println('qui')
    List<Long> mostCommons = mostCommons(input)
    updateCache(cache, mostCommons)
    println("MostCommons: $mostCommons | found: ${input.count { it in mostCommons }} times")

}
println(input.size())

println(ans + input.size())


static List<Long> step(final long input) {
    if (input == 0)
        return [1]

    int middleIndex = "$input".length() / 2

    if ("$input".length() % 2 == 0)
        return ["$input".substring(0, middleIndex) as long, "$input".substring(middleIndex) as long]

    return [input * 2024]
}


static void updateCache(Map<Long, Long> cache, List<Long> commons) {

    commons.findAll { cache[it as long] == null }.each { long n ->
        def start = [n] as List<Long>
        25.times { start = start.collect { elem -> step(elem) }.flatten() }
        cache[n as long] = start.size() as long
    }
}


static List<Long> mostCommons(List<Long> list) {
    return list.countBy { it }
            .entrySet()
            .sort { it.value }
            .takeRight(5000)
            .collect { it.key }

}
