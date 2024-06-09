package level5

import java.util.*

fun main() {

//    val calculator = Calculator()
//
//    calculator.execute()

//    val regex1 = "^[0-9\\+\\-\\*\\/]*$"
    val regex1 = "^[0-9]*$"
    val equation = "5*3+145/22-3"

    val splitedEquation = equation.split(regex1.toRegex())

    println(splitedEquation.toString())

}