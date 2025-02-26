import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
    // Задача 1: Четное или нечетное число
    // val input = readln().toInt()
    // if (input % 2 == 0) {
    // println("Число четное")
    // } else {
    // println("Число нечетное")
    // }

    // Задача 2: Минимальное из трех чисел
    // val num1 = readln().toInt()
    // val num2 = readln().toInt()
    // val num3 = readln().toInt()
    // val min = minOf(num1, num2, num3)
    // println(min)

    // Задача 3: Таблица умножения
    // for (i in 0..10) {
    // println("${i * 5}")
    // }

    // Задача 4: Сумма чисел от 1 до N
    // println("Введите число: ")
    // val n = readln().toInt()
    // var sum = 0
    // for (i in 1..n) {
    // sum += i
    // }
    // println(sum)

    // Задача 5: Число Фибоначчи
    // val n = readln().toInt()
    // val fibonacciSequence = mutableListOf()
    //
    // if (n >= 1) fibonacciSequence.add(0)
    // if (n >= 2) fibonacciSequence.add(1)
    //
    // for (i in 2..< n) {
    // val nextNumber = fibonacciSequence[i - 1] + fibonacciSequence[i - 2]
    // fibonacciSequence.add(nextNumber)
    // }
    //
    // println("Первые $n чисел Фибоначчи: $fibonacciSequence")

    // Задача 6: Проверка простого числа
//    print("Введите целое число: ")
//    val number = readln().toInt()
//
//    if (number > 1) {
//        var isPrime = true
//
//        if (number == 2) {
//            isPrime = true
//        } else if (number % 2 == 0) {
//            isPrime = false
//        } else {
//            val sqrt = sqrt(number.toDouble()).toInt()
//            for (i in 3..sqrt step 2) {
//                if (number % i == 0) {
//                    isPrime = false
//                    break
//                }
//            }
//        }
//
//        if (isPrime) {
//            println("$number является простым числом.")
//        } else {
//            println("$number не является простым числом.")
//        }
//    } else {
//        println("Пожалуйста, введите корректное целое число больше 1.")
//    }

    // Задача 7: Обратный порядок чисел
    // print("Введите число n: ")
    // val n = readln().toInt()
    // val narray = mutableListOf()
    // for (i in 1 .. n) {
    // print("Введите число $i: ")
    // val number = readln().toInt()
    // narray.add(number)
    // }
    // narray.reverse()
    // for (num in narray) {
    // println(num)
    // }

    // Задача 8: Сумма четных чисел
    // val a = readln().toInt()
    // val b = readln().toInt()
    //
    // val start = minOf(a, b)
    // val end = maxOf(a, b)
    //
    // var sum = 0
    //
    // for (i in start .. end) {
    // if (i % 2 == 0) {
    // sum += i
    // }
    // }
    //
    // println("Сумма четных чисел в диапазоне от $a до $b: $sum")

    // Задача 9: Реверс строки
    // val string = readln()
    // println(string.reversed())

    // Задача 10: Количество цифр в числе
    // print("Введите целое число: ")
    // val number = readln().toInt()
    //
    // val numberOfDigits = number.toString().length
    // print("Количество разрядов в числе $number: $numberOfDigits")

    // Задача 11: Факториал числа
    // print("Введите целое число N: ")
    // val n = readln().toInt()
    //
    // var factorial: Long = 1
    // for (i in 2..n) {
    // factorial *= i
    // }
    //
    // println("Факториал числа $n: $factorial")

    // Задача 12: Сумма цифр числа
    // var number = readln().toInt()
    // number = abs(number)
    //
    // var sum = 0
    //
    // while (number > 0) {
    // sum += number % 10
    // number /= 10
    // }
    //
    // println("Сумма цифр числа $number: $sum")

    // Задача 13: Палиндром
