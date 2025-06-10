package lesson_09_nested_inner

// 1. Внешний класс
class Spaceship(
    val name: String,
    private val capacity: Int
) { // 'capacity' - приватное свойство

    fun displayInfo() {
        println("Космический корабль: $name, вместимость: $capacity пассажиров.")
    }

    // 2. Вложенный класс (Nested Class)
    // Вложенный класс не имеет доступа к членам внешнего класса.
    // Он подобен обычному классу, просто объявлен внутри другого для организации.
    // Для создания его экземпляра нужен объект внешнего класса, но он не привязан к его состоянию.
    class NavigationSystem(val targetDimension: String) {
        fun plotCourse() {
            // ОШИБКА КОМПИЛЯЦИИ: Невозможно получить доступ к 'name' или 'capacity' из Spaceship
            // println("Прокладываю курс для ${this@Spaceship.name} к $targetDimension.")
            println("Прокладываю курс к измерению '$targetDimension'.")
        }

        fun calibrate() {
            println("Навигационная система калибруется.")
        }
    }

    // 3. Внутренний класс (Inner Class)
    // Внутренний класс объявляется с ключевым словом 'inner'.
    // Он имеет доступ к членам внешнего класса, включая приватные.
    // Для создания его экземпляра ВСЕГДА требуется экземпляр внешнего класса.
    inner class PassengerBay(private var currentPassengers: Int = 0) {
        fun addPassengers(count: Int) {
            if (currentPassengers + count <= capacity) { // Доступ к 'capacity' из внешнего класса
                currentPassengers += count
                println("$count пассажиров сели на борт $name. Всего: $currentPassengers.")
            } else {
                println("Недостаточно места на корабле $name. Вместимость $capacity.")
            }
        }

        fun removePassengers(count: Int) {
            if (currentPassengers - count >= 0) {
                currentPassengers -= count
                println("$count пассажиров покинули $name. Осталось: $currentPassengers.")
            } else {
                println("Невозможно удалить столько пассажиров. На борту: $currentPassengers.")
            }
        }

        fun getShipName(): String {
            return this@Spaceship.name // Доступ к 'name' внешнего класса
        }
    }
}

// 4. Демонстрация использования
fun main() {
    println("--- Урок 9: Вложенные и Внутренние Классы ---")

    println("\n--- Работа с Вложенным Классом (Nested Class) ---")
    // Создаем экземпляр вложенного класса напрямую, без объекта Spaceship
    val galacticNav = Spaceship.NavigationSystem(targetDimension = "Измерение Сквончи")
    galacticNav.plotCourse()
    galacticNav.calibrate()

    // Можно создать несколько экземпляров NavigationSystem, не привязанных к конкретному Spaceship
    val anotherNav = Spaceship.NavigationSystem(targetDimension = "Галактический Центр")
    anotherNav.plotCourse()

    println("\n--- Работа с Внутренним Классом (Inner Class) ---")
    val burpship = Spaceship(name = "БурпШип", capacity = 10)
    burpship.displayInfo()

    // Для создания экземпляра Inner Class нужен экземпляр внешнего класса
    val burpshipPassengers = burpship.PassengerBay() // Создаем PassengerBay для burpship
    burpshipPassengers.addPassengers(count = 5)
    burpshipPassengers.addPassengers(count = 7) // Попытка добавить больше, чем capacity
    burpshipPassengers.removePassengers(count = 3)

    println("Имя корабля из PassengerBay: ${burpshipPassengers.getShipName()}")

    // Создаем другой корабль и его PassengerBay
    val plumbusHauler = Spaceship(name = "Пламбус-такси", capacity = 2)
    val plumbusPassengers = plumbusHauler.PassengerBay()
    plumbusPassengers.addPassengers(count = 1)
    println("Имя корабля из другого PassengerBay: ${plumbusPassengers.getShipName()}")
}
