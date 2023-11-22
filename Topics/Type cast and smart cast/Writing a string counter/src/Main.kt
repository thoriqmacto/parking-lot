fun countStrings(list: List<Any>): Int {
    // make your code here
    var count = 0
    for(item in list){
        if(item is String){
            count++
        }
    }
    return count
}