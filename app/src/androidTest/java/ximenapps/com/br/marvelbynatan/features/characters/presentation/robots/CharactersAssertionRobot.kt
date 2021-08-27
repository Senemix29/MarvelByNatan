package ximenapps.com.br.marvelbynatan.features.characters.presentation.robots

class CharactersAssertionRobot {
    companion object {
        fun check(block: CharactersAssertionRobot.() -> Unit) {
            CharactersAssertionRobot().apply(block)
        }
    }
}