package me.okitastudio.tgbfaker

@Suppress("unused")
object TPatcher {
    const val ACTION_REPACTH = "ACT_REPATCH"

    fun fakeProps() {
        NativeCalls.setProp("ro.build.id","R16NW")
        NativeCalls.setProp("ro.product.model","SM-G965F")
        NativeCalls.setProp("ro.product.name","star2ltexx")
        NativeCalls.setProp("ro.build.product","star2lte")
        NativeCalls.setProp("ro.product.device","star2lte")
        NativeCalls.setProp("ro.product.brand","samsung")
        NativeCalls.setProp("ro.product.manufacturer","samsung")
        NativeCalls.setProp("ro.product.cpu.abi","arm64-v8a")
        NativeCalls.setProp("ro.product.cpu.abilist","arm64-v8a,armeabi-v7a,armeabi,x86")
        NativeCalls.setProp("ro.product.cpu.abilist32","armeabi-v7a,armeabi,x86")
        NativeCalls.setProp("ro.product.locale","en-GB")
        NativeCalls.setProp("ro.build.fingerprint","samsung/star2ltexx/star2lte:8.0.0/R16NW/G965FXXU1ARCC:user/release-keys")
        NativeCalls.setProp("ro.device.id","667bfd7b08bb71d2")
        NativeCalls.setProp("ro.build.characteristics","phone")
        NativeCalls.setProp("gsm.operator.alpha", "XL AXIATA")
        NativeCalls.setProp("gsm.operator.iso-country", "id")
        NativeCalls.setProp("gsm.operator.isroaming", "false")
        NativeCalls.setProp("gsm.operator.numeric", "8962118925001303502")
        NativeCalls.setProp("gsm.sim.operator.alpha", "XL AXIATA")
        NativeCalls.setProp("gsm.sim.operator.iso-country", "id")
        NativeCalls.setProp("gsm.sim.operator.numeric", "8962118925001303502")
        NativeCalls.unsetProp("testing.mediascanner.skiplist")
    }

    fun fakeNoGeny(function: (String) -> Unit = {}) {
        val getprop = NativeCalls.invokeSU("getprop").inputStream.bufferedReader().readLines()
        for (i in getprop) {
            if (i.contains("ro.geny")) {
                val prop = i.replace("\\[(.*)]: (.*)$".toRegex(),"$1")
                NativeCalls.unsetProp(prop)
                function.invoke(prop)
            }
        }
    }
}