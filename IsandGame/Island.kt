import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.random.Random

// Конфигурация острова
object Config {
    const val WIDTH = 20
    const val HEIGHT = 20
    const val SIMULATION_TICK = 2000L
    const val REPRODUCTION_CHANCE = 30
    const val MAX_ANIMALS_PER_LOCATION = 10
    const val INITIAL_ANIMALS = 200
    const val MAX_PLANTS_PER_LOCATION = 3
    const val MAX_TICKS = 10
}

// Локация на острове
class Location {
    val animals: MutableList<Animal> = mutableListOf()
    val plants: MutableList<Plant> = mutableListOf()

    init {
        if (Random.nextBoolean()) {
            plants.add(Plant())
        }
    }
}

// Остров
class Island {
    val grid: Array<Array<Location>> = Array(Config.HEIGHT) { Array(Config.WIDTH) { Location() } }

    init {
        repeat(Config.INITIAL_ANIMALS) {
            val x = Random.nextInt(Config.WIDTH)
            val y = Random.nextInt(Config.HEIGHT)
            val animal: Animal = when (Random.nextInt(15)) { // 15 видов животных
                0 -> Wolf()
                1 -> Boa()
                2 -> Fox()
                3 -> Bear()
                4 -> Eagle()
                5 -> Horse()
                6 -> Deer()
                7 -> Rabbit()
                8 -> Mouse()
                9 -> Goat()
                10 -> Sheep()
                11 -> Boar()
                12 -> Buffalo()
                13 -> Duck()
                else -> Caterpillar()
            }
            grid[y][x].animals.add(animal)
        }
    }

    // Подсчет общего количества животных
    fun countAnimals(): Int {
        var count = 0
        for (y in 0..<Config.HEIGHT) {
            for (x in 0..<Config.WIDTH) {
                count += grid[y][x].animals.size
            }
        }
        return count
    }
}

// Абстрактный класс животного
abstract class Animal {
    abstract val name: String
    var energy: Int = 100

    abstract fun eat(location: Location)
    abstract fun move(island: Island, x: Int, y: Int): Pair<Int, Int>
    abstract fun reproduce(location: Location)

    fun starve() {
        energy -= 8
        if (energy <= 0) {
            println("$name умер от голода.")
        }
    }
}

// Хищники и травоядные
abstract class Carnivore : Animal()
abstract class Herbivore : Animal()

// Хищники
class Wolf : Carnivore() {
    override val name = "\uD83D\uDC3A Волк"
    override fun eat(location: Location) {
        val prey = location.animals.find { it is Herbivore }
        if (prey != null && Random.nextInt(100) < 60) {
            println("$name съел ${prey.name}.")
            location.animals.remove(prey)
            energy += 50
        }
    }
    override fun move(island: Island, x: Int, y: Int): Pair<Int, Int> {
        val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
        val (dx, dy) = directions.random()
        val newX = (x + dx).coerceIn(0, Config.WIDTH - 1)
        val newY = (y + dy).coerceIn(0, Config.HEIGHT - 1)
        return newX to newY
    }
    override fun reproduce(location: Location) {
        if (location.animals.count { it is Wolf } >= 2 && location.animals.size < Config.MAX_ANIMALS_PER_LOCATION && Random.nextInt(100) < Config.REPRODUCTION_CHANCE) {
            location.animals.add(Wolf())
            println("Родился новый волк! \uD83D\uDC3A")
        }
    }
}

class Boa : Carnivore() {
    override val name = "\uD83D\uDC0D Удав"
    override fun eat(location: Location) {
        val prey = location.animals.find { it is Herbivore }
        if (prey != null && Random.nextInt(100) < 50) {
            println("$name съел ${prey.name}.")
            location.animals.remove(prey)
            energy += 40
        }
    }
    override fun move(island: Island, x: Int, y: Int): Pair<Int, Int> {
        val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
        val (dx, dy) = directions.random()
        val newX = (x + dx).coerceIn(0, Config.WIDTH - 1)
        val newY = (y + dy).coerceIn(0, Config.HEIGHT - 1)
        return newX to newY
    }
    override fun reproduce(location: Location) {
        if (location.animals.count { it is Boa } >= 2 && location.animals.size < Config.MAX_ANIMALS_PER_LOCATION && Random.nextInt(100) < Config.REPRODUCTION_CHANCE) {
            location.animals.add(Boa())
            println("Родился новый удав! \uD83D\uDC0D")
        }
    }
}

