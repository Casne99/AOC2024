import groovyjarjarantlr4.v4.runtime.misc.Tuple2

def input = new File('in.txt').readLines().collect {
    def splitted = it.split('\\s+')
    new Tuple2<>(splitted[0] as int, splitted[1] as int)
}

def l = input.collect { it.item1 }
def r = input.collect { it.item2 }
l.sort()
r.sort()

def ans = 0
for( int i = 0; i < l.size(); i++) {
    ans += mod(l[i] - r[i])
}

println(ans)

static int mod(final int i) {
    i > 0 ? i : -i
}