package lesson_02_props_methods

// Класс 'Character' с расширенными свойствами и методами
class Character(
    val name: String,
    var dimension: String
) {

    // Неизменяемое свойство (val): Значение присваивается один раз и не может быть изменено.
    val origin: String = "Неизвестно" // Может быть инициализировано здесь или в конструкторе.

    // Изменяемое свойство (var): Значение может быть изменено после инициализации.
    var currentLocation: String = "Земля C-137"

    // Приватное свойство: Доступно только внутри этого класса.
    private var health: Int = 100

    // Пользовательский геттер для свойства 'status'
    // 'status' не хранит данные напрямую, а вычисляет их на основе 'health'.
    val status: String
        get() = if (health > 0) "Жив" else "Мертв"

    // Метод: Изменение локации
    fun changeLocation(newLocation: String) {
        println("$name перемещается из $currentLocation в $newLocation.")
        currentLocation = newLocation
    }

    // Метод: Получение урона
    fun takeDamage(amount: Int) {
        if (amount < 0) {
            println("Урон не может быть отрицательным!")
            return
        }
        health -= amount
        if (health < 0) health = 0 // Здоровье не может быть меньше нуля
        println("$name получил $amount урона. Текущее здоровье: $health. Статус: $status")
    }

    // Метод: Лечение
    fun heal(amount: Int) {
        if (amount < 0) {
            println("Лечение не может быть отрицательным!")
            return
        }
        health += amount
        if (health > 100) health = 100 // Здоровье не может быть больше 100
        println("$name вылечился на $amount. Текущее здоровье: $health. Статус: $status")
    }
}

fun main() {
    println("--- Урок 2: Свойства и Методы Классов ---")

    val rick = Character("Рик Санчез", "Измерение C-137")

    // Доступ к свойствам
    println("Имя: ${rick.name}")
    println("Исходное измерение: ${rick.dimension}")
    println("Начальная локация: ${rick.currentLocation}")
    println("Происхождение: ${rick.origin}") // Доступ к неизменяемому свойству

    // Изменение изменяемого свойства (var)
    rick.dimension = "Измерение С-137 B" // Изменяем свойство dimension
    println("Новое измерение: ${rick.dimension}")

    rick.currentLocation = "Планета Кроненбергов" // Изменяем свойство currentLocation
    println("Новая локация: ${rick.currentLocation}")

    // rick.name = "Рик-огурец" // ОШИБКА! name - val, его нельзя изменить после инициализации.
    // rick.origin = "Млечный Путь" // ОШИБКА! origin - val.

    println("\n--- Работа с методами ---")

    // Вызов методов
    rick.changeLocation("Цитадель Риков")
    println("Рик сейчас находится в: ${rick.currentLocation}")

    println("\n--- Управление здоровьем ---")
    println("Начальный статус Рика: ${rick.status}") // Используем пользовательский геттер
    rick.takeDamage(30)
    rick.takeDamage(50)
    rick.heal(40)
    rick.takeDamage(70) // Должен стать "Мертв"
    println("Финальный статус Рика: ${rick.status}")
    rick.heal(100) // Попробуем вылечить мертвого
    println("Финальный статус Рика после лечения: ${rick.status}")

    println("\n--- Еще один персонаж для демонстрации ---")
    val morty = Character("Морти Смит", "Земля C-137")
    morty.changeLocation("Измерение Флюбовых Людей")
    morty.takeDamage(10)
    morty.heal(5)
}