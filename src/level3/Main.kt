package level3

fun main() {

    print("숫자를 입력하세요 : ")
    var result: Any = readLine()!!.toDouble()
    while(true) {
        print("연산자를 입력하세요 (1 : +, 2 : -, 3 : *, 4 : /, -1 : 종료) : ")
        val operator = readLine()
        if(operator == "-1")
            return

        print("숫자를 입력하세요 : ")
        val input2 = readLine()!!.toDouble()
        if(result is Int)
            result = result.toDouble()

        val calculator = when(operator){
            "1" -> Calculator(AddOperation())
            "2" -> Calculator(SubtractOperation())
            "3" -> Calculator(MultiplyOperation())
            "4" -> Calculator(DivideOperation())
            else -> {
                println("연산자를 정확히 입력하세요.(1~4)")
                continue
            }
        }

        result = calculator.operator!!.execute(result as Double, input2)
        println("= ${result}")
    }
}