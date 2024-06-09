package level3



// level2
fun main() {

//    val calculator = Calculator2()

//    val plus = calculator.plus(3.0, 2.2)

//    println("plus = ${plus}")

//    calculator.execute()
//    calculator.execute(AddOperation())
}


open class Calculator2(val opertation: Calculator) {


    open fun execute() {
        print("숫자를 입력하세요 : ")
        var result: Any = readLine()!!.toDouble()
//        var isContinue = true
        while(true) {
            print("연산자를 입력하세요 (1 : +, 2 : -, 3 : *, 4 : /, 5 : %, -1 : 종료) : ")
            val operator = readLine()
            if(operator == "-1")
                return
            print("숫자를 입력하세요 : ")
            val input2 = readLine()!!.toDouble()
            if(result is Int)
                result = result.toDouble()

            result = when (operator) {

//                "1" -> plus(result as Double, input2)
                "1" -> this.opertation.execute(result as Double, input2)
                "2" -> minus(result as Double, input2)
                "3" -> multiple(result as Double, input2)
                "4" -> divide(result as Double, input2)
                "5" -> modular(result as Double, input2)
                else -> {
                    println("연산자를 정확히 입력하세요.(1~5)")
                    return
                }
            }
            println("= ${result}")
//            print("연산을 계속 하시겠습니까?(종료를 원할시 -1 입력)")
//            isContinue = (readlnOrNull()?:"0") != "-1" // numberFormatException 처리
        }
    }

    open fun execute(input1: Double, input2: Double): Any {
        return "calculator's execute"
    }

    fun plus(input1: Double, input2: Double): Any {
        val result =  input1 + input2

        if(result - result.toInt() == 0.0)
            return result.toInt()

        return result
    }

    fun minus(input1: Double, input2: Double): Any {
        val result = input1 - input2

        if(result - result.toInt() == 0.0)
            return result.toInt()

        return result
    }

    fun multiple(input1: Double, input2: Double): Any {
        val result = input1 * input2

        if(result - result.toInt() == 0.0)
            return result.toInt()
        return result
    }

    fun divide(input1: Double, input2: Double): Any {
        val result = input1 / input2

        if(result - result.toInt() == 0.0)
            return result.toInt()

        return result
    }

    fun modular(input1: Double, input2: Double): Any {
        val result = input1 % input2

        if(result - result.toInt() == 0.0)
            return result.toInt()

        return result
    }

}