class Fox : Carnivore() {
    override val name = "\uD83E\uDD8A Лиса"
    override fun eat(location: Location) {
        val prey = location.animals.find { it is Herbivore }
        if (prey != null && Random.nextInt(100) < 70) {
            println("$name съел ${prey.name}.")
            location.animals.remove(prey)
            energy += 30
        }
    }
    override fun move(island: Island, x: Int, y: Int): Pair<Int, Int> {
        val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
        val (dx, dy) = directions.random()
        val newX = (x + dx).coerceIn(0, Config.WIDTH - 1)
        val newY = (y + dy).coerceIn(0, Config.HEIGHT - 1)
        return newX to newY
    }
    override fun reproduce(location: Location) {
        if (location.animals.count { it is Fox } >= 2 && location.animals.size < Config.MAX_ANIMALS_PER_LOCATION && Random.nextInt(100) < Config.REPRODUCTION_CHANCE) {
            location.animals.add(Fox())
            println("Родилась новая лиса! \uD83E\uDD8A")
        }
    }
}

class Bear : Carnivore() {
    override val name = "\uD83D\uDC3B Медведь"
    override fun eat(location: Location) {
        val prey = location.animals.find { it is Herbivore }
        if (prey != null && Random.nextInt(100) < 80) {
            println("$name съел ${prey.name}.")
            location.animals.remove(prey)
            energy += 70
        }
    }
    override fun move(island: Island, x: Int, y: Int): Pair<Int, Int> {
        val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
        val (dx, dy) = directions.random()
        val newX = (x + dx).coerceIn(0, Config.WIDTH - 1)
        val newY = (y + dy).coerceIn(0, Config.HEIGHT - 1)
        return newX to newY
    }
    override fun reproduce(location: Location) {
        if (location.animals.count { it is Bear } >= 2 && location.animals.size < Config.MAX_ANIMALS_PER_LOCATION && Random.nextInt(100) < Config.REPRODUCTION_CHANCE) {
            location.animals.add(Bear())
            println("Родился новый медведь! \uD83D\uDC3B")
        }
    }
}

class Eagle : Carnivore() {
    override val name = "\uD83E\uDD85 Орел"
    override fun eat(location: Location) {
        val prey = location.animals.find { it is Herbivore }
        if (prey != null && Random.nextInt(100) < 90) {
            println("$name съел ${prey.name}.")
            location.animals.remove(prey)
            energy += 60
        }
    }
    override fun move(island: Island, x: Int, y: Int): Pair<Int, Int> {
        val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
        val (dx, dy) = directions.random()
        val newX = (x + dx).coerceIn(0, Config.WIDTH - 1)
        val newY = (y + dy).coerceIn(0, Config.HEIGHT - 1)
        return newX to newY
    }
    override fun reproduce(location: Location) {
        if (location.animals.count { it is Eagle } >= 2 && location.animals.size < Config.MAX_ANIMALS_PER_LOCATION && Random.nextInt(100) < Config.REPRODUCTION_CHANCE) {
            location.animals.add(Eagle())
            println("Родился новый орел! \uD83E\uDD85")
        }
    }
}

