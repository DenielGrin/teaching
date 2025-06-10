package lesson_07_special_classes

// 1. Data-класс
// Data-классы предназначены для хранения данных.
// Компилятор Kotlin автоматически генерирует для них:
// - equals()/hashCode()
// - toString()
// - componentN() функции (для деструктуризации)
// - copy()
data class Episode(
    val season: Int,
    val episodeNumber: Int,
    val title: String,
    val airDate: String
) {
    // В data-классах можно добавлять свои методы и свойства
    fun getFullTitle(): String {
        return "S${"%02d".format(season)}E${"%02d".format(episodeNumber)}: $title"
    }
}

// 2. Sealed-класс
// Sealed-класс используется для создания иерархий классов,
// где все возможные подклассы известны и определены в том же файле (или вложены).
// Компилятор может проверять полноту выражений 'when' с sealed-классами.
sealed class GalacticEvent {
    data class PortalAnomaly(val dimension: String, val severity: Int) : GalacticEvent()
    object UniversalThreat : GalacticEvent() // Объект как подкласс
    class AlienInvasion(val planet: String, val invaders: String) : GalacticEvent()
    // Можно также иметь обычные классы в качестве подклассов, не обязательно data-классы.
    // data class CosmicDustCloud(val density: Double) : GalacticEvent()
}

// 3. Object-класс (Singleton)
// Объектный класс объявляется с ключевым словом 'object'.
// Создает единственный экземпляр этого класса (синглтон).
// Нет конструктора, нет множественных экземпляров.
object InterdimensionalCouncil {
    private var rickCount: Int = 0

    fun addRick() {
        rickCount++
        println("Рик добавлен в Совет. Всего Риков: $rickCount")
    }

    fun holdVote(topic: String) {
        println("Совет голосует по теме: '$topic'.")
        if (rickCount > 5) {
            println("Голосование завершено: Решение принято большинством Риков.")
        } else {
            println("Голосование не состоялось: Недостаточно Риков.")
        }
    }
}

// 4. Companion Object (Сопутствующий объект)
// Объект, связанный с классом, а не с экземпляром класса.
// Позволяет создавать "статические" члены класса (методы, свойства).
class SpaceShip(val name: String) {
    // Сопутствующий объект для создания фабричного метода
    companion object Factory { // 'Factory' - это опциональное имя для companion object
        const val MAX_SPEED_LIGHT_YEARS_PER_HOUR = 1000 // Константа времени компиляции

        fun createDefaultShip(): SpaceShip {
            println("Создаем стандартный космический корабль.")
            return SpaceShip(name = "Грузовой шаттл")
        }

        fun createPlumbusPoweredShip(name: String): SpaceShip {
            println("Создаем космический корабль с питанием от Пламбуса: $name")
            return SpaceShip(name = name)
        }
    }

    fun activateShields() {
        println("$name: Щиты активированы!")
    }
}


fun processGalacticEvent(event: GalacticEvent) {
    when (event) {
        is GalacticEvent.PortalAnomaly -> println("Обнаружена портальная аномалия в ${event.dimension}, серьезность: ${event.severity}.")
        is GalacticEvent.UniversalThreat -> println("Внимание! Обнаружена вселенская угроза!")
        is GalacticEvent.AlienInvasion -> println("Планета ${event.planet} подверглась вторжению ${event.invaders}!")
        // Компилятор Kotlin заставит вас добавить 'else' ветку или все подклассы
        // если вы не использовали 'sealed' и не охватили все случаи.
        // С sealed-классами, если вы добавите новый подкласс, компилятор укажет на пропущенные ветки 'when'.
    }
}


fun main() {
    println("--- Урок 7: Специальные Классы Kotlin ---")

    // --- Data-классы ---
    println("\n--- Data-классы ---")
    val firstEpisode = Episode(season = 1, episodeNumber = 1, title = "Пилот", airDate = "02.12.2013")
    val secondEpisode = Episode(season = 1, episodeNumber = 1, title = "Пилот", airDate = "02.12.2013") // Та же информация
    val thirdEpisode = Episode(season = 1, episodeNumber = 2, title = "Газорпазорп", airDate = "09.12.2013")

    println("Первый эпизод: ${firstEpisode.getFullTitle()} (Дата: ${firstEpisode.airDate})")
    println("Третий эпизод: ${thirdEpisode.getFullTitle()}")

    // Автоматически генерируемые методы:
    println("firstEpisode == secondEpisode: ${firstEpisode == secondEpisode}") // true (equals() по значению)
    println("firstEpisode == thirdEpisode: ${firstEpisode == thirdEpisode}")   // false

    // toString()
    println("firstEpisode.toString(): $firstEpisode")

    // copy()
    val pilotInSpace = firstEpisode.copy(title = "Пилот в космосе")
    println("Копия эпизода: $pilotInSpace")

    // Деструктуризация (componentN())
    val (s, epNum, title, date) = firstEpisode
    println("Деструктуризация: Сезон $s, Эпизод $epNum, Название '$title', Дата '$date'")

    // --- Sealed-классы ---
    println("\n--- Sealed-классы ---")
    val portalProblem = GalacticEvent.PortalAnomaly("Измерение C-137", 8)
    val cosmicDoomsday = GalacticEvent.UniversalThreat
    val cronenbergInvasion = GalacticEvent.AlienInvasion("Земля", "Кроненберги")

    processGalacticEvent(portalProblem)
    processGalacticEvent(cosmicDoomsday)
    processGalacticEvent(cronenbergInvasion)

    // При добавлении нового подкласса в GalacticEvent, компилятор напомнит
    // добавить новую ветку в 'when' без 'else'.

    // --- Object-классы (Singleton) ---
    println("\n--- Object-классы (Singleton) ---")
    InterdimensionalCouncil.addRick() // Доступ к единственному экземпляру через имя объекта
    InterdimensionalCouncil.addRick()
    InterdimensionalCouncil.holdVote(topic = "Разрешение на межпространственные продажи Пламбусов")

    val councilRef1 = InterdimensionalCouncil
    val councilRef2 = InterdimensionalCouncil
    println("councilRef1 == councilRef2: ${councilRef1 == councilRef2}") // true, это один и тот же объект

    // --- Companion Object ---
    println("\n--- Companion Object ---")
    val defaultShip = SpaceShip.createDefaultShip() // Вызов "статического" метода
    defaultShip.activateShields()

    val plumbusShip = SpaceShip.createPlumbusPoweredShip(name = "Морти's Cruiser")
    plumbusShip.activateShields()

    println("Максимальная скорость: ${SpaceShip.MAX_SPEED_LIGHT_YEARS_PER_HOUR} световых лет/час")
}
