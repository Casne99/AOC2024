def lines = new File('in.txt').readLines()

def ans = (lines.join() =~ /mul\((\d+),(\d+)\)/).collect { match ->
    (match[1] as int) * (match[2] as int)
}.sum()

println("Answer is ${ans}")