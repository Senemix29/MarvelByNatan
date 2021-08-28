package ximenapps.com.br.marvelbynatan.features.characters.presentation.robots

class CharactersAssertionRobot {

    fun listSizeIs(size: Int) {
        //TODO
    }

    fun characterNameAtPosition(name: String, position: Int) {
        //TODO
    }

    companion object {
        fun check(block: CharactersAssertionRobot.() -> Unit) {
            CharactersAssertionRobot().apply(block)
        }
    }
}