package lesson_04_inheritance

// 1. Базовый (родительский) класс
// Чтобы класс можно было наследовать, его нужно объявить как 'open'.
// По умолчанию классы в Kotlin 'final' (не наследуемые).
open class Creature(
    val name: String,
    var dimension: String
) {
    // Свойство, которое может быть переопределено в дочерних классах
    open val sound: String = "Неизвестный звук"

    // Метод, который может быть переопределен
    open fun describe() {
        println("Это существо по имени $name из измерения $dimension.")
    }

    fun roam() {
        println("$name бродит по измерению $dimension.")
    }
}

// 2. Производный (дочерний) класс 'Human'
// Наследуется от 'Creature' с помощью двоеточия ':'.
class Human(
    name: String,
    dimension: String,
    val profession: String
) : Creature(
    name = name,
    dimension = dimension
) {

    // Переопределение свойства 'sound'
    override val sound: String = "Разговорная речь"

    // Переопределение метода 'describe'
    override fun describe() {
        // Вызов метода родительского класса с помощью 'super'
        super.describe()
        println("Он/она является $profession.")
    }

    fun work() {
        println("$name выполняет свою работу: $profession.")
    }
}

// 3. Производный класс 'Alien'
// Также наследуется от 'Creature'.
class Alien(
    name: String,
    dimension: String,
    val numberOfEyes: Int
) : Creature(
    name = name,
    dimension = dimension
) {

    // Переопределение свойства 'sound'
    override val sound: String = "Инопланетные звуки"

    override fun describe() {
        super.describe()
        println("У него $numberOfEyes глаз.")
    }

    fun abduct() {
        println("$name пытается похитить кого-то!")
    }
}

fun main() {
    println("--- Урок 4: Наследование ---")

    println("\n--- Базовое существо ---")
    val squanchy = Creature(
        name = "Сквончи",
        dimension = "Измерение Сквончи"
    )
    squanchy.describe()
    squanchy.roam()
    println("Звук: ${squanchy.sound}")

    println("\n--- Персонаж - Человек ---")
    val rick = Human(
        name = "Рик Санчез",
        dimension = "Земля C-137",
        profession = "Ученый"
    )
    rick.describe() // Вызывается переопределенный метод Human
    rick.roam()     // Метод, унаследованный от Creature
    rick.work()     // Собственный метод Human
    println("Звук: ${rick.sound}")

    println("\n--- Персонаж - Инопланетянин ---")
    val mrMeeseeks = Alien(
        name = "Мистер Мисикс",
        dimension = "Неизвестное измерение",
        numberOfEyes = 2
    )
    mrMeeseeks.describe() // Вызывается переопределенный метод Alien
    mrMeeseeks.roam()      // Метод, унаследованный от Creature
    mrMeeseeks.abduct()    // Собственный метод Alien
    println("Звук: ${mrMeeseeks.sound}")

    println("\n--- Полиморфизм в действии (кратко) ---")
    // Переменная типа Creature может хранить объект Human или Alien.
    // Это будет более подробно рассмотрено в уроке про полиморфизм.
    val creatureList: List<Creature> = listOf(rick, mrMeeseeks, squanchy)
    for (creature in creatureList) {
        creature.describe() // Вызывается метод describe() соответствующего типа объекта
        println("----------")
    }
}
