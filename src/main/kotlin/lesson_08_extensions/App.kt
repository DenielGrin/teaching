package lesson_08_extensions

// Импортируем классы из предыдущих уроков для демонстрации
import lesson_01_intro.Character // Наш класс Character из первого урока
import lesson_07_special_classes.Episode // Наш data class Episode

// 1. Функция расширения для String
// Добавляет метод 'toRickAndMortyCaps()' к классу String.
// 'this' внутри функции расширения ссылается на объект, для которого она вызвана.
fun String.toRickAndMortyCaps(): String {
    return this.uppercase() + "!" // Переводим строку в верхний регистр и добавляем "!"
}

// 2. Функция расширения для класса Character
// Добавляет метод 'travelToDimension()' к нашему классу Character.
// Предположим, что у класса Character изначально не было такого метода.
fun Character.travelToDimension(newDimension: String) {
    println("${this.name} (из ${this.species}) использует портальную пушку и попадает в измерение '$newDimension'.")
    // Если бы у Character было изменяемое свойство 'currentDimension', мы бы могли изменить его:
    // this.currentDimension = newDimension
}

// 3. Функция расширения для списка Episodes
// Добавляет метод 'printAllTitles()' к List<Episode>.
fun List<Episode>.printAllTitles() {
    println("--- Список эпизодов: ---")
    if (this.isEmpty()) {
        println("Список пуст.")
        return
    }
    for (i in this.indices) {
        println("${i + 1}. ${this[i].getFullTitle()}")
    }
    println("-----------------------")
}

// 4. Функция расширения, возвращающая новое значение
fun String.reverseAndAddPortalEffect(): String {
    return this.reversed() + " (эффект портала)"
}

fun main() {
    println("--- Урок 8: Функции Расширения ---")

    // --- Демонстрация функции расширения для String ---
    println("\n--- Расширение String ---")
    val rickQuote = "Рик: Вубба Лубба Даб Даб"
    println("Оригинальная фраза: $rickQuote")
    println("Фраза в стиле Рика: ${rickQuote.toRickAndMortyCaps()}")

    val mortyQuote = "Морти: О, боже, Рик!"
    println("Фраза с эффектом портала: ${mortyQuote.reverseAndAddPortalEffect()}")

    // --- Демонстрация функции расширения для Character ---
    println("\n--- Расширение Character ---")
    // Создаем экземпляр Character (из урока 1)
    val rickC137 = Character(name = "Рик C-137", species = "Человек")
    val mortyC137 = Character(name = "Морти C-137", species = "Человек")

    rickC137.introduce() // Обычный метод класса Character
    rickC137.travelToDimension(newDimension = "Измерение Флюбовых Людей") // Вызов функции расширения!

    mortyC137.introduce()
    mortyC137.travelToDimension(newDimension = "Планета Кроненбергов")

    // --- Демонстрация функции расширения для List<Episode> ---
    println("\n--- Расширение List<Episode> ---")
    val episode1 = Episode(season = 1, episodeNumber = 1, title = "Пилот", airDate = "02.12.2013")
    val episode2 = Episode(season = 1, episodeNumber = 2, title = "Газорпазорп", airDate = "09.12.2013")
    val episode3 = Episode(season = 2, episodeNumber = 3, title = "Анатомический парк", airDate = "10.08.2014")

    val allEpisodes = listOf(episode1, episode2, episode3)
    allEpisodes.printAllTitles() // Вызов функции расширения для списка

    val emptyEpisodesList = emptyList<Episode>()
    emptyEpisodesList.printAllTitles()
}
