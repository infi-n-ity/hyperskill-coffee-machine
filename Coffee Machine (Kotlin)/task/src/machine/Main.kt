package machine


fun main() {
    val coffeeMachine = CoffeeMachine()
    coffeeMachine.start()
    do {
        val action = readln()
        coffeeMachine.performAction(action)
    } while (coffeeMachine.state != State.EXIT)
}



