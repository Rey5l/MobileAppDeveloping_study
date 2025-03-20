package model

import animals.Animal
import animals.carnivores.*
import animals.herbivores.*
import config.Config
import kotlin.random.Random

class Island {
    val grid: Array<Array<Location>> = Array(Config.HEIGHT) { Array(Config.WIDTH) { Location() } }

    init {
        repeat(Config.INITIAL_ANIMALS) {
            val x = Random.nextInt(Config.WIDTH)
            val y = Random.nextInt(Config.HEIGHT)
            val animal: Animal = when (Random.nextInt(15)) {
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
