
class TrieNode {
    var children = mutableMapOf<Char, TrieNode>()
    var isLeaf: Boolean = false
    var value: Int? = null
}

class Trie {
    var root: TrieNode = TrieNode()

    fun insert(key: String, value: Int) {
        var cursor = root
        key.forEach { c: Char ->
            cursor = cursor.children.getOrElse(c) {
                cursor.children[c] = TrieNode()
                cursor.children.getValue(c)
            }
        }
        cursor.isLeaf = true
        cursor.value = value
    }

    fun find(key: String): Int? {
        var cursor = root
        key.forEach { c: Char ->
            cursor = when (val next = cursor.children[c]) {
                null -> return null
                else -> next
            }
        }
        return cursor.value
    }
}

val fromLeft = arrayOf(
    Pair("one", 1),
    Pair("two", 2),
    Pair("three", 3),
    Pair("four", 4),
    Pair("five", 5),
    Pair("six", 6),
    Pair("seven" ,7),
    Pair("eight" ,8),
    Pair("nine", 9),
)
// o t f s e n
// [
//   'o' -> 'n' -> 'e' = 1,
//   'n' -> 'i' -> 'n' -> 'e' => 9,
//   'e' -> 'i' -> 'g' -> 'h' -> 't' = 8,
//   't' -> [
//     'w' -> 'o' = 2,
//     'h' -> 'r' -> 'e' -> 'e' = 3
//   ],
//   'f' -> [
//     'o' -> 'u' -> 'r' = 4,
//     'i' -> 'v' -> 'e' = 5,
//   ],
//   's' -> [
//     'i' -> 'x' = 6,
//     'e' -> 'v' -> 'e' -> 'n' = 7
//   ]

val fromRight = arrayOf(
    Pair("eno", 1),
    Pair("owt", 2),
    Pair("eerht", 3),
    Pair("ruof", 4),
    Pair("evif", 5),
    Pair("xis", 6),
    Pair("neves", 7),
    Pair("thgie", 8),
    Pair("enin", 9),
)
// e o r x n t

fun scooch(calibration: String): Int {
    val scooch: CharArray = calibration.toCharArray()
    var left = 0
    while (!scooch[left].isDigit()) { left++ }
    var right = scooch.size - 1
    while (!scooch[right].isDigit()) { right-- }

    val result = scooch[left].digitToInt() * 10 + scooch[right].digitToInt()

    return result
}

fun partOne() {
    val brations = mutableListOf<Int>()
    generateSequence(::readlnOrNull).forEach { line: String ->
        print(line.toCharArray())
        print("  <=>  ")
        val res: Int = scooch(line)
        brations.add(res)
        print(res)
        print("\n")
    }
    print(brations.sum())
    print("\n")
}

fun main() {
    var store = Trie()
    store.insert("two", 2)
    store.insert("three", 3)
    println(store.find("two"))
    println(store.find("three"))
}

