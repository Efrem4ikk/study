package lesson11.task1

/**
 * Класс "беззнаковое большое целое число".
 *
 * Общая сложность задания -- очень сложная, общая ценность в баллах -- 32.
 * Объект класса содержит целое число без знака произвольного размера
 * и поддерживает основные операции над такими числами, а именно:
 * сложение, вычитание (при вычитании большего числа из меньшего бросается исключение),
 * умножение, деление, остаток от деления,
 * преобразование в строку/из строки, преобразование в целое/из целого,
 * сравнение на равенство и неравенство
 */
class UnsignedBigInteger : Comparable<UnsignedBigInteger> {

    private val number: MutableList<Int>

    /**
     * Конструктор из строки
     */
    constructor(s: String) {
        number = s.reversed().map { it.toInt() - '0'.toInt() }.toMutableList()
    }

    /**
     * Конструктор из целого
     */
    constructor(i: Int) {
        number = mutableListOf()
        var n = i
        while (n > 0) {
            number.add(n % 10)
            n /= 10
        }
    }

    /**
     * Сложение
     */
    operator fun plus(other: UnsignedBigInteger): UnsignedBigInteger {
        val result = UnsignedBigInteger("")
        var remains = 0
        for (i in 0 until maxOf(this.number.size, other.number.size)) {
            val sum = remains +
                    (if (i < this.number.size) number[i] else 0) +
                    (if (i < other.number.size) other.number[i] else 0)
            result.number.add(sum % 10)
            remains = sum / 10
        }
        if (remains > 0) {
            result.number.add(remains)
        }
        return result
    }

    /**
     * Вычитание (бросить ArithmeticException, если this < other)
     */
    operator fun minus(other: UnsignedBigInteger): UnsignedBigInteger {
        if (this < other) throw ArithmeticException()
        val result = UnsignedBigInteger("")
        var weBorrow = false
        for (i in 0 until this.number.size) {
            var diff = if (weBorrow) this.number[i] - 1 else this.number[i]
            if (i < other.number.size) {
                diff -= other.number[i]
            }
            if (diff < 0) {
                diff += 10
                weBorrow = true
            } else {
                weBorrow = false
            }
            result.number.add(diff)
        }
        return result
    }

    /**
     * Умножение
     */
    operator fun times(other: UnsignedBigInteger): UnsignedBigInteger {
        if (other.number == mutableListOf(0)) return UnsignedBigInteger(0)
        val ans = MutableList(this.number.size + other.number.size + 1) { 0 }
        val a = this.number.size
        val b = other.number.size
        for (i in 0 until a) {
            var remains = 0
            for (j in 0 until b) {
                val product = this.number[i] * other.number[j] + remains + ans[a + b - i - j]
                ans[a + b - i - j] = product % 10
                println(ans)
                remains = product / 10
                if (j == b - 1) {
                    ans[a + b - i - j - 1] += remains
                }
            }
        }
        var result = ""
        for (i in 1 until ans.size) {
            if (ans[i].toString() in "0123456789") result += ans[i].toString()
        }
        return UnsignedBigInteger(result)
    }

    /**
     * Деление
     */
    operator fun div(other: UnsignedBigInteger): UnsignedBigInteger {
        when {
            other.number.equals(0) -> throw ArithmeticException()
            other.number.equals(1) -> return UnsignedBigInteger(this.number.toString())
        }
        TODO()
    }

    /**
     * Взятие остатка
     */
    operator fun rem(other: UnsignedBigInteger): UnsignedBigInteger = TODO()

    /**
     * Сравнение на равенство (по контракту Any.equals)
     */
    override fun equals(other: Any?): Boolean {
        if (other !is UnsignedBigInteger) return false
        if (other.number == this.number) return true
        return false
    }

    /**
     * Сравнение на больше/меньше (по контракту Comparable.compareTo)
     */
    override fun compareTo(other: UnsignedBigInteger): Int {
        when {
            this.number.equals(other.number) -> return 0
            this.number.size > other.number.size -> return 1
            this.number.size < other.number.size -> return -1
        }
        for (i in 0 until this.number.size) {
            when {
                this.number[i] > other.number[i] -> return 1
                this.number[i] < other.number[i] -> return -1
            }
        }
        throw ArithmeticException()
    }

    /**
     * Преобразование в строку
     */
    override fun toString(): String {
        val result = buildString {
            for (i in 0 until number.size) {
                append(number[i].toString())
            }
        }
        return result.reversed()
    }

    /**
     * Преобразование в целое
     * Если число не влезает в диапазон Int, бросить ArithmeticException
     */
    fun toInt(): Int {
        print(this.number)
        var ans = ""
        for (i in 0 until this.number.size) ans = this.number[i].toString() + ans
        return ans.toIntOrNull() ?: throw ArithmeticException()
    }
}