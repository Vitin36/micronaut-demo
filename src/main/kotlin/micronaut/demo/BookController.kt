package micronaut.demo

import io.micronaut.http.annotation.*
import io.micronaut.http.client.annotation.Client
import java.net.http.HttpHeaders
import java.util.UUID
import javax.inject.Inject
import javax.validation.Valid

@Controller
@Client
class BookController {

    @Inject
    lateinit var context: Context

    @Post
    fun save(@Valid @Body book: Book): BookSaved {
        val bookSaved = BookSaved()
        bookSaved.name = book.name
        bookSaved.isbn = UUID.randomUUID().toString()
        return bookSaved
    }

    @Get
    fun get(): List<Book>{
        return emptyList()
    }

    @Put
    fun put(): String{
        return "put working"
    }
}