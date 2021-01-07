package com.gamerole.plugin

object BuildConfig {
    val isTest = true

    val compileSdkVersion = 30
    val minSdkVersion = 21
    val targetSdkVersion = 30
    var applicationId = "com.gamerole.base"


    var versionCode = 100
    var versionName = "1.0.0"
    var appName = "Structure"
    var apkName = "Structure-正式"

    var baseUrl = "http://liyouedu.cn/"

    init {
        if (isTest) {
            versionCode = 100
            versionName = "1.0.0"
            appName = "Structure"
            apkName = "Structure-测试"
            baseUrl = "http://wmtest1207.weimansoft.vip/"
            applicationId = "com.gamerole.base.test"
        }
    }

}