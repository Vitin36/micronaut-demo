package micronaut.demo

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestStreamHandler
import io.micronaut.function.aws.proxy.MicronautLambdaContainerHandler
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream


class StreamLambdaHandler : RequestStreamHandler {
    private var handler : MicronautLambdaContainerHandler? = MicronautLambdaContainerHandler()

    @Throws(IOException::class)
    override fun handleRequest(inputStream: InputStream?, outputStream: OutputStream?, context: Context?) {
        handler!!.proxyStream(inputStream, outputStream, context) // <2>
    }
}