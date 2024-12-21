package your.projectPackage.domain.example

interface UserRepository {
    suspend fun getUsers(): List<User>
    suspend fun createUser(vararg users: User)
    suspend fun deleteUser(vararg users: User)
}
