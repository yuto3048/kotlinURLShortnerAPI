package ml.yuisyo.kotlinURLShortnerAPI

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.lang.Runtime.*
import java.util.*

@RestController
class HomeController {
    @GetMapping("")
    fun index(): String = """
        <h1>URL shortener</h1>
        <form action="test" method="get">
            <input type="text" value="url">
            <input type="submit" value="send">
        </form>
        """.trimIndent()

    @GetMapping("/json")
    fun json(@RequestParam("url") url: String): Map<String, String> {
        val text = rndText()
        val file = FileWriter("/home/yuto/test.conf.d/$text.conf")
        val pw = PrintWriter(BufferedWriter(file))
        pw.println()
        pw.println("rewrite ^/$text(.*)\$ $url permanent;")
        pw.close()
        getRuntime().exec("sudo systemctl restart nginx.service")
        return mapOf("original url" to url, "url" to "http://localhost/" + text)
    }

    @GetMapping("/test")
    fun test(@RequestParam("url") url: String): String {
        val text = rndText()
        val file = FileWriter("/home/yuto/test.conf.d/$text.conf")
        val pw = PrintWriter(BufferedWriter(file))
        pw.println()
        pw.println("rewrite ^/$text(.*)\$ $url permanent;")
        pw.close()
        getRuntime().exec("sudo systemctl restart nginx.service")
        return "http://localhost/" + text
    }

    private fun rndText(): String {
        val charSet = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val rand = Random()
        var text = ""
        for (i in 0 .. 5) {
            text += charSet.get(rand.nextInt(charSet.length))
        }
        return text
    }
}
