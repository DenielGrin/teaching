package lesson_06_poly_interfaces

// 1. Интерфейс
//  определяет набор методов (или свойств), которые класс должен реализовать.
// Он не содержит реализации методов.
interface Communicator {
    fun sendMessage(message: String)
    fun receiveMessage(): String
}

// 2. Абстрактный класс
// Абстрактный класс не может быть инстанцирован напрямую.
// Он может содержать как реализованные, так и абстрактные (без реализации) методы.
// От него могут наследоваться другие классы.
abstract class CosmicBeing(
    val name: String,
    val dimension: String
) {
    // Реализованный метод в абстрактном классе, помеченный как 'open'
    open fun introduce() {
        println("Я $name, существо из измерения $dimension.")
    }

    // Реализованный метод, помеченный как 'open'
    open fun describeExistence() { // Теперь помечен как 'open'
        println("$name существует в измерении $dimension и это часть его бытия.")
    }

    abstract fun performCosmicDuty() // Абстрактный метод, должен быть реализован в подклассах
}

// 3. Класс 'Rick' реализует интерфейс 'Communicator' и наследуется от CosmicBeing
class Rick(
    name: String,
    dimension: String
) : CosmicBeing(
    name = name,
    dimension = dimension
), Communicator {
    // Переопределение абстрактного метода
    override fun performCosmicDuty() {
        println("$name занят спасением или разрушением мультивселенной.")
    }

    // Переопределение реализованного метода из абстрактного класса
    override fun introduce() {
        super.introduce()
        println("Я также гений и алкоголик.")
    }

    // Опциональное переопределение describeExistence
    override fun describeExistence() {
        super.describeExistence()
        println("Для Рика это обычно включает приключения и много пьянства.")
    }

    // Реализация методов интерфейса Communicator
    override fun sendMessage(message: String) {
        println("$name кричит: \"$message\"")
    }

    override fun receiveMessage(): String {
        return "Услышал ${name}'s безумное бормотание."
    }

    fun inventGadget() {
        println("$name изобретает новый гаджет!")
    }
}

// 4. Класс 'Morty' также реализует интерфейс 'Communicator' и наследуется от CosmicBeing
class Morty(
    name: String,
    dimension: String
) : CosmicBeing(
    name = name,
    dimension = dimension
), Communicator {
    // Переопределение абстрактного метода
    override fun performCosmicDuty() {
        println("$name пытается просто выжить в этой безумной вселенной.")
    }

    // Переопределение реализованного метода из абстрактного класса
    override fun introduce() {
        super.introduce()
        println("И я просто хочу быть нормальным.")
    }

    // Опциональное переопределение describeExistence
    override fun describeExistence() {
        super.describeExistence()
        println("Для Морти это бесконечный стресс и травмы.")
    }

    // Реализация методов интерфейса Communicator
    override fun sendMessage(message: String) {
        println("$name нервно мямлит: \"$message\"")
    }

    override fun receiveMessage(): String {
        return "Услышал ${name}'s испуганное бормотание."
    }

    fun panic() {
        println("$name впадает в панику!")
    }
}

// 5. Класс 'CouncilOfRicks' использует полиморфизм
class CouncilOfRicks(private val members: List<Rick>) {
    fun holdMeeting() {
        println("\n--- Совет Риков проводит собрание ---")
        for (rick in members) {
            rick.sendMessage("Заседание началось!")
            rick.performCosmicDuty()
            rick.introduce()
            rick.describeExistence() // Теперь доступно и здесь
            println("--------------------")
        }
    }
}

fun main() {
    println("--- Урок 6: Полиморфизм и Интерфейсы ---")

    val c137Rick = Rick(name = "Рик C-137", dimension = "Измерение C-137")
    val fatRick = Rick(name = "Толстый Рик", dimension = "Измерение J19 Zeta 7")
    val mortyPrime = Morty(name = "Морти Прайм", dimension = "Измерение C-137")

    println("\n--- Демонстрация методов персонажей ---")
    c137Rick.introduce()
    c137Rick.describeExistence() // Теперь работает
    c137Rick.performCosmicDuty()
    c137Rick.inventGadget()
    c137Rick.sendMessage("Вубба Лубба Даб Даб!")
    println(c137Rick.receiveMessage())

    println("\n--- Демонстрация Морти ---")
    mortyPrime.introduce()
    mortyPrime.describeExistence() // Теперь работает
    mortyPrime.performCosmicDuty()
    mortyPrime.panic()
    mortyPrime.sendMessage("О, боже, Рик, что мы будем делать?!")
    println(mortyPrime.receiveMessage())

    println("\n--- Полиморфизм с Интерфейсом Communicator ---")
    val communicators: List<Communicator> = listOf(c137Rick, mortyPrime, fatRick)

    for (comm in communicators) {
        comm.sendMessage("Это тестовое сообщение от общего интерфейса.")
        println(comm.receiveMessage())
        println("----------")
    }

    println("\n--- Полиморфизм с Абстрактным Классом CosmicBeing ---")
    val cosmicBeings: List<CosmicBeing> = listOf(c137Rick, mortyPrime)

    for (being in cosmicBeings) {
        being.describeExistence() // Вызывает переопределенный метод
        being.performCosmicDuty()
        being.introduce()
        println("----------")
    }

    // Демонстрация работы с коллекцией Риков
    val council = CouncilOfRicks(listOf(c137Rick, fatRick))
    council.holdMeeting()
}
