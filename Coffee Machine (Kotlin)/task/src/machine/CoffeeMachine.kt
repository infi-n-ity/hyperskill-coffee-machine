package machine

class CoffeeMachine(var state: State = State.MAIN_MENU,
                    private var water: Int = 400,
                    private var milk: Int = 540,
                    private var beans: Int = 120,
                    private var cups: Int = 9,
                    private var money: Int = 550) {

    fun start() {
        askAction()
    }

    fun performAction(action: String) {
        if (state.needNextLine) {
            println()
        }

        when(state) {
            State.CHOOSING_ACTION -> chooseAction(action)
            State.CHOOSING_COFFEE -> takeOrder(action)
            State.FILLING_WATER -> addWater(action.toInt())
            State.FILLING_MILK -> addMilk(action.toInt())
            State.FILLING_BEANS -> addBeans(action.toInt())
            State.FILLING_CUPS -> addCups(action.toInt())
        }

        if (state == State.MAIN_MENU) {
            println()
            askAction()
        }
    }

    private fun chooseAction(action: String) {
        when (action) {
            "buy" -> buy()
            "fill" -> askWaterCount()
            "take" -> take()
            "remaining" -> this.printInfo()
            "exit" -> state = State.EXIT
        }
    }

    private fun askAction() {
        println("Write action (buy, fill, take, remaining, exit):")
        state = State.CHOOSING_ACTION
    }

    private fun buy() {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
        state = State.CHOOSING_COFFEE
    }

    private fun takeOrder(coffeeType: String) {
        when (coffeeType) {
            "1" -> if (machineHaveResource(Coffee.ESPRESSO)) makeCoffee(Coffee.ESPRESSO)
            "2" -> if(machineHaveResource(Coffee.LATTE)) makeCoffee(Coffee.LATTE)
            "3" -> if (machineHaveResource(Coffee.CAPPUCCINO)) makeCoffee(Coffee.CAPPUCCINO)
        }
        state = State.MAIN_MENU
    }

    private fun machineHaveResource(coffee: Coffee): Boolean {
        return if (water < coffee.water) {
            println("Sorry, not enough water!")
            false
        } else if (milk < coffee.milk) {
            println("Sorry, not enough milk!")
            false
        } else if (beans < coffee.beans) {
            println("Sorry, not enough beans!")
            false
        } else if (cups == 0) {
            println("Sorry, not enough cups!")
            false
        } else {
            println("I have enough resources, making you a coffee!")
            true
        }
    }

    private fun makeCoffee(coffee: Coffee) {
        water -= coffee.water
        milk -= coffee.milk
        beans -= coffee.beans
        cups--
        money += coffee.cost
    }

    private fun askWaterCount() {
        println("Write how many ml of water you want to add:")
        state = State.FILLING_WATER
    }

    private fun askMilkCount() {
        println("Write how many ml of milk you want to add:")
        state = State.FILLING_MILK
    }

    private fun askBeansCount() {
        println("Write how many grams of coffee beans you want to add:")
        state = State.FILLING_BEANS
    }

    private fun askCupsCount() {
        println("Write how many disposable cups you want to add:")
        state = State.FILLING_CUPS
    }

    private fun addWater(count: Int) {
        water += count
        askMilkCount()
    }

    private fun addMilk(count: Int) {
        milk += count
        askBeansCount()
    }

    private fun addBeans(count: Int) {
        beans += count
        askCupsCount()
    }

    private fun addCups(count: Int) {
        cups += count
        state = State.MAIN_MENU
    }

    private fun take() {
        println("I gave you $$money")
        money = 0
        state = State.MAIN_MENU
    }

    private fun printInfo() {
        println("The coffee machine has:")
        println("$water ml of water")
        println("$milk ml of milk")
        println("$beans g of coffee beans")
        println("$cups disposable cups")
        println("$$money of money")
        state = State.MAIN_MENU
    }
}