// Травоядные
class Horse : Herbivore() {
    override val name = "\uD83D\uDC0E Лошадь"
    override fun eat(location: Location) {
        if (location.plants.isNotEmpty()) {
            location.plants.removeAt(0)
            energy += 40
            println("$name съел растение.\uD83C\uDF3F")
        }
    }
    override fun move(island: Island, x: Int, y: Int): Pair<Int, Int> {
        val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
        val (dx, dy) = directions.random()
        val newX = (x + dx).coerceIn(0, Config.WIDTH - 1)
        val newY = (y + dy).coerceIn(0, Config.HEIGHT - 1)
        return newX to newY
    }
    override fun reproduce(location: Location) {
        if (location.animals.count { it is Horse } >= 2 && location.animals.size < Config.MAX_ANIMALS_PER_LOCATION && Random.nextInt(100) < Config.REPRODUCTION_CHANCE) {
            location.animals.add(Horse())
            println("Родилась новая лошадь! \uD83D\uDC0E")
        }
    }
}

class Deer : Herbivore() {
    override val name = "\uD83E\uDD8C Олень"
    override fun eat(location: Location) {
        if (location.plants.isNotEmpty()) {
            location.plants.removeAt(0)
            energy += 30
            println("$name съел растение.\uD83C\uDF3F")
        }
    }
    override fun move(island: Island, x: Int, y: Int): Pair<Int, Int> {
        val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
        val (dx, dy) = directions.random()
        val newX = (x + dx).coerceIn(0, Config.WIDTH - 1)
        val newY = (y + dy).coerceIn(0, Config.HEIGHT - 1)
        return newX to newY
    }
    override fun reproduce(location: Location) {
        if (location.animals.count { it is Deer } >= 2 && location.animals.size < Config.MAX_ANIMALS_PER_LOCATION && Random.nextInt(100) < Config.REPRODUCTION_CHANCE) {
            location.animals.add(Deer())
            println("Родился новый олень! \uD83E\uDD8C")
        }
    }
}

class Rabbit : Herbivore() {
    override val name = "\uD83D\uDC07 Кролик"
    override fun eat(location: Location) {
        if (location.plants.isNotEmpty()) {
            location.plants.removeAt(0)
            energy += 20
            println("$name съел растение.\uD83C\uDF3F")
        }
    }
    override fun move(island: Island, x: Int, y: Int): Pair<Int, Int> {
        val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
        val (dx, dy) = directions.random()
        val newX = (x + dx).coerceIn(0, Config.WIDTH - 1)
        val newY = (y + dy).coerceIn(0, Config.HEIGHT - 1)
        return newX to newY
    }
    override fun reproduce(location: Location) {
        if (location.animals.count { it is Rabbit } >= 2 && location.animals.size < Config.MAX_ANIMALS_PER_LOCATION && Random.nextInt(100) < Config.REPRODUCTION_CHANCE) {
            location.animals.add(Rabbit())
            println("Родился новый кролик! \uD83D\uDC07")
        }
    }
}

class Mouse : Herbivore() {
    override val name = "\uD83D\uDC01 Мышь"
    override fun eat(location: Location) {
        if (location.plants.isNotEmpty()) {
            location.plants.removeAt(0)
            energy += 10
            println("$name съел растение.\uD83C\uDF3F")
        }
    }
    override fun move(island: Island, x: Int, y: Int): Pair<Int, Int> {
        val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
        val (dx, dy) = directions.random()
        val newX = (x + dx).coerceIn(0, Config.WIDTH - 1)
        val newY = (y + dy).coerceIn(0, Config.HEIGHT - 1)
        return newX to newY
    }
    override fun reproduce(location: Location) {
        if (location.animals.count { it is Mouse } >= 2 && location.animals.size < Config.MAX_ANIMALS_PER_LOCATION && Random.nextInt(100) < Config.REPRODUCTION_CHANCE) {
            location.animals.add(Mouse())
            println("Родилась новая мышь! \uD83D\uDC01")
        }
    }
}

