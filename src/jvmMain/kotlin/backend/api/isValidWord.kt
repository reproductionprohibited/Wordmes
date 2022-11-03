package backend.api

import java.net.ConnectException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse


/**
 * List of booleans:
 * 1st place: If there's no Exception,
 * 2nd place: If the word exists
 */
fun isValidWord(word: String): List<Boolean> {
    try {
        val request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.dictionaryapi.dev/api/v2/entries/en/$word"))
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build()
        val response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString())
        val body = response.body()!!
        return listOf(
            false,
            body.contains("audio") && body.contains("phonetics") && body.contains("definition"),
        )
    } catch (e: ConnectException) {
        return listOf(true, false)
    }
}
