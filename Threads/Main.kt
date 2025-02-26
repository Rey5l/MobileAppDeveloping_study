import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Semaphore
import kotlin.random.Random



//    // task 1

//var counter = 0
//val lock = ReentrantLock()
//
//fun main() {
//    runBlocking {
//        val jobs = List(5) {
//            launch(Dispatchers.Default) {
//                repeat(1000) {
//                    lock.lock()
//                    try {
//                        counter++
//                    } finally {
//                        lock.unlock()
//                    }
//                }
//            }
//        }
//        jobs.joinAll()
//        println("Final counter value: $counter")
//    }
//}

//    // task 2
//val numberList = CopyOnWriteArrayList<Int>()
//
//fun main() = runBlocking {
//    val jobs = List(10) {
//        launch(Dispatchers.Default) {
//            for (i in 1 .. 100) {
//                numberList.add(i)
//            }
//        }
//    }
//    jobs.joinAll()
//    println(numberList.size)
//}

//    // task 3

//fun main() {
//    val executor = Executors.newFixedThreadPool(4)
//
//    repeat(20) { taskNumber ->
//        executor.execute {
//            println("Thread: ${Thread.currentThread().name}, Task: $taskNumber")
//        }
//    }
//
//    executor.shutdown()
//}

//    // task 4

//class BankAccount(var balance: Int) {
//    private val lock = ReentrantLock()
//
//    fun transfer(to: BankAccount, amount: Int) {
//        lock.lock()
//        try {
//            if (balance >= amount) {
//                balance -= amount
//                to.deposit(amount)
//                println("Transferred $amount from $this to $to")
//            }
//        } finally {
//            lock.unlock()
//        }
//    }
//
//    fun deposit(amount: Int) {
//        balance += amount
//    }
//}
//
//fun main() = runBlocking {
//    val accounts = List(3) { BankAccount(1000) }
//    val jobs = List(10) {
//        launch(Dispatchers.Default) {
//            repeat(5) {
//                val from = accounts.random()
//                val to = accounts.random()
//                val amount = (10..500).random()
//                from.transfer(to, amount)
//            }
//        }
//    }
//    jobs.joinAll()
//    accounts.forEachIndexed { index, account -> println("Account $index balance: ${account.balance}") }
//}


//    // task 5
//fun main() = runBlocking {
//    val barrier = CyclicBarrier(5) {
//        println("All threads reached the barrier. Proceeding to the next phase.")
//    }
//
//    val jobs = List(5) { index ->
//        launch(Dispatchers.Default) {
//            println("Thread $index is working")
//            delay((500..1500).random().toLong())
//            println("Thread $index reached the barrier")
//            barrier.await()
//            println("Thread $index proceeds to the next phase")
//        }
//    }
//
//    jobs.joinAll()
//}

//    // task 6

//val semaphore = Semaphore(2)
//
//suspend fun accessResource(id: Int) {
//    withContext(Dispatchers.IO) {
//        semaphore.acquire()
//    }
//
//    try {
//        println("Thread $id is accessing the resource")
//        delay((500..1500).random().toLong())
//        println("Thread $id has finished using the resource.")
//    } finally {
//        semaphore.release()
//    }
//}
//
//fun main() = runBlocking {
//    val jobs = List(5) { index ->
//        launch(Dispatchers.Default) {
//            accessResource(index)
//        }
//    }
//
//    jobs.joinAll()
//}

//    // task 7

//fun factorial(n: Int): Long {
//    return if (n == 0) 1 else n * factorial(n - 1)
//}
//
//fun main() {
//    val executor = Executors.newFixedThreadPool(10)
//    val futures = List(10) { index ->
//        executor.submit(Callable {
//            val result = factorial(index + 1)
//            println("Factorial of ${index + 1} is $result")
//            result
//        })
//    }
//
//    futures.forEach { println("Result: ${it.get()}") }
//    executor.shutdown()
//}

//    // task 8
//val queue = ArrayBlockingQueue<Int>(5)
//
//fun main() = runBlocking {
//    val producer = launch(Dispatchers.Default) {
//        repeat(10) {
//            queue.put(it)
//            println("Produced: $it")
//            delay(1000)
//        }
//    }
//
//    val consumer = launch(Dispatchers.Default) {
//        repeat(10) {
//            val item = queue.take()
//            println("Consumed: $item")
//            delay(1000)
//        }
//    }
//
//    producer.join()
//    consumer.join()
//}

