package c0x12c.github.model.response

data class InstallationResponse(
  val id: Long,
  val account: Account,
  val suspendedAt: String? = null
) {
  data class Account(
    val id: Long,
    val login: String,
    val type: String,
    val avatarUrl: String
  )
}