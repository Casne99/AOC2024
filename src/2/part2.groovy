def sequences = new File('in.txt').readLines().collect {
    sequence -> sequence.split(' ').collect { it as int }
}

int ans = 0

for (List<Integer> sequence : sequences) {
    if (isValid(sequence) || (s(sequence).count { it -> isValid(it) } > 0)) {
        ans++
    }
}

println(ans)

private static boolean isValid(List<Integer> sequence) {
    for (int i = 0; i < sequence.size() - 1; i++) {
        def mod = mod(sequence[i] - sequence[i + 1])
        if (mod > 3 || mod < 1 || !isSorted(sequence))
            return false
    }
    return true
}

static int mod(final int i) {
    return i > 0 ? i : -i
}

static boolean isSorted(List<Integer> l) {
    def ans = true
    boolean ascending = l[0] < l[1]
    for (int i = 0; i < l.size() - 1; i++) {
        if (ascending && (l[i] > l[i + 1])) {
            ans = false
        }
        if (!ascending && (l[i + 1] > l[i]))
            ans = false
    }
    return ans
}

static Set<List<Integer>> s(List<Integer> input) {
    def res = [] as Set<List<Integer>>
    (0..<input.size()).each { i ->
        res.add((0..<input.size()).findAll { it != i }.collect { input[it] })
    }
    return res
}


