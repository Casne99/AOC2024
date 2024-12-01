import groovyjarjarantlr4.v4.runtime.misc.Tuple2

def input = new File('in.txt').readLines().collect {
    def splitted = it.split('\\s+')
    new Tuple2<>(splitted[0] as int, splitted[1] as int)
}

def l = input.collect { it.item1 }
def r = input.collect { it.item2 }


Map<Integer, Integer> counts = [:]
for (int n : r) {
    counts[n] = counts[n] == null ? 1 : counts[n] + 1
}

def ans = 0
for (int n : l) {
    println(n + " " + counts.getOrDefault(n, 0))
    ans += n * counts.getOrDefault(n, 0)
}

println(ans)