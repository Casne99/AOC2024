def lines = new File('in.txt').readLines()

def ans = 0

def matches = (lines.join() =~ /mul\((\d+),(\d+)\)/).collect { match ->
    println(match)
    ans += ((match[1] as int) * (match[2] as int))
}

println(ans)