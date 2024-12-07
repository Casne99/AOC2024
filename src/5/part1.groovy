def input = new File('in.txt').readLines()

int ans = 0

String pageOrderings = getPageOrderings(input)
Comparator<Integer> pageComparator = new PageComparator(pageOrderings)

getLines(input).each { list ->
    def copy = copy(list)
    copy.sort(pageComparator)
    if (copy == list)
        ans += copy[(list.size() - 1) / 2]
}

println(ans)


class PageComparator implements Comparator<Integer> {

    private final List<List<Integer>> orders = []

    PageComparator(final String orders) {
        orders.split('\n').each { line ->
            def numbers = Arrays.asList(line.split('\\|'))
            this.orders.add([numbers[0] as int, numbers[1] as int])
        }
    }

    @Override
    int compare(Integer o1, Integer o2) {
        if (orders.contains([o1, o2]))
            return -1
        else if (orders.contains([o2, o1]))
            return 1
        return 0
    }
}

static String getPageOrderings(final List<String> fileContent) {
    StringBuilder sb = new StringBuilder()
    for (String s : fileContent) {
        if (s.isEmpty())
            break
        sb.append(s + '\n')
    }
    return sb.deleteCharAt(sb.size() - 1).toString()
}

static List<List<Integer>> getLines(final List<String> fileContent) {
    def res = []
    int index = fileContent.indexOf('')
    for (int i = index + 1; i < fileContent.size(); i++) {
        res.add(fileContent[i].split(',').collect { number ->
            number as int
        })
    }
    res
}

static List<Integer> copy(final List<Integer> input) {
    def res = []
    input.each { res.add(it) }
    return res
}