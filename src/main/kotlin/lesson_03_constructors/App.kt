package lesson_03_constructors

import kotlin.random.Random

// Класс 'PortalGun' для демонстрации конструкторов
class PortalGun(val maxCharges: Int) { // Основной конструктор: принимает maxCharges

    // Свойство, инициализируемое основным конструктором
    var currentCharges: Int

    // Блок инициализации: выполняется после вызова основного конструктора
    // Здесь можно выполнять логику, которая нужна при создании каждого объекта
    init {
        require(maxCharges > 0) { "Максимальное количество зарядов должно быть больше нуля!" }
        currentCharges = maxCharges // Инициализируем текущие заряды по умолчанию
        println("Портальная пушка создана с $maxCharges максимальными зарядами.")
    }

    // Дополнительный конструктор 1: Дефолтные 50 зарядов
    constructor() : this(50) { // Делегирование основному конструктору
        println("Создана портальная пушка с дефолтными 50 зарядами.")
    }

    // Дополнительный конструктор 2: Случайное количество зарядов
    constructor(isRandom: Boolean) : this(if (isRandom) Random.nextInt(10, 101) else 10) {
        // Делегирование основному конструктору с условием
        println("Создана портальная пушка со случайным или минимальным количеством зарядов.")
    }

    // Метод для использования зарядов
    fun fire() {
        if (currentCharges > 0) {
            currentCharges--
            println("Портал открыт! Зарядов осталось: $currentCharges.")
        } else {
            println("Недостаточно зарядов для открытия портала.")
        }
    }

    // Метод для перезарядки
    fun recharge(amount: Int) {
        currentCharges = (currentCharges + amount).coerceAtMost(maxCharges)
        println("Пушка перезаряжена на $amount зарядов. Всего: $currentCharges/$maxCharges.")
    }
}

// Класс 'Rick' для демонстрации инъекции зависимости через конструктор
class Rick(val portalGun: PortalGun) { // Rick получает PortalGun через конструктор

    fun travelToRandomDimension() {
        if (portalGun.currentCharges > 0) {
            portalGun.fire()
            println("Рик перемещается в случайное измерение!")
        } else {
            println("Рик не может переместиться, портальная пушка разряжена!")
        }
    }
}

fun main() {
    println("--- Урок 3: Конструкторы и Инициализация Объектов ---")

    // Создание объекта с использованием основного конструктора
    println("\n--- Создание PortalGun через основной конструктор (150 зарядов) ---")
    val heavyDutyPortalGun = PortalGun(150)
    heavyDutyPortalGun.fire()
    heavyDutyPortalGun.fire()
    heavyDutyPortalGun.recharge(10)

    // Создание объекта с использованием дополнительного конструктора (дефолтное количество)
    println("\n--- Создание PortalGun через дополнительный конструктор (дефолтные 50 зарядов) ---")
    val standardPortalGun = PortalGun()
    standardPortalGun.fire()

    // Создание объекта с использованием дополнительного конструктора (случайное количество)
    println("\n--- Создание PortalGun через дополнительный конструктор (случайное количество) ---")
    val experimentalPortalGun = PortalGun(true)
    experimentalPortalGun.fire()
    experimentalPortalGun.fire()

    // Демонстрация класса Rick, который получает PortalGun через конструктор
    println("\n--- Рик и его портальные пушки ---")
    val rickWithHeavyDutyGun = Rick(heavyDutyPortalGun)
    rickWithHeavyDutyGun.travelToRandomDimension()
    rickWithHeavyDutyGun.travelToRandomDimension()

    val rickWithStandardGun = Rick(standardPortalGun)
    rickWithStandardGun.travelToRandomDimension()

    println("\n--- Демонстрация ошибок инициализации ---")
    try {
        // Попытка создать пушку с некорректным количеством зарядов
        val brokenPortalGun = PortalGun(0)
    } catch (e: IllegalArgumentException) {
        println("Ошибка: ${e.message}")
    }
}
