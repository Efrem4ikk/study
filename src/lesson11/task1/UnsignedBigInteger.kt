package lesson11.task1

import java.math.BigInteger

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

    private val number: BigInteger

    /**
     * Конструктор из строки
     */
    constructor(s: String) {
        this.number = BigInteger(s)
    }

    /**
     * Конструктор из целого
     */
    constructor(i: Int) {
        this.number = BigInteger(i.toString())
    }

    /**
     * Сложение
     */
    operator fun plus(other: UnsignedBigInteger): UnsignedBigInteger =
        UnsignedBigInteger((this.number + other.number).toString())

    /**
     * Вычитание (бросить ArithmeticException, если this < other)
     */
    operator fun minus(other: UnsignedBigInteger): UnsignedBigInteger {
        if (this < other) {
            throw ArithmeticException()
        }
        return UnsignedBigInteger((this.number - other.number).toString())
    }

    /**
     * Умножение
     */
    operator fun times(other: UnsignedBigInteger): UnsignedBigInteger {
        when {
            other.equals(0) -> return UnsignedBigInteger(0)
            other.equals(1) -> return UnsignedBigInteger(this.number.toString())
        }
        return UnsignedBigInteger((this.number * other.number).toString())
    }

    /**
     * Деление
     */
    operator fun div(other: UnsignedBigInteger): UnsignedBigInteger {
        return when {
            other.equals(0) -> throw ArithmeticException()
            other.equals(1) -> UnsignedBigInteger(this.number.toString())
            else -> UnsignedBigInteger((this.number / other.number).toString())
        }
    }

    /**
     * Взятие остатка
     */
    operator fun rem(other: UnsignedBigInteger): UnsignedBigInteger {
        return when {
            other.equals(0) -> throw ArithmeticException()
            other.equals(1) -> UnsignedBigInteger(0)
            else -> UnsignedBigInteger((this.number % other.number).toInt())
        }
    }

    /**
     * Сравнение на равенство (по контракту Any.equals)
     */
    override fun equals(other: Any?): Boolean {
        if (other is UnsignedBigInteger) {
            return this.number == other.number
        }
        return false
    }

    /**
     * Сравнение на больше/меньше (по контракту Comparable.compareTo)
     */
    override fun compareTo(other: UnsignedBigInteger): Int {
        when {
            this.number.equals(other.number) -> return 0
            this.number > (other.number) -> return 1
            this.number < (other.number) -> return -1
        }
        throw ArithmeticException()
    }

    /**
     * Преобразование в строку
     */
    override fun toString(): String = this.number.toString()

    /**
     * Преобразование в целое
     * Если число не влезает в диапазон Int, бросить ArithmeticException
     */
    fun toInt(): Int =
        this.toString().toIntOrNull() ?: throw ArithmeticException()
}