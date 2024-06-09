package level5

abstract class AbstractOperation {

    abstract fun add(input1: Double, input2: Double): Number
    abstract fun subtract(input1: Double, input2: Double): Number
    abstract fun multiply(input1: Double, input2: Double): Number
    abstract fun divide(input1: Double, input2: Double): Number
    abstract fun calculate(input1: Double, input2: Double, operator: String): Number
    abstract fun calculateEntireQuation(splitedEquation: MutableList<String>): Number
    abstract fun calculateEntireQuationIncludedBracket(equation: String): Number
    abstract fun splitNumbersAndOperators(equation: String): List<String>

}