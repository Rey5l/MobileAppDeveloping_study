import java.io.File

fun main() {
    println("Выберите режим (encrypt/decrypt/bruteforce): ")
    val mode = readln()
    println("Введите путь к файлу:")
    val inputPath = readln()
    println("Введите путь для сохранения результата:")
    val outputPath = readln()

    val shift = if (mode != "bruteforce") {
        println("Введите сдвиг (целое число): ")
        readln().toInt()
    } else 0

    CaesarCipher.processFile(inputPath, outputPath, shift, mode)
}

object CaesarCipher {
    private const val EN_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
    private const val RU_ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя"

    private var currentAlphabet = "en"


    private fun encrypt(text: String, shift: Int): String {
        return text.map { char -> shiftChar(char, shift) }.joinToString("")
    }

    private fun decrypt(text: String, shift: Int): String {
        return encrypt(text, -shift)
    }

    private fun shiftChar(char: Char, shift: Int): Char {
        return when (char) {
            in EN_ALPHABET -> {
                currentAlphabet = "en"
                shiftWithinAlphabet(char, shift, EN_ALPHABET)
            }

            in RU_ALPHABET -> {
                currentAlphabet = "ru"
                shiftWithinAlphabet(char, shift, RU_ALPHABET)
            }

            else -> char
        }
    }

    private fun shiftWithinAlphabet(char: Char, shift: Int, alphabet: String): Char {
        val index = alphabet.indexOf(char)
        val newIndex = (index + shift + alphabet.length) % alphabet.length
        return alphabet[newIndex]
    }

    private fun bruteForceDecrypt(text: String): List<String> {
        val result: List<String> = if (currentAlphabet == "ru") {
            (1..<RU_ALPHABET.length).map { shift ->
                "Shift $shift " + decrypt(text, shift)
            }
        } else {
            (1..<EN_ALPHABET.length).map { shift ->
                "Shift $shift " + decrypt(text, shift)
            }
        }
        return result
    }

    fun processFile(inputPath: String, outputPath: String, shift: Int, mode: String) {
        val inputFile = File(inputPath)
        if (!inputFile.exists()) {
            println("Ошибка: файл не найден.")
            return
        }

        val content = inputFile.readText()
        val result = when (mode) {
            "encrypt" -> encrypt(content, shift)
            "decrypt" -> decrypt(content, shift)
            "bruteforce" -> bruteForceDecrypt(content).joinToString("\n")
            else -> {
                println("Ошибка: Неизвестный режим.")
                return
            }
        }
        File(outputPath).writeText(result)
        println("Операция $mode завершена. Результат записан в $outputPath")
    }
}