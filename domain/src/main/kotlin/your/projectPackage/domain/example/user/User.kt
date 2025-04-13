package your.projectPackage.domain.example.user

import arrow.optics.optics

data class User(val uid: UserId, val name: String?) {
    companion object
}

@optics
@JvmInline
value class UserId(val value: Int) {
    companion object
}
