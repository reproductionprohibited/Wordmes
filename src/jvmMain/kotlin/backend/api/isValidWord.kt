package backend.api

import java.net.ConnectException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse


/**
 * List of booleans:
 *      0 index: true = No Exception, false = Internet Exception,
 *      1 index: true = Word Exists, false = Word does not exist
 */
fun isValidWord(word: String): List<Boolean> {
    try {
        val request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.dictionaryapi.dev/api/v2/entries/en/$word"))
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build()
        val response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString())
        val body = response.body()!!
        println("$word : $body")
        return listOf(
            false,
            !body.contains("\"message\":\"Sorry pal, we couldn't find definitions for the word you were looking for.\"")
        )
    } catch (e: ConnectException) {
        return listOf(true, false)
    }
}
