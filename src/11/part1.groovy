def input = new File('in.txt').readLines().get(0).split(' ').collect { it -> it as int }

25.times {
    input = input.collect { a -> step(a) }.flatten()
            .collect { it as long }
}

println(input.size())


static List<Long> step(final long input) {
    if (input == 0)
        return [1]

    int middleIndex = "$input".length() / 2

    if ("$input".length() % 2 == 0)
        return ["$input".substring(0, middleIndex) as long, "$input".substring(middleIndex) as long]

    return [input * 2024]
}