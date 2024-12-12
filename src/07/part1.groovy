def input = new File('in.txt').readLines()

long ans = 0
for (String line : input) {
    def splitted = line.split(': ')
    def res = splitted[0] as long
    def operands = Arrays.asList(splitted[1].split(' ')).collect { it -> it as long }
    if (isValid(res, operands))
        ans += res
}

println(ans)


static boolean isValid(final long result, List<Long> numbers) {
    if (numbers.isEmpty())
        return false
    long n = numbers.remove(0)
    if (n == result && numbers.isEmpty()) return true
    if (numbers.isEmpty() || n > result) return false
    long next = numbers.remove(0)
    return isValid(result, [n + next] + numbers) ||
            isValid(result, [n * next] + numbers)
}



