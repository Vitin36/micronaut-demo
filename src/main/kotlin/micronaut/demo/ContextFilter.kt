package micronaut.demo

import io.micronaut.http.HttpRequest
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Filter
import io.micronaut.http.filter.OncePerRequestHttpServerFilter
import io.micronaut.http.filter.ServerFilterChain
import org.reactivestreams.Publisher
import javax.inject.Inject

@Filter("/**")
class ContextFilter: OncePerRequestHttpServerFilter() {

    @Inject
    lateinit var context: Context

    override fun doFilterOnce(request: HttpRequest<*>?, chain: ServerFilterChain?): Publisher<MutableHttpResponse<*>> {
        println(request)
        val appId = request?.headers?.get(context.applicationIdHeaderName)
        val orgId = request?.headers?.get(context.organizationIdHeaderName)
        context.setContext(appId, orgId)
        return chain!!.proceed(request)
    }
}