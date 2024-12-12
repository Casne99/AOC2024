List<String> input = new File('in.txt').readLines()

int ans = 0

(1..<input.size() - 1).each { y ->
    (1..<input[y].size() - 1).each { x ->
        if (matches(getX(input, y, x))) ans++
    }
}

println(ans)


static List<String> getX(List<String> grid, int y, int x) {
    return [grid[y + 1][x - 1] + grid[y][x] + grid[y - 1][x + 1],
            grid[y - 1][x - 1] + grid[y][x] + grid[y + 1][x + 1]]
}

static boolean matches(List<String> X) {
    return X.every { it -> it == 'MAS' || it == 'SAM' }
}