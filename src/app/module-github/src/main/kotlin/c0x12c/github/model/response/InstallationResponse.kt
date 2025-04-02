package c0x12c.github.model.response

data class InstallationResponse(
  val id: Long,
  val account: Account
) {
  data class Account(
    val id: Long,
    val login: String,
    val type: String,
    val avatarUrl: String
  )
}