package retrofit

interface WsResponse {
    fun successResponse(response: Any?, code: Int)
    fun failureRespons(error: Throwable, code: Int)
    fun noInternetConnection(code: Int)
 }
