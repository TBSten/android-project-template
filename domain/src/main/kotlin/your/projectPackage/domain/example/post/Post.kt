package your.projectPackage.domain.example.post

import arrow.optics.optics

@optics
data class Post(val id: PostId, val userId: UserId, val title: String, val body: String) {
    companion object
}

@optics
sealed class State {
    abstract val hoge: String
    abstract val fuga: String

    @optics
    data class Initial(override val hoge: String, override val fuga: String) : State() {
        companion object
    }

    @optics
    data class Stable(override val hoge: String, override val fuga: String, val piyo: String) : State() {
        companion object
    }

    companion object
}

@optics
@JvmInline
value class PostId(val value: Int) {
    companion object
}

@optics
@JvmInline
value class UserId(val value: Int) {
    companion object
}
