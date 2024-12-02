def sequences = new File('in.txt').readLines().collect {
    sequence -> sequence.split(' ').collect { it as int }
}

int ans = 0

for (List<Integer> sequence : sequences) {
    if (isValid(sequence))
        ans++
}

private static boolean isValid(final List<Integer> sequence) {
    for (int i = 0; i < sequence.size() - 1; i++) {
        def mod = mod(sequence[i] - sequence[i + 1])
        if (mod > 3 || mod < 1) {
            return false
        }
        if (!isSorted(sequence))
            return false
    }
    return true
}

println(ans)

static int mod(final int i) {
    return i > 0 ? i : -i
}

static boolean isSorted(final List<Integer> l) {
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