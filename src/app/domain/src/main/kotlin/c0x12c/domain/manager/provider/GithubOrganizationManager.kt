package c0x12c.domain.manager.provider

import c0x12c.domain.manager.common.ProviderOrganizationManager
import c0x12c.exception.ClientError
import c0x12c.github.manager.GithubAppManager
import c0x12c.logging.logger
import c0x12c.postgresql.constant.UserRole
import c0x12c.postgresql.constant.github.GithubAccountType
import c0x12c.postgresql.repository.GithubAccountRepository
import c0x12c.postgresql.repository.UserRepository
import java.util.UUID

class GithubOrganizationManager(
  private val userRepository: UserRepository,
  private val githubAccountRepository: GithubAccountRepository,
  private val githubAppManager: GithubAppManager
) : ProviderOrganizationManager {

  companion object {
    private val logger = GithubOrganizationManager::class.logger()
  }

  override suspend fun fetchLatestRoles(
    organizationId: UUID,
    currentRoleByUserId: Map<UUID, UserRole>
  ): Map<UUID, UserRole> {
    val account = githubAccountRepository.byOrganizationId(organizationId) ?: throw ClientError.ORGANIZATION_NOT_FOUND.asException()

    // Return early if not an organization account
    if (account.type != GithubAccountType.ORGANIZATION) {
      return currentRoleByUserId
    }

    // User with Collaborator role will not be changed
    val userIds = currentRoleByUserId
      .filter { it.value != UserRole.COLLABORATOR }
      .keys.toList()

    if (userIds.isEmpty()) {
      return currentRoleByUserId
    }

    val users = userRepository.byIds(ids = userIds)
    val installation = githubAppManager.installation(
      installationId = account.installationId
    )

    // Return in case app was removed from organization
    if (installation == null) {
      return currentRoleByUserId
    }

    // Fetch new roles
    val newRoleByUserId = users.associate { user ->
      val userProviderId = githubAppManager.userById(
        installationId = account.installationId,
        userId = user.authId
      ).login

      val membership = githubAppManager.orgMembershipOfUser(
        installationId = account.installationId,
        orgName = installation.account.login,
        username = userProviderId
      )

      user.id to membership.role.asUserRole()
    }

    return currentRoleByUserId + newRoleByUserId
  }

  private fun String.asUserRole() =
    when (this) {
      "admin" -> UserRole.ADMIN
      "member" -> UserRole.USER
      else -> UserRole.USER
    }
}