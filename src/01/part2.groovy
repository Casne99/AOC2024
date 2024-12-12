def lists = new File('in.txt').readLines().collect { line ->
    line.split('\\s+').collect { it as int }
}.transpose()

def counts = lists[1].countBy { it }

def ans = lists[0].sum { n ->
    n * (counts[n] ?: 0)
}

println("Answer is ${ans}")
