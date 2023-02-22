package com.example.flutter_random

import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import kotlin.random.Random

class MainActivity : FlutterActivity() {
    private val CHANNEL = "seanglay.random"
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
                call,
                result ->
            if (call.method == "getRandomNumber") {
                val rand = Random.nextInt(100)
                result.success(rand)
            } else if (call.method == "getRandomString") {
                val rand = ('a'..'z').shuffled().take(4).joinToString("")
                result.success(rand)
            } else {
                result.notImplemented()
            }
        }
    }
}
