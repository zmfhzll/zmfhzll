package com.sol.zmfhzll.global.exception

class BusinessException(
    val errorCode: ErrorCode,
    override val message: String? = errorCode.message,
    val details: Map<String, Any?>? = null,  // 추가 상세 정보
    override val cause: Throwable? = null     // 원인 예외
) : RuntimeException(message, cause) {

    // 상세 정보와 함께 예외 생성
    constructor(
        errorCode: ErrorCode,
        message: String?,
        details: Map<String, Any?>
    ) : this(errorCode, message, details, null)

    // 원인 예외와 함께 생성
    constructor(
        errorCode: ErrorCode,
        cause: Throwable
    ) : this(errorCode, errorCode.message, null, cause)

    // 메시지와 원인 예외 함께 생성
    constructor(
        errorCode: ErrorCode,
        message: String?,
        cause: Throwable
    ) : this(errorCode, message, null, cause)
}