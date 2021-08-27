package ximenapps.com.br.marvelbynatan.features.characters.presentation.robots

class CharactersActionRobot {
    companion object {
        fun act(block: CharactersActionRobot.() -> Unit) {
            CharactersActionRobot().apply(block)
        }
    }
}