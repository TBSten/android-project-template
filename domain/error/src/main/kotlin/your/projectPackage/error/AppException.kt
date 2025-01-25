package your.projectPackage.error

sealed class AppException(
    val logMessage: String?,
    override val cause: Throwable?,
) : Exception()
