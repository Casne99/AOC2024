List<String> input = new File('in.txt').readLines().reverse()
def ans = 0
def full = getFullInput(input)
for (String s : full) {
    ans += s.count('XMAS')
}

println(ans)


static List<String> getFullInput(List<String> input) {
    def res = []
    for (String s : input) {
        res.add(s)
        res.add(s.reverse())
    }

    for (int x = 0; x < input[0].size(); x++) {
        StringBuilder sb = new StringBuilder()
        for (int y = 0; y < input.size(); y++) {
            sb.append(input[y][x])
        }
        String s = sb.toString()
        res.add(s)
        res.add(s.reverse())
    }


    for (int sx = 0; sx < input[0].size(); sx++) {
        int y = 0
        StringBuilder sb = new StringBuilder()
        for (int delta = 0; delta < input.size() - sx; delta++) {
            sb.append(input[y + delta][sx + delta])
        }
        res.add(sb.toString())
        res.add(sb.toString().reverse())
    }

    for (int sy = 1; sy < input[0].size(); sy++) {
        int x = 0
        StringBuilder sb = new StringBuilder()
        for (int delta = 0; delta < input.size() - sy; delta++) {
            sb.append(input[sy + delta][x + delta])
        }
        res.add(sb.toString())
        res.add(sb.toString().reverse())
    }


    for (int sx = 0; sx < input[0].size(); sx++) {
        int y = 0
        StringBuilder sb = new StringBuilder()
        for (int delta = 0; delta < sx + 1; delta++) {
            sb.append(input[y + delta][sx - delta])
        }
        res.add(sb.toString())
        res.add(sb.toString().reverse())
    }

    for (int sy = 1; sy < input[0].size(); sy++) {
        int x = input[0].size() - 1
        StringBuilder sb = new StringBuilder()
        for (int delta = 0; delta < x - sy + 1; delta++) {
            sb.append(input[sy + delta][x - delta])
        }
        res.add(sb.toString())
        res.add(sb.toString().reverse())
    }


    return res
}