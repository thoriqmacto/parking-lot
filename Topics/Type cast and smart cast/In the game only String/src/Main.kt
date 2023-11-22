fun <T> getStringsOnly(list: List<T>): List<String> {
    val result = mutableListOf<String>()
    // make your code here
    for (i in list.indices){
        if (list[i] is String){
            result.add(list[i].toString())
        }
    }
    return result
}