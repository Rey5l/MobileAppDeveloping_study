package animals

import model.Island
import model.Location

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

abstract class Carnivore : Animal()
abstract class Herbivore : Animal()
