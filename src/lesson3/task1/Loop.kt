@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson4.task1.task1.sqr
import kotlin.math.*

// Урок 3: циклы
// Максимальное количество баллов = 9
// Рекомендуемое количество баллов = 7
// Вместе с предыдущими уроками = 16/21

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая (2 балла)
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var count = 0
    var num = abs(n)
    do {
        count += 1
        num /= 10
    } while (num > 0)
    return count
}

/**
 * Простая (2 балла)
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var answer = 1
    var prev = 1
    var preprev = 1
    for (i in 3..n) {
        answer = prev + preprev
        preprev = prev
        prev = answer
    }
    return answer
}


/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    return if (isPrime(n)) n else {
        val num = n.toDouble()
        for (i in 2..sqrt(num).toInt()) {
            if (n % i == 0) return i
        }
        return -1
    }
}

/**
 * Простая (2 балла)
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int = n / minDivisor(n)


/**
 * Простая (2 балла)
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var number = x
    var count = 0
    while (number != 1) {
        if (number % 2 == 0) number /= 2 else number = number * 3 + 1
        count += 1
    }
    return count
}

/**
 * Средняя (3 балла)
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var n1 = n
    var m1 = m
    when {
        n == m -> return n
        isPrime(m) && isPrime(n) -> return m * n
        else -> while (m1 != n1) if (n1 > m1) n1 -= m1 else m1 -= n1
    }
    return n * m / n1
}

/**
 * Средняя (3 балла)
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var n = n
    var m = m
    when {
        isPrime(m) && isPrime(n) -> return true
        else -> while (m != n)
            if (n > m) n -= m else m -= n
    }
    return n == 1
}

/**
 * Средняя (3 балла)
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var number = n
    var reversed = 0
    while (number > 0) {
        reversed = reversed * 10 + number % 10
        number /= 10
    }
    return reversed
}

/**
 * Средняя (3 балла)
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = n == revert(n)


/**
 * Средняя (3 балла)
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    val length = digitNumber(n)
    when (length) {
        2 -> return n / 10 != n % 10
        else -> for (i in 1..length - 2) {
            val prelast = (n / 10.0.pow(i)).toInt() % 10
            val last = (n / 10.0.pow(i + 1)).toInt() % 10
            if (prelast != last) {
                return true
            }
        }
    }
    return false
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    var sinus = x % (2 * PI)
    val argument = sinus
    var flag = false
    var i = 3
    var nextStep = argument.pow(i) / factorial(i)
    while (abs(nextStep) >= abs(eps)) {
        if (flag) {
            flag = false
            sinus += nextStep
        } else {
            flag = true
            sinus -= nextStep
        }
        i += 2
        nextStep = argument.pow(i) / factorial(i)
    }
    return sinus
}

/**
 * Средняя (4 балла)
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var cosinus = 1.0
    val argument = x % (2 * PI)
    var flag = false
    var i = 2
    var nextStep = argument.pow(i) / factorial(i)
    while (abs(nextStep) >= abs(eps)) {
        if (flag) {
            flag = false
            cosinus += nextStep
        } else {
            flag = true
            cosinus -= nextStep
        }
        i += 2
        nextStep = argument.pow(i) / factorial(i)
    }
    return cosinus
}

/**
 * Сложная (4 балла)
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var lengthOfLine = 0
    var i = 0
    var lastNumber = 0
    while (lengthOfLine < n) {
        i += 1
        lengthOfLine += digitNumber(sqr(i))
        lastNumber = sqr(i)
    }
    while (lengthOfLine != n) {
        lastNumber /= 10
        lengthOfLine -= 1
    }
    return lastNumber % 10
}

/**
 * Сложная (5 баллов)
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var lengthOfLine = 0
    var i = 0
    var lastNumber = 0
    while (lengthOfLine < n) {
        i += 1
        lengthOfLine += digitNumber(fib(i))
        lastNumber = fib(i)
    }
    while (lengthOfLine != n) {
        lastNumber /= 10
        lengthOfLine -= 1
    }
    return lastNumber % 10
}