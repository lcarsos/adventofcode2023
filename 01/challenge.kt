import java.io.File

fun scooch(calibration: String): Int {
    val scooch: Char[] = calibration.toCharArray()[0]
    val left = 0
    while (scooch[left].toInt()

fun main() {
    File("test-input.txt").forEachLine { line: String ->
        print(line.toCharArray()[0])
        print("\n")
    }
}