class Goat : Herbivore() {
    override val name = "\uD83D\uDC10 Коза"
    override fun eat(location: Location) {
        if (location.plants.isNotEmpty()) {
            location.plants.removeAt(0)
            energy += 25
            println("$name съел растение.\uD83C\uDF3F")
        }
    }
    override fun move(island: Island, x: Int, y: Int): Pair<Int, Int> {
        val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
        val (dx, dy) = directions.random()
        val newX = (x + dx).coerceIn(0, Config.WIDTH - 1)
        val newY = (y + dy).coerceIn(0, Config.HEIGHT - 1)
        return newX to newY
    }
    override fun reproduce(location: Location) {
        if (location.animals.count { it is Goat } >= 2 && location.animals.size < Config.MAX_ANIMALS_PER_LOCATION && Random.nextInt(100) < Config.REPRODUCTION_CHANCE) {
            location.animals.add(Goat())
            println("Родилась новая коза! \uD83D\uDC10")
        }
    }
}

class Sheep : Herbivore() {
    override val name = "\uD83D\uDC11 Овца"
    override fun eat(location: Location) {
        if (location.plants.isNotEmpty()) {
            location.plants.removeAt(0)
            energy += 30
            println("$name съел растение.\uD83C\uDF3F")
        }
    }
    override fun move(island: Island, x: Int, y: Int): Pair<Int, Int> {
        val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
        val (dx, dy) = directions.random()
        val newX = (x + dx).coerceIn(0, Config.WIDTH - 1)
        val newY = (y + dy).coerceIn(0, Config.HEIGHT - 1)
        return newX to newY
    }
    override fun reproduce(location: Location) {
        if (location.animals.count { it is Sheep } >= 2 && location.animals.size < Config.MAX_ANIMALS_PER_LOCATION && Random.nextInt(100) < Config.REPRODUCTION_CHANCE) {
            location.animals.add(Sheep())
            println("Родилась новая овца! \uD83D\uDC11")
        }
    }
}

class Boar : Herbivore() {
    override val name = "\uD83D\uDC17 Кабан"
    override fun eat(location: Location) {
        if (location.plants.isNotEmpty()) {
            location.plants.removeAt(0)
            energy += 50
            println("$name съел растение.\uD83C\uDF3F")
        }
    }
    override fun move(island: Island, x: Int, y: Int): Pair<Int, Int> {
        val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
        val (dx, dy) = directions.random()
        val newX = (x + dx).coerceIn(0, Config.WIDTH - 1)
        val newY = (y + dy).coerceIn(0, Config.HEIGHT - 1)
        return newX to newY
    }
    override fun reproduce(location: Location) {
        if (location.animals.count { it is Boar } >= 2 && location.animals.size < Config.MAX_ANIMALS_PER_LOCATION && Random.nextInt(100) < Config.REPRODUCTION_CHANCE) {
            location.animals.add(Boar())
            println("Родился новый кабан! \uD83D\uDC17")
        }
    }
}

class Buffalo : Herbivore() {
    override val name = "\uD83D\uDC03 Буйвол"
    override fun eat(location: Location) {
        if (location.plants.isNotEmpty()) {
            location.plants.removeAt(0)
            energy += 70
            println("$name съел растение.\uD83C\uDF3F")
        }
    }
    override fun move(island: Island, x: Int, y: Int): Pair<Int, Int> {
        val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
        val (dx, dy) = directions.random()
        val newX = (x + dx).coerceIn(0, Config.WIDTH - 1)
        val newY = (y + dy).coerceIn(0, Config.HEIGHT - 1)
        return newX to newY
    }
    override fun reproduce(location: Location) {
        if (location.animals.count { it is Buffalo } >= 2 && location.animals.size < Config.MAX_ANIMALS_PER_LOCATION && Random.nextInt(100) < Config.REPRODUCTION_CHANCE) {
            location.animals.add(Buffalo())
            println("Родился новый буйвол! \uD83D\uDC03")
        }
    }
}

