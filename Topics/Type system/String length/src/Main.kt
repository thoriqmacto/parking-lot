fun getLength(input: String?): Int? {
    return input?.length ?: -1
}

fun funk(n:Int):Int {

    var a = 1
    var b = 1
    var x = 0

    for (j in 2 until n) {
        x = a + b
        a = b
        b = x
    }
    return x
}

fun main(){
    println(funk(10))
}

