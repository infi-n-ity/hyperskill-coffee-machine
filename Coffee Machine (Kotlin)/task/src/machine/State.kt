package machine

enum class State(val needNextLine: Boolean) {
    MAIN_MENU(true),
    CHOOSING_ACTION(true),
    CHOOSING_COFFEE(false),
    FILLING_WATER(false),
    FILLING_MILK(false),
    FILLING_BEANS(false),
    FILLING_CUPS(false),
    EXIT(false)
}