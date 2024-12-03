def lines = new File('in.txt').readLines()

boolean enabled = true
def setMode = [
        "do()"   : { enabled = true },
        "don't()": { enabled = false }
]

def ans = (lines.join() =~ /mul\((\d+),(\d+)\)|do\(\)|don't\(\)/).collect { match ->
    setMode[match[0]]?.call()
    enabled && match[1] != null ? (match[1] as int) * (match[2] as int) : 0
}.sum()

println("Answer is ${ans}")
