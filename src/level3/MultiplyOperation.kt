package level3

class MultiplyOperation : Calculator() {

    override fun execute(input1: Double, input2: Double): Number{
        val result =  input1 * input2

        if(result - result.toInt() == 0.0)
            return result.toInt()

        return result
    }
}