package parking

fun findFreeSpotIndex(muListCarSpot:MutableList<Boolean>):Int{
    for(i in muListCarSpot.indices){
        if(!muListCarSpot[i]){
            return i
        }
    }

    return 99
}

fun isParkEmpty(muListCarSpot: MutableList<Boolean>):Boolean{
    var trueCount = 0
    for (i in muListCarSpot.indices){
        if(!muListCarSpot[i]){
            trueCount++
        }
    }

    if (trueCount == muListCarSpot.size){
        return true
    }

    return false
}

fun createSpot(muListCarSpot: MutableList<Boolean>,totalSpot:Int){
    muListCarSpot.clear()
    repeat(totalSpot){
        muListCarSpot.add(false)
    }
    println("Created a parking lot with $totalSpot spots.")
}

fun main() {
    val carSpot = mutableListOf<Boolean>()
    val parkStatus = mutableListOf<String>()

    do {
        val userInput = readln()

        if (carSpot.isNotEmpty()) {
            if (userInput.contains("park")) {
                val wordList = userInput.split(" ")
                val carColor = wordList[2].lowercase()
                val firstLetterCarColor: Char = carColor[0].uppercaseChar()
                val validCarColor = carColor.replace(carColor[0], firstLetterCarColor)

                val freeSpotIndex = findFreeSpotIndex(carSpot)

                if (freeSpotIndex != 99) {
                    val validSpot = freeSpotIndex + 1
                    carSpot[freeSpotIndex] = true

                    parkStatus.add(validSpot.toString() + " " + wordList[1] + " " + validCarColor)

                    println("$validCarColor car parked in spot $validSpot.")
                } else {
                    println("Sorry, the parking lot is full.")
                }

            } else if (userInput.contains("leave")) {
                val wordList = userInput.split(" ")
                val numSpot = wordList[1].toInt()
                val idx = numSpot - 1

                if (carSpot[idx]) {
                    carSpot[idx] = false

                    parkStatus.removeAt(idx)

                    println("Spot $numSpot is free.")
                } else {
                    println("There is no car in spot $numSpot.")
                }
            } else if(userInput.contains("status")){
                if (!isParkEmpty(carSpot)) {
                    println(parkStatus.joinToString("\n"))
                }else{
                    println("Parking lot is empty.")
                }
            } else if(userInput.contains("create")){
                parkStatus.clear()

                val wordList = userInput.split(" ")
                val totalSpot = wordList[1].toInt()
                createSpot(carSpot,totalSpot)
            } else if(userInput.contains("spot_by_")){
                val wordList1 = userInput.split(" ")
                val wordList2 = wordList1[0].split("_")
                when(wordList2[2]){
                    "color" -> println(querySpotByColor(parkStatus, wordList1[1]))
                    "reg"   -> println(querySpotByReg(parkStatus, wordList1[1]))
                }
            } else if(userInput.contains("reg_by_")){
                println(queryRegByColor(parkStatus, userInput.split(" ")[1]))
            }
        }else{
            if (userInput.contains("park") ||
                userInput.contains("leave") ||
                userInput.contains("status") ||
                userInput.contains("spot_by_") ||
                userInput.contains("reg_by_")){
                println("Sorry, a parking lot has not been created.")
            }else if(userInput.contains("create")){
                val wordList = userInput.split(" ")
                val totalSpot = wordList[1].toInt()
                createSpot(carSpot,totalSpot)
            }
        }
    }while(userInput != "exit")
}

fun queryRegByColor(muListCarSpot: MutableList<String>, regString:String):String{
    val resultList = mutableListOf<String>()
    val lowerCaseRegString = regString.lowercase()
    val validRegString = lowerCaseRegString.replace(lowerCaseRegString[0],lowerCaseRegString[0].uppercaseChar())

    for (i in muListCarSpot.indices){
        if (muListCarSpot[i].toString().contains(validRegString)){
            val wordList = muListCarSpot[i].toString().split(" ")
            val regCode = wordList[1]
            resultList.add(regCode)
        }
    }

    return if (resultList.isNotEmpty()){
        resultList.joinToString(", ")
    }else{
        "No cars with color $regString were found."
    }
}

fun querySpotByColor(muListCarSpot: MutableList<String>, s: String): String {
    val resultList = mutableListOf<String>()
    val lowerCaseColor = s.lowercase()
    val validColor = lowerCaseColor.replace(lowerCaseColor[0],lowerCaseColor[0].uppercaseChar())

    for (i in muListCarSpot.indices){
        val spottedColor = muListCarSpot[i].split(" ")[2]
        if (validColor == spottedColor) {
            resultList.add(muListCarSpot[i].split(" ")[0])
        }
    }

    return if (resultList.isNotEmpty()){
        resultList.joinToString(", ")
    }else{
        "No cars with color $s were found."
    }
}

fun querySpotByReg(muListCarSpot: MutableList<String>, s: String): String {
    for (i in muListCarSpot.indices){
        val spottedReg = muListCarSpot[i].split(" ")[1]
        if(s == spottedReg){
            return muListCarSpot[i].split(" ")[0]
        }
    }

    return "No cars with registration number $s were found."
}