//    print("Введите строку для проверки на палиндром: ")
//    val input = readln()
//    val reversedInput = input.reversed()
//    if (input == reversedInput) {
//        println("Строка является палиндромом")
//    } else {
//        println("Строка не является палиндромом")
//    }

//    Задача 14: Найти максимальное число в массиве
//    print("Введите размер массива: ")
//    val n = readln().toInt()
//    val array = IntArray(n)
//    for (i in 0 ..< n) {
//        println("Элемент под индексом $i:")
//        array[i] = readln().toInt()
//    }
//    val maxElement = array.max()
//    println("Максимальный элемент в массиве: $maxElement")

    // Задача 15: Сумма всех элементов массива
//    print("Введите размер массива: ")
//    val n = readln().toInt()
//    val array = IntArray(n)
//    for (i in 0 ..< n) {
//        println("Элемент под индексом $i:")
//        array[i] = readln().toInt()
//    }
//    val sumOfElements = array.sum()
//    println("Сумма элементов массива: $sumOfElements")

    // Задача 16: Количество положительных и отрицательных чисел
//    print("Введите размер массива: ")
//    val n = readln().toInt()
//    val array = IntArray(n)
//
//    for (i in 0 ..< n) {
//        println("Элемент под индексом $i: ")
//        array[i] = readln().toInt()
//    }
//
//    val positiveNumbers = array.count { it >= 0 }
//    val negativeNumbers = array.count { it < 0 }
//
//    println("Количество положительных элементов: $positiveNumbers")
//    println("Количество отрицательных элементов: $negativeNumbers")

    // Задача 17: Простые числа в диапазоне
//    print("Введите начало диапазона: ")
//    val a = readln().toInt()
//    print("Введите конец диапазона: ")
//    val b = readln().toInt()
//
//    val start = minOf(a, b)
//    val end = maxOf(a, b)
//    val arrayOfPrimeNumbers = mutableListOf<Int>()
//
//    for (number in start..end) {
//        if (number > 1) {
//            var isPrime = true
//
//            if (number == 2) {
//                isPrime = true
//            } else if (number % 2 == 0) {
//                isPrime = false
//            } else {
//                val sqrt = sqrt(number.toDouble()).toInt()
//                for (i in 3..sqrt step 2) {
//                    if (number % i == 0) {
//                        isPrime = false
//                        break
//                    }
//                }
//            }
//
//            if (isPrime) {
//                arrayOfPrimeNumbers.add(number)
//            }
//        }
//    }
//    println("Простые числа в диапазоне от $start до $end: $arrayOfPrimeNumbers")

    // Задача 18: Подсчет гласных и согласных в строке
//    println("Введите строку: ")
//    val input = readln()
//
//    var vowelCount = 0
//    var consonantCount = 0
//
//    val vowels = setOf(
//        'а', 'е', 'ё', 'и', 'о', 'у', 'ы', 'э', 'ю'
//    )
//
//    for (char in input) {
//        if (char.isLetter()) {
//            when {
//                char in vowels -> vowelCount++
//                else -> consonantCount++
//            }
//        }
//    }
//
//    println("Количество гласных букв: $vowelCount")
//    println("Количество согласных букв: $consonantCount")

    // Задача 19: Перестановка слов в строке
//    println("Введите строку, состоящую из нескольких слов:")
//    val input = readLine()!!
//
//    val words = input.split(" ")
//
//    val reversedWords = words.reversed()
//    println("Слова в обратном порядке: ${reversedWords.joinToString(" ")}")

    // Задача 20: Число Армстронга
//    println("Введите целое число:")
//    val input = readln()
//    val number = input.toInt()
//    val digits = number.toString().map { it.toString().toInt() }
//    val numberOfDigits = digits.size
//    val armstrongSum = digits.sumOf { it.toDouble().pow(numberOfDigits) }.toInt()
//
//    if (armstrongSum == number) {
//        println("$number является числом Армстронга.")
//    } else {
//        println("$number не является числом Армстронга.")
//    }
}
