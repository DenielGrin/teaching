package lesson_05_visibility

// 1. Модификатор 'public' (по умолчанию)
// Члены класса 'public' доступны отовсюду.
// Если модификатор не указан, он по умолчанию 'public'.
class PublicCharacter(val name: String) {
    fun greet() {
        println("Привет, я $name (public).")
    }
}

// 2. Модификатор 'private'
// Члены класса 'private' доступны только внутри класса, в котором они объявлены.
class PrivateLab {
    private val secretFormula: String = "Концентрированная темная материя"
    private fun activateSelfDestruct() {
        println("Запущено самоуничтожение лаборатории!")
    }

    fun revealSecret() {
        println("Позвольте мне раскрыть часть секрета...")
        // Доступ к private членам внутри класса разрешен
        println("Формула: $secretFormula")
        // activateSelfDestruct() // Закомментировано, чтобы случайно не вызвать самоуничтожение :)
    }
}

// 3. Модификатор 'protected'
// Члены класса 'protected' доступны внутри класса и в его подклассах.
// Недоступны вне пакета или для других классов.
open class AlienBeing(val species: String) {
    protected val secretPower: String = "Телепатия"

    protected fun displaySecretPower() {
        println("$species обладает скрытой силой: $secretPower.")
    }

    fun introduce() {
        println("Я $species, существо из космоса.")
    }
}

class AdvancedAlien(
    species: String,
    val techLevel: Int
) : AlienBeing(species = species) {
    fun showAbilities() {
        println("$species с уровнем технологий $techLevel.")
        // Доступ к protected членам родительского класса разрешен
        displaySecretPower()
    }
}

// 4. Модификатор 'internal'
// Члены класса 'internal' доступны в пределах одного модуля.
// Модуль — это набор Kotlin-файлов, скомпилированных вместе (например, Maven или Gradle проект).
internal class ModuleGadget(val type: String) {
    fun useGadget() {
        println("Используется гаджет типа: $type.")
    }
}

fun main() {
    println("--- Урок 5: Модификаторы Видимости и Инкапсуляция ---")

    // --- Public ---
    println("\n--- Public Character ---")
    val morty = PublicCharacter(name = "Морти")
    morty.greet() // Доступно отовсюду

    // --- Private ---
    println("\n--- Private Lab ---")
    val lab = PrivateLab()
    lab.revealSecret() // Мы можем вызвать public метод, который внутри использует private члены
    // lab.secretFormula // ОШИБКА КОМПИЛЯЦИИ: 'secretFormula' - private
    // lab.activateSelfDestruct() // ОШИБКА КОМПИЛЯЦИИ: 'activateSelfDestruct' - private

    // --- Protected ---
    println("\n--- Protected Alien ---")
    val cronenberg = AlienBeing(species = "Кроненберг")
    cronenberg.introduce()
    // cronenberg.secretPower // ОШИБКА КОМПИЛЯЦИИ: 'secretPower' - protected
    // cronenberg.displaySecretPower() // ОШИБКА КОМПИЛЯЦИИ: 'displaySecretPower' - protected

    val mortyJr = AdvancedAlien(
        species = "Морти-младший (Газорпиец)",
        techLevel = 7
    )
    mortyJr.introduce()
    mortyJr.showAbilities() // Метод 'showAbilities' имеет доступ к 'protected' членам родителя

    // --- Internal ---
    println("\n--- Internal Gadget ---")
    val portalDevice = ModuleGadget(type = "Портальное устройство")
    portalDevice.useGadget() // Доступно, так как находится в том же модуле (в нашем проекте)

    // Если бы ModuleGadget был в другом модуле, он был бы недоступен без явного импорта или был бы приватным.
    // Например, если бы мы разделили App.kt и ModuleGadget в разные Gradle-модули.
}
