package model

import animals.Animal
import kotlin.random.Random

class Location {
    val animals: MutableList<Animal> = mutableListOf()
    val plants: MutableList<Plant> = mutableListOf()

    init {
        if (Random.nextBoolean()) {
            plants.add(Plant())
        }
    }
}
