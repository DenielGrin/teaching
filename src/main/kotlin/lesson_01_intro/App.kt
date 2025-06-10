package lesson_01_intro

// 1. Определение класса
// Класс — это шаблон или чертеж для создания объектов.
// Здесь мы определяем класс 'Character' с двумя свойствами: 'name' и 'species'.
class Character(val name: String, val species: String) {

    // 2. Метод класса
    // Метод — это функция, которая принадлежит классу и оперирует его данными.
    fun introduce() {
        println("Привет! Меня зовут $name, и я $species.")
    }

    fun catchphrase() {
        if (name == "Рик Санчез") {
            println("Wubba Lubba Dub Dub!")
        } else if (name == "Морти Смит") {
            println("О, боже, Рик!")
        } else {
            println("Просто еще один персонаж.")
        }
    }
}

// 3. Точка входа в программу Kotlin
// Функция 'main' — это место, откуда начинается выполнение нашей программы.
fun main() {
    println("--- Урок 1: Введение в классы (Рик и Морти) ---")

    // 4. Создание объекта (экземпляра) класса
    // Мы создаем конкретные объекты на основе чертежа 'Character'.
    // 'rick' и 'morty' — это экземпляры класса 'Character'.
    val rick = Character("Рик Санчез", "Человек")
    val morty = Character("Морти Смит", "Человек")
    val birdperson = Character("Бердперсон", "Бердперсон")

    // 5. Доступ к свойствам и вызов методов объекта
    // Мы можем обращаться к свойствам объекта и вызывать его методы.
    println("Имя первого персонажа: ${rick.name}")
    rick.introduce() // Вызов метода introduce() для объекта rick
    rick.catchphrase() // Вызов метода catchphrase() для объекта rick

    println("\nИмя второго персонажа: ${morty.name}")
    morty.introduce() // Вызов метода introduce() для объекта morty
    morty.catchphrase() // Вызов метода catchphrase() для объекта morty

    println("\nИмя третьего персонажа: ${birdperson.name}")
    birdperson.introduce() // Вызов метода introduce() для объекта birdperson
    birdperson.catchphrase() // Вызов метода catchphrase() для объекта birdperson

    // 6. Класс vs Объект (Экземпляр)
    // Класс 'Character' — это общая концепция "персонажа" (шаблон).
    // Объекты 'rick', 'morty' и 'birdperson' — это конкретные персонажи со своими уникальными именами и видами.
    println("\nКласс 'Character' — это чертеж, а 'rick', 'morty' и 'birdperson' — это созданные по нему объекты.")
}
