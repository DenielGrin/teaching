package common // Важно: этот файл находится в пакете 'common'

// 1. Функция расширения для String
// Позволяет легко выводить сообщения в стиле Рика и Морти с тегом.
fun String.logWithTag(tag: String = "КОД УРОКА") {
    println("[$tag] $this")
}

// 2. Функция верхнего уровня (Top-Level Function)
// Эта функция не принадлежит ни одному классу. Она доступна напрямую в пакете 'common'.
// Мы можем использовать ее в любом месте, где импортирован пакет 'common'.
fun printSectionHeader(title: String) {
    println("\n--- $title ---")
}

// 3. Объект-утилита (Singleton Object)
// Если нам нужны утилиты, которые имеют состояние или более сложную логику,
// и которые не являются расширениями, можно использовать 'object'.
object DimensionUtils {
    private val knownDimensions = mutableListOf<String>()

    fun registerDimension(dimension: String) {
        if (dimension !in knownDimensions) {
            knownDimensions.add(dimension)
            "Новое измерение '$dimension' зарегистрировано.".logWithTag("DIMENSION_UTIL")
        } else {
            "Измерение '$dimension' уже известно.".logWithTag("DIMENSION_UTIL")
        }
    }

    fun listKnownDimensions() {
        "Известные измерения: ${knownDimensions.joinToString(", ")}".logWithTag("DIMENSION_UTIL")
    }
}
