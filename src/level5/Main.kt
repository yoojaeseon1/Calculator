package level5

fun main() {

    val calculator = Calculator()

//    val equation = "100+3*15-123/2"
//    val equation = "3+(100*2+(3-2*100)-100)+5"
    val equation = "(3+2-(4-5))+3"
//    val equation = "3+((3+5)-2)"

//    print(calculator.splitNumbersAndOperators(equation).toString())
//    print(calculator.calculateEntireQuation(equation))
    println(calculator.calculateEntireEquationWithBracket(equation))

}