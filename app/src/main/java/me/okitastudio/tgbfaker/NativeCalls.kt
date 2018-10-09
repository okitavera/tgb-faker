package me.okitastudio.tgbfaker

@Suppress("unused")
object NativeCalls {
    private const val mBin = "/data/local/tmp/magisk"

    fun setProp(prop: String, value: String): Process =
            Runtime.getRuntime().exec(arrayOf(mBin, "resetprop", "-n", prop, value))

    fun unsetProp(prop: String): Process =
            Runtime.getRuntime().exec(arrayOf(mBin, "resetprop", "--delete", prop))

    fun invokeSU(a: String): Process =
            Runtime.getRuntime().exec(arrayOf("su", "-c", a))

    fun pidOf(name: String) =
            Runtime.getRuntime().exec(arrayOf("sh", "-c", "ps"))
                .inputStream.bufferedReader().readLines()
                .lastOrNull{ it.contains(Regex.escape(name).plus("$").toRegex()) }
                ?.replace("^\\w+\\s+(\\d+)\\s.*$".toRegex(),"$1") ?: "N/A"
}