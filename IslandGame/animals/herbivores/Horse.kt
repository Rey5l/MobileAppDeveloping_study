package animals.herbivores

import animals.Herbivore
import config.Config
import model.Island
import model.Location
import kotlin.random.Random

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
