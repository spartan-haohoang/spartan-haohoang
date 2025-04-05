package c0x12c.app.controller

import c0x12c.app.auth.CustomSecurityRule
import c0x12c.approvia.client.request.codereviewer.CodeReviewRequest
import c0x12c.approvia.client.request.codereviewer.CodeWalkthroughRequest
import c0x12c.approvia.client.response.codereviewer.CodeReviewResponse
import c0x12c.approvia.client.response.codereviewer.CodeWalkthroughResponse
import c0x12c.approvia.codereview.CodeReviewer
import c0x12c.approvia.codereview.model.ReviewInput
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.scheduling.TaskExecutors
import io.micronaut.scheduling.annotation.ExecuteOn
import io.micronaut.security.annotation.Secured
import io.micronaut.validation.Validated
import io.swagger.v3.oas.annotations.tags.Tag

@ExecuteOn(TaskExecutors.IO)
@Secured(CustomSecurityRule.USER)
@Validated
@Tag(name = "code-reviewer")
@Controller("/api/code-reviewer")
class CodeReviewerController(
  private val codeReviewer : CodeReviewer
) {

  @Post("/review")
  suspend fun review(
    @Body request: CodeReviewRequest
  ): CodeReviewResponse {
    val input = ReviewInput(
      enableRepoContext = request.enableRepoContext,
      repoId = request.repoId,
      pullRequestTitle = request.pullRequestTitle,
      diffContent = request.diffContent
    )
    return CodeReviewResponse(codeReviewer.review(input).review)
  }

  @Post("/walkthrough")
  suspend fun walkthrough(
    @Body request: CodeWalkthroughRequest
  ): CodeWalkthroughResponse {
    val input = ReviewInput(
      enableRepoContext = request.enableRepoContext,
      repoId = request.repoId,
      pullRequestTitle = request.pullRequestTitle,
      diffContent = request.diffContent
    )
    return CodeWalkthroughResponse(codeReviewer.walkthrough(input))
  }
}