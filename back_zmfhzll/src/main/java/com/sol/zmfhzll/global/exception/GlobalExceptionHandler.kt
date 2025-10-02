package com.sol.zmfhzll.global.exception

import com.sol.zmfhzll.global.response.ErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.NoHandlerFoundException

@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    // 커스텀 비즈니스 예외 처리
    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(ex: BusinessException): ResponseEntity<ErrorResponse> {
        logger.warn("Business exception occurred: ${ex.message}", ex)

        // cause가 있으면 로그에 상세 정보 추가
        if (ex.cause != null) {
            logger.warn("Caused by: ${ex.cause?.message}", ex.cause)
        }

        val errorResponse = ErrorResponse(
            status = ex.errorCode.status.value(),
            code = ex.errorCode.code,
            message = ex.message ?: ex.errorCode.message,
            details = ex.details  // 상세 정보 포함
        )
        return ResponseEntity.status(ex.errorCode.status).body(errorResponse)
    }

    // Validation 예외 처리 (@Valid, @Validated)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        logger.warn("Validation exception occurred: ${ex.message}")
        val errors = ex.bindingResult.fieldErrors.associate {
            it.field to (it.defaultMessage ?: "Invalid value")
        }
        val errorResponse = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            code = "VALIDATION_ERROR",
            message = "입력값 검증에 실패했습니다.",
            errors = errors
        )
        return ResponseEntity.badRequest().body(errorResponse)
    }

    // 타입 불일치 예외 처리
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleTypeMismatchException(ex: MethodArgumentTypeMismatchException): ResponseEntity<ErrorResponse> {
        logger.warn("Type mismatch exception occurred: ${ex.message}")
        val errorResponse = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            code = "TYPE_MISMATCH",
            message = "잘못된 타입의 값이 전달되었습니다: ${ex.name}"
        )
        return ResponseEntity.badRequest().body(errorResponse)
    }

    // 404 예외 처리
    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNotFoundException(ex: NoHandlerFoundException): ResponseEntity<ErrorResponse> {
        logger.warn("Not found exception occurred: ${ex.message}")
        val errorResponse = ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            code = "NOT_FOUND",
            message = "요청하신 리소스를 찾을 수 없습니다."
        )
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
    }

    // 일반 예외 처리
    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception): ResponseEntity<ErrorResponse> {
        logger.error("Unexpected exception occurred: ${ex.message}", ex)
        val errorResponse = ErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            code = "INTERNAL_SERVER_ERROR",
            message = "서버 내부 오류가 발생했습니다."
        )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse)
    }
}