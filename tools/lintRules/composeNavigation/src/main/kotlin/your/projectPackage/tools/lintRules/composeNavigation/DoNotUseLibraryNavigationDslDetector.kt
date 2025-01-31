package your.projectPackage.tools.lintRules.composeNavigation

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.android.tools.lint.detector.api.SourceCodeScanner
import com.intellij.psi.PsiJavaFile
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression

/**
 * ライブラリ側の Navigation DSL の使用を制限するための Detector。
 */
class DoNotUseLibraryNavigationDslDetector :
    Detector(),
    SourceCodeScanner {

    private val libraryNavigationDslFunctions = listOf(
        "androidx.navigation.compose" to "composable",
        "androidx.navigation.compose" to "navigation",
        "androidx.navigation.compose" to "dialog",
    )

    override fun getApplicableMethodNames(): List<String> = libraryNavigationDslFunctions
        .map { it.second }

    override fun visitMethodCall(context: JavaContext, node: UCallExpression, method: PsiMethod) {
        val javaFile = method.containingFile as? PsiJavaFile
        if (javaFile?.packageName in libraryNavigationDslFunctions.map { it.first }) {
            context.report(
                DoNotUseLibraryNavigationDslIssue,
                node,
                context.getLocation(node),
                BRIEF_DESCRIPTION,
            )
        }
    }
}

private const val BRIEF_DESCRIPTION =
    "navigation compose ライブラリの Navigation DSL を使用しないでください。ライブラリの DSL の使用は予期せぬランタイムエラーにつながる恐れがあります。"

internal val DoNotUseLibraryNavigationDslIssue = Issue.create(
    id = "DoNotUseLibraryNavigationDsl",
    briefDescription = BRIEF_DESCRIPTION,
    explanation = "$BRIEF_DESCRIPTION 代わりに :ui:navigation の NavigationDsl ないの関数を使用してください。",
    category = Category.CORRECTNESS,
    priority = 6,
    severity = Severity.ERROR,
    implementation = Implementation(
        DoNotUseLibraryNavigationDslDetector::class.java,
        Scope.JAVA_FILE_SCOPE,
    ),
)
