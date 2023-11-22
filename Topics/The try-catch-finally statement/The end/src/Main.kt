fun solution() {
    // write here
    val first = readln().toInt()
    val second = readln().toInt()

    try {
        val result = first / second
        println(result)
    }catch (e: Exception){
        println(e.message)
    }finally {
        println("This is the end, my friend.")
    }
}
//
//fun main(){
//    solution()
//}