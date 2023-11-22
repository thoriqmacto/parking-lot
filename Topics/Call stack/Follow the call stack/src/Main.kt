fun printIfPrime(number: Int) {
    var flag = false
    // write here
    for(i in 2.. number/2){
        if (number % i == 0){
            flag = true
            break
        }
    }

    if(!flag){
        println("$number is a prime number.")
    }else{
        println("$number is not a prime number.")
    }
}

fun main(args: Array<String>) {
    // write here
    val number = readln().toInt()
    printIfPrime(number)
}