package level5

import java.util.*

class CalculatorCopy : AbstractOperation() {

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
//        println("splitedEquation = ${splitedEquation}")
        for(splitElement in splitedEquation) {

//            println("splitElement = ${splitElement}")
            if(splitElement in setOf("(",")")) {
                if(numbersAndOperators.size == 0)
                    continue

//                val equationSB = StringBuilder()
//                val endIndex = when(splitElement) {
//                    "(" -> numbersAndOperators.size - 2 // ((( 이면 어떻게?
//                    else -> numbersAndOperators.size - 1
//                }
//
//                for(numberOrOperatorIndex in 0..endIndex) {
//                    equationSB.append(numbersAndOperators[numberOrOperatorIndex])
//                }
                val lastOperator = numbersAndOperators[numbersAndOperators.size-1]
                if(splitElement == "(")
                    numbersAndOperators.removeAt(numbersAndOperators.size-1)


//                println("equationSB = ${equationSB}")
//                val partResult = calculateEntireQuation(equationSB.toString())
//                println("numbersAndOperators = ${numbersAndOperators}, ${lastOperator}")
                val partResult = calculateEntireQuation(numbersAndOperators)
                if(equationStack.isNotEmpty() && equationStack.peek() !in setOf("+","-","*","/"))
                    equationStack.push("+")

                equationStack.push(partResult.toString())

                if(splitElement == "(")
                    equationStack.push(lastOperator)
//                    equationStack.push(numbersAndOperators[numbersAndOperators.size-1])
//                println("after numbersAndOperators = ${numbersAndOperators}")
//                println("---------")

                numbersAndOperators.clear()
            } else
                numbersAndOperators.add(splitElement)
        }

//        println("stack~~~")



        for(numberAndOrperator in numbersAndOperators) {
            println("numberAndOrperator = ${numberAndOrperator}")
            equationStack.push(numberAndOrperator)
        }

//        while (equationStack.isNotEmpty()) {
//            print("${equationStack.pop()}, ")
//        }

        val toList = equationStack.toMutableList()
        println("toList.size = ${toList.size}")
        for (element in toList) {
            print("${element}, ")
        }

        var answer = calculateEntireQuation(toList).toDouble()

//        var answer = equationStack[0].toDouble()
//        var stackIndex = 1
        println("stack size = ${equationStack.size}")
//        while(stackIndex < equationStack.size) {
//            val numberOrOperator = equationStack[stackIndex]
//            println("numberOrOperator = ${numberOrOperator}")
//            if (numberOrOperator in setOf("+", "-")) {
//                val input = equationStack[stackIndex++].toDouble()
//                answer = calculate(answer, input, numberOrOperator).toDouble()
//            }
//        }

        if (answer - answer.toInt() == 0.0)
            return answer.toInt()

        return answer
    }

    override fun calculateEntireQuation(splitedEquation: MutableList<String>): Number {
//        var a = "a" + "b"
//        val splitedEquation = splitNumbersAndOperators(equation)
//        for (equation in splitedEquation) {
//            print("${equation}, ")
//        }
//        println()
        val equationStack = Stack<String>()
//        if(splitedEquation[0] in setOf("+", "-")) {
//            if(splitedEquation[0] == "-")
//                splitedEquation[1] = "-" + splitedEquation[1]
//
//            splitedEquation.removeAt(0)
//        }


        if(splitedEquation[0] in setOf("+", "-")) {
            splitedEquation.add(0, "0")
        }

        var splitedIndex = 0
        while (splitedIndex < splitedEquation.size) {
            val numberOrOperator = splitedEquation[splitedIndex]
            if (numberOrOperator in setOf("*", "/")) {
                val nextNumber = splitedEquation[splitedIndex + 1]
                val input = equationStack.pop()
                val result = calculate(input.toDouble(), nextNumber.toDouble(), numberOrOperator)
                equationStack.push(result.toString())
                splitedIndex++
            } else
                equationStack.push(numberOrOperator)

            splitedIndex++
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

//        var answer = 0.0

//        for(equation in equationStack.elements()) {
//            if (equation !in setOf("+","-","*","/"))
//                answer += equation.toDouble()
//        }


        println("answer = ${answer}")
        if (answer - answer.toInt() == 0.0)
            return answer.toInt()
        return answer

    }


    override fun splitNumbersAndOperators(equation: String): List<String> {

//        val numbersAndOperators = mutableListOf<String>()
//        val operators = mutableListOf('+', '-', '*', '/')
//        val brackets = mutableListOf('(',')')
//        var startIndex = 0
//
//
//        for (equationIndex in equation.indices) {
//            val charOfEquation = equation[equationIndex]
//            if (operators.contains(charOfEquation)) {
//                numbersAndOperators.add(equation.substring(startIndex, equationIndex))
//                numbersAndOperators.add(charOfEquation.toString())
//                startIndex = equationIndex + 1
//            } else if(charOfEquation == '(')  {
//                numbersAndOperators.add(charOfEquation.toString())
//                startIndex = equationIndex + 1
//            } else if(charOfEquation == ')') {
//                val beforeNumber = equation.substring(startIndex, equationIndex)
//                if(beforeNumber == " ")
//                    continue
//                numbersAndOperators.add(beforeNumber)
//                numbersAndOperators.add(charOfEquation.toString())
//                startIndex = equationIndex + 1
//            }
//        }
//
//        numbersAndOperators.add(equation.substring(startIndex, equation.length))

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