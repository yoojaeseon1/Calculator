package level5

import java.util.*

class Calculator : AbstractOperation() {

    fun execute() {
        print("숫자를 입력하세요 : ")
        var result: Number = readLine()!!.toDouble()
        while (true) {
            print("연산자를 입력하세요 (1 : +, 2 : -, 3 : *, 4 : /, -1 : 종료) : ")
            val operator = readLine()
            if (operator == "-1")
                return

            print("숫자를 입력하세요 : ")
            val input2 = readLine()!!.toDouble()
            if (result is Int)
                result = result.toDouble()

            val calculator = Calculator()
            result = when (operator) {
                "1" -> calculator.add(result as Double, input2)
                "2" -> calculator.subtract(result as Double, input2)
                "3" -> calculator.multiply(result as Double, input2)
                "4" -> calculator.divide(result as Double, input2)
                else -> {
                    println("연산자를 정확히 입력하세요.(1~4)")
                    continue
                }
            }
            println("= ${result}")
        }
    }

    override fun add(input1: Double, input2: Double): Number {
        val result = input1 + input2
        if (result - result.toInt() == 0.0)
            return result.toInt()

        return result
    }

    override fun subtract(input1: Double, input2: Double): Number {
        val result = input1 - input2

        if (result - result.toInt() == 0.0)
            return result.toInt()

        return result
    }

    override fun multiply(input1: Double, input2: Double): Number {
        val result = input1 * input2

        if (result - result.toInt() == 0.0)
            return result.toInt()

        return result
    }

    override fun divide(input1: Double, input2: Double): Number {
        val result = input1 / input2

        if (result - result.toInt() == 0.0)
            return result.toInt()

        return result
    }

    override fun calculate(input1: Double, input2: Double, operator: String): Number {
        return when (operator) {
            "+" -> add(input1, input2)
            "-" -> subtract(input1, input2)
            "*" -> multiply(input1, input2)
            else -> divide(input1, input2) // "/"
        }
    }

    override fun calculateEntireQuationIncludedBracket(equation: String): Number {

        val splitedEquation = splitNumbersAndOperators(equation)
        val equationStack = Stack<String>()
        val numbersAndOperators = mutableListOf<String>()
        for(splitElement in splitedEquation) {
            if(splitElement in setOf("(",")")) {
                if(numbersAndOperators.size == 0)
                    continue
                val lastOperator = numbersAndOperators[numbersAndOperators.size-1]
                if(splitElement == "(")
                    numbersAndOperators.removeAt(numbersAndOperators.size-1)

                val partResult = calculateEntireQuation(numbersAndOperators)
                if(equationStack.isNotEmpty() && equationStack.peek() !in setOf("+","-","*","/"))
                    equationStack.push("+")

                equationStack.push(partResult.toString())

                if(splitElement == "(")
                    equationStack.push(lastOperator)

                numbersAndOperators.clear()
            } else
                numbersAndOperators.add(splitElement)
        }

        for(numberAndOperator in numbersAndOperators)
            equationStack.push(numberAndOperator)


        val toList = equationStack.toMutableList()
        for (element in toList) {
            print("${element}, ")
        }

        val answer = calculateEntireQuation(toList).toDouble()
        if (answer - answer.toInt() == 0.0)
            return answer.toInt()

        return answer
    }

    override fun calculateEntireQuation(splitedEquation: MutableList<String>): Number {
        val equationStack = Stack<String>()
        if(splitedEquation[0] in setOf("+", "-")) {
            splitedEquation.add(0, "0")
        }

        var splitIndex = 0
        while (splitIndex < splitedEquation.size) {
            val numberOrOperator = splitedEquation[splitIndex]
            if (numberOrOperator in setOf("*", "/")) {
                val nextNumber = splitedEquation[splitIndex + 1]
                val input = equationStack.pop()
                val result = calculate(input.toDouble(), nextNumber.toDouble(), numberOrOperator)
                equationStack.push(result.toString())
                splitIndex++
            } else
                equationStack.push(numberOrOperator)

            splitIndex++
        }

        var answer = equationStack[0].toDouble()
        var stackIndex = 1
        while(stackIndex < equationStack.size) {
            val numberOrOperator = equationStack[stackIndex++]
            if (numberOrOperator in setOf("+", "-")) {
                val input = equationStack[stackIndex++].toDouble()
                answer = calculate(answer, input, numberOrOperator).toDouble()
            }
        }
        if (answer - answer.toInt() == 0.0)
            return answer.toInt()
        return answer

    }


    override fun splitNumbersAndOperators(equation: String): List<String> {

        val numbersAndOperators = mutableListOf<String>()
        val operators = mutableListOf('+', '-', '*', '/')
        val brackets = mutableListOf('(',')')
        val numberSB = StringBuilder()

        for(equationChar in equation) {
            if(operators.contains(equationChar) || brackets.contains(equationChar)) {
                if(numberSB.isNotEmpty()) {
                    numbersAndOperators.add(numberSB.toString())
                    numberSB.clear()
                }
                numbersAndOperators.add(equationChar.toString())
            } else
                numberSB.append(equationChar)
        }

        if(numberSB.isNotEmpty())
            numbersAndOperators.add(numberSB.toString())

        return numbersAndOperators
    }
}

fun main() {

    val calculator = Calculator()

//    val equation = "100+3*15-123/2"
//    val equation = "3+(100*2+(3-2*100)-100)+5"
    val equation = "(3+2-(4-5))+3"
//    val equation = "3+((3+5)-2)"

//    print(calculator.splitNumbersAndOperators(equation).toString())
//    print(calculator.calculateEntireQuation(equation))
    println(calculator.calculateEntireQuationIncludedBracket(equation))

}