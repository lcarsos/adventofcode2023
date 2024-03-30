
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

    fun find(key: List<Char>): Int? {
        var cursor = root
        run breaking@ {
            key.forEach { c: Char ->
                cursor = when (val next = cursor.children[c]) {
                    null -> return null
                    else -> next
                }
                if (cursor.isLeaf) { return@breaking }
            }
        }
        return cursor.value
    }
}

class Challenge() {
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

    var leftTrie = Trie()
    var rightTrie = Trie()

    init {
        fromLeft.forEach { pair ->
            leftTrie.insert(pair.first, pair.second)
        }
        fromLeft.forEach { pair ->
            rightTrie.insert(pair.first.reversed(), pair.second)
        }
    }

    fun scooch(calibration: String): Int {
        val scooch: CharArray = calibration.toCharArray()
        var left = 0
        while (!scooch[left].isDigit()) { left++ }
        var right = scooch.size - 1
        while (!scooch[right].isDigit()) { right-- }

        val result = scooch[left].digitToInt() * 10 + scooch[right].digitToInt()

        return result
    }

    fun scoochWords(calibration: String): Int {
        val scooch: CharArray = calibration.toCharArray()
        val scoochR = scooch.reversed()

        var left = 0
        while (
            !scooch[left].isDigit() &&
            leftTrie.find(scooch.slice(left..<scooch.size)) == null
        ) {
            left++
        }
        val leftDigit: Int = if (scooch[left].isDigit()) {
            scooch[left].digitToInt()
        } else {
            leftTrie.find(scooch.slice(left..<scooch.size))!!
        }

        var right = 0
        while (
            !scoochR[right].isDigit() &&
            rightTrie.find(scoochR.slice(right..<scoochR.size)) == null
        ) {
            right++
        }
        val rightDigit: Int = if (scoochR[right].isDigit()) {
            scoochR[right].digitToInt()
        } else {
            rightTrie.find(scoochR.slice(right..<scoochR.size))!!
        }

        val result = leftDigit * 10 + rightDigit

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

    fun partTwo() {
        val brations = mutableListOf<Int>()
        generateSequence(::readlnOrNull).forEach { line: String ->
            print(line.toCharArray())
            print("  <=>  ")
            val res: Int = scoochWords(line)
            brations.add(res)
            print(res)
            print("\n")
        }
        print(brations.sum())
        print("\n")
    }

    fun foo() {
        val search = "4gbkshmmksfseven"
        val res = scoochWords(search)
        println(res)
    }

}

fun main() {
    //var right = Trie()
    //fromLeft.forEach { pair ->
    //    right.insert(pair.first.reversed(), pair.second)
    //}
    //val search = "4gbkshmmksfseven"
    //println("from 0: ${right.find(search.reversed().slice(0..< search.length))}")
    //println("from 3: ${right.find(search.reversed().slice(3..< search.length))}")

    val challenge = Challenge()
    challenge.partTwo()

    //println(right.find("owt"))
}

