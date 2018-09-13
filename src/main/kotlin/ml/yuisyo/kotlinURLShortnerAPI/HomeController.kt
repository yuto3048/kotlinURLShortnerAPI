package ml.yuisyo.kotlinURLShortnerAPI

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class HomeController {
    @GetMapping("")
    fun index(): String = "<h1>URL shortener"

    @GetMapping("/json")
    fun json(): Map<String, String> = mapOf("url" to "https://yuisyo.ml/" + rndText())

    private fun rndText(): String{
        val charSet = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val rand = Random()
        var text:String = ""
        for (i in 0 .. 5) {
            text += charSet.get(rand.nextInt(charSet.length))
        }
        return text
    }
}
