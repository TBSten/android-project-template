package your.projectpackage.lintRules.composeNavigation

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.client.api.Vendor
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue

class ComposeNavigationIssueRegistry : IssueRegistry() {
    override val issues: List<Issue> =
        listOf(
            DoNotUseLibraryNavigationDslIssue,
        )
    override val api: Int = CURRENT_API
    override val vendor = Vendor(
        vendorName = "TBSten",
        identifier = "tbsten",
        feedbackUrl = "https://github.com/TBSten/AndroidProjectTemplate/issues",
    )
}
