package backend.api

import kotlinx.coroutines.*
import java.net.ConnectException
import kotlin.concurrent.thread
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

val preset = listOf("bebra", "bebrab", "bebrabb")

fun getRandomWord(wordLength: Int): String? {
    try {
        var response: HttpResponse<String>? = null
        runBlocking {
            val request = HttpRequest.newBuilder()
                .uri(URI.create("https://random-words5.p.rapidapi.com/getRandom?wordLength=$wordLength"))
                .header("X-RapidAPI-Key", "a86df08bacmshc5a4fb6f13649cep10c35ejsnbc1432c5b419")
                .header("X-RapidAPI-Host", "random-words5.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build()
            response = with(Dispatchers.IO) {
                HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString())
            }
            println("Random Word With Length $wordLength is ${response?.body()?.lowercase()}")

        }
        return response?.body()?.lowercase()
    } catch (e: ConnectException) {
        println("Did not receive")
        return null
    } catch (e: NullPointerException) {
        println("Null Pointer Exception")
        return null
    }
}