//    // task 9
//fun merge(left: List<Int>, right: List<Int>): List<Int> {
//    var i = 0
//    var j = 0
//    val merged = mutableListOf<Int>()
//    while (i < left.size && j < right.size) {
//        if (left[i] < right[i]) {
//            merged.add(left[i++])
//        } else {
//            merged.add(right[j++])
//        }
//    }
//    merged.addAll(left.drop(i))
//    merged.addAll(right.drop(j))
//    return merged
//}
//
//suspend fun parallelSort(array: List<Int>): List<Int> = coroutineScope {
//    if (array.size <= 1) {
//        return@coroutineScope array
//    }
//    val mid = array.size / 2
//    val leftDeferred = async(Dispatchers.Default) { parallelSort(array.subList(0, mid)) }
//    val rightDeferred = async(Dispatchers.Default) { parallelSort(array.subList(mid, array.size)) }
//    merge(leftDeferred.await(), rightDeferred.await())
//}
//
//fun main() = runBlocking {
//    val array = listOf(5, 2, 9, 1, 5, 6, 8, 3, 7, 4)
//    println("Unsorted array: $array")
//    val sortedArray = parallelSort(array)
//    println("Sorted array: $sortedArray")
//}

//   // task 10
//const val PHILOSOPHER_COUNT = 5
//
//class Fork {
//    val mutex = Mutex()
//}
//
//class Philosopher(private val id: Int, private val leftFork: Fork, private val rightFork: Fork) {
//    suspend fun dine() {
//        while (true) {
//            think()
//            eat()
//        }
//    }
//
//    private suspend fun think() {
//        println("Philosopher $id is thinking")
//        delay((500..1500).random().toLong())
//    }
//
//    private suspend fun eat() {
//        val firstFork = if (id % 2 == 0) leftFork else rightFork
//        val secondFork = if (id % 2 == 0) rightFork else leftFork
//
//        firstFork.mutex.withLock {
//            println("Philosopher $id picked up first fork")
//            delay((500..1500).random().toLong())
//        }
//    }
//}
//
//fun main() = runBlocking {
//    val forks = List(PHILOSOPHER_COUNT) { Fork() }
//    val philosophers = List(PHILOSOPHER_COUNT) { index ->
//        Philosopher(index, forks[index], forks[(index + 1) % PHILOSOPHER_COUNT])
//    }
//
//    coroutineScope {
//        philosophers.forEach { philosopher ->
//            launch {
//                philosopher.dine()
//            }
//        }
//    }
//}

//    // task 11

//fun multiplyMatricesParallel(A: Array<IntArray>, B: Array<IntArray>): Array<IntArray> {
//    val rowsA = A.size
//    val colsA = A[0].size
//    val colsB = B[0].size
//    val result = Array(rowsA) { IntArray(colsB) }
//
//    runBlocking {
//        val jobs = List(rowsA) { row ->
//            launch(Dispatchers.Default) {
//                for (col in 0 until colsB) {
//                    var sum = 0
//                    for (k in 0 until colsA) {
//                        sum += A[row][k] * B[k][col]
//                    }
//                    result[row][col] = sum
//                }
//            }
//        }
//        jobs.forEach { it.join() }
//    }
//    return result
//}
//
//fun generateMatrix(rows: Int, cols: Int): Array<IntArray> =
//    Array(rows) { IntArray(cols) { Random.nextInt(1, 10) } }
//
//fun printMatrix(matrix: Array<IntArray>) {
//    matrix.forEach { row -> println(row.joinToString("\t")) }
//}
//
//fun main() {
//    val A = generateMatrix(3, 3)
//    val B = generateMatrix(3, 3)
//
//    println("Матрица A:")
//    printMatrix(A)
//
//    println("\nМатрица B:")
//    printMatrix(B)
//
//    val result = multiplyMatricesParallel(A, B)
//
//    println("\nРезультат умножения:")
//    printMatrix(result)
//}


//    // task 12
//fun main() = runBlocking {
//    var isRunning = true
//
//    val timerJob = launch(Dispatchers.Default) {
//        var seconds = 0
//        while (isRunning) {
//            println("Seconds: $seconds")
//            delay(1000)
//            seconds++
//        }
//    }
//
//    launch {
//        delay(10000)
//        isRunning = false
//        println("Timer stopped")
//    }
//
//    timerJob.join()
//}