class Duck : Herbivore() {
    override val name = "\uD83E\uDD86 Утка"
    override fun eat(location: Location) {
        if (location.plants.isNotEmpty()) {
            location.plants.removeAt(0)
            energy += 15
            println("$name съел растение.\uD83C\uDF3F")
        }
    }
    override fun move(island: Island, x: Int, y: Int): Pair<Int, Int> {
        val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
        val (dx, dy) = directions.random()
        val newX = (x + dx).coerceIn(0, Config.WIDTH - 1)
        val newY = (y + dy).coerceIn(0, Config.HEIGHT - 1)
        return newX to newY
    }
    override fun reproduce(location: Location) {
        if (location.animals.count { it is Duck } >= 2 && location.animals.size < Config.MAX_ANIMALS_PER_LOCATION && Random.nextInt(100) < Config.REPRODUCTION_CHANCE) {
            location.animals.add(Duck())
            println("Родилась новая утка! \uD83E\uDD86")
        }
    }
}

class Caterpillar : Herbivore() {
    override val name = "\uD83D\uDC1B Гусеница"
    override fun eat(location: Location) {
        if (location.plants.isNotEmpty()) {
            location.plants.removeAt(0)
            energy += 5
            println("$name съел растение.\uD83C\uDF3F")
        }
    }
    override fun move(island: Island, x: Int, y: Int): Pair<Int, Int> {
        val directions = listOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
        val (dx, dy) = directions.random()
        val newX = (x + dx).coerceIn(0, Config.WIDTH - 1)
        val newY = (y + dy).coerceIn(0, Config.HEIGHT - 1)
        return newX to newY
    }
    override fun reproduce(location: Location) {
        if (location.animals.count { it is Caterpillar } >= 2 && location.animals.size < Config.MAX_ANIMALS_PER_LOCATION && Random.nextInt(100) < Config.REPRODUCTION_CHANCE) {
            location.animals.add(Caterpillar())
            println("Родилась новая гусеница! \uD83D\uDC1B")
        }
    }
}

// Растения
class Plant

// Основной симулятор
class Simulation {
    private val island = Island()
    private val executor = Executors.newScheduledThreadPool(3)
    private var currentTick = 0

    fun start() {
        executor.scheduleAtFixedRate({ tick() }, 0, Config.SIMULATION_TICK, TimeUnit.MILLISECONDS)
    }

    private fun tick() {
        println("=== Такт симуляции $currentTick ===")
        currentTick++

        // Проверка условий остановки
        if (currentTick >= Config.MAX_TICKS) {
            println("Достигнуто максимальное количество тактов. Симуляция остановлена.")
            executor.shutdown()
            return
        }

        if (island.countAnimals() == 0) {
            println("Все животные вымерли. Симуляция остановлена.")
            executor.shutdown()
            return
        }

        for (y in 0..<Config.HEIGHT) {
            for (x in 0..<Config.WIDTH) {
                val location = island.grid[y][x]

                // Рост растений
                if (location.plants.size < Config.MAX_PLANTS_PER_LOCATION && Random.nextBoolean()) {
                    location.plants.add(Plant())
                }

                // Создаем копию списка животных для безопасной итерации
                val animalsToProcess = location.animals.toList()

                // Обрабатываем каждое животное
                for (animal in animalsToProcess) {
                    // Проверяем, что животное все еще существует в списке
                    if (animal !in location.animals) continue

                    // Животное ест
                    animal.eat(location)

                    // Животное размножается
                    animal.reproduce(location)

                    // Животное теряет энергию
                    animal.starve()

                    // Если энергия животного <= 0, оно умирает
                    if (animal.energy <= 0) {
                        location.animals.remove(animal)
                        println("${animal.name} умер от голода.")
                    }
                }

                // Перемещение животных
                val animalsToMove = location.animals.toList()
                for (animal in animalsToMove) {
                    // Проверяем, что животное все еще существует в списке
                    if (animal !in location.animals) continue

                    // Животное перемещается
                    val (newX, newY) = animal.move(island, x, y)
                    if (newX != x || newY != y) {
                        island.grid[newY][newX].animals.add(animal)
                        location.animals.remove(animal)
                    }
                }
            }
        }
    }
}

fun main() {
    val simulation = Simulation()
    simulation.start()

    while (true) {
        Thread.sleep(1000)
    }
}
