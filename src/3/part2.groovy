def lines = new File('in.txt').readLines()

def ans = 0

boolean enabled = true
for (def match : (lines.join() =~ /mul\((\d+),(\d+)\)|do\(\)|don't\(\)/)) {
    if (match[0] == 'do()') {
        enabled = true
    }
    if (match[0] == "don't()") {
        enabled = false
    }
    if (enabled && match[1] != null) {
        println("multyplying ${match[1]} and ${match[2]}")
        ans += (match[1] as int) * (match[2] as int)
    }
}


println(ans)