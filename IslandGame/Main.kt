import simulation.Simulation

fun main() {
    val simulation = Simulation()
    simulation.start()

    while (true) {
        Thread.sleep(1000)
    }
}
