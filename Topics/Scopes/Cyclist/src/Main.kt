fun main() {
    var distance = readln().toInt() // the distance back
    var totalDistance = readln().toInt()

    // fix the code below
    if (distance < 0) {
        distance *= -1
    }
    totalDistance += distance
    println(totalDistance)
}