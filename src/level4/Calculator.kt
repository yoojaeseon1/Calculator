package level4

class Calculator : AbstractOperation() {

    fun execute() {
        print("숫자를 입력하세요 : ")
        var result: Number = readLine()!!.toDouble()
        while(true) {
            print("연산자를 입력하세요 (1 : +, 2 : -, 3 : *, 4 : /, -1 : 종료) : ")
            val operator = readLine()
            if(operator == "-1")
                return

            print("숫자를 입력하세요 : ")
            val input2 = readLine()!!.toDouble()
            if(result is Int)
                result = result.toDouble()

            val calculator = Calculator()
            result = when(operator){
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

}