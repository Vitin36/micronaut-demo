package micronaut.demo

import javax.inject.Singleton

@Singleton
class Context {
    val applicationIdHeaderName = "x-application-id"
    val organizationIdHeaderName = "x-organization-id"

    var applicationId: String? = null
    var organizationId: String? = null

    fun setContext(appId: String?, orgId: String?){
        applicationId = appId
        organizationId = orgId
    }
}