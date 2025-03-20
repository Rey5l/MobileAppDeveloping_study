package simulation

import config.Config
import model.Island
import model.Plant
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.random.Random

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

                if (location.plants.size < Config.MAX_PLANTS_PER_LOCATION && Random.nextBoolean()) {
                    location.plants.add(Plant())
                }

                val animalsToProcess = location.animals.toList()

                for (animal in animalsToProcess) {
                    if (animal !in location.animals) continue

                    animal.eat(location)

                    animal.reproduce(location)

                    animal.starve()

                    if (animal.energy <= 0) {
                        location.animals.remove(animal)
                        println("${animal.name} умер от голода.")
                    }
                }

                val animalsToMove = location.animals.toList()
                for (animal in animalsToMove) {
                    if (animal !in location.animals) continue

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
