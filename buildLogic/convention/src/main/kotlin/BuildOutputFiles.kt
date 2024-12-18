import org.gradle.api.Project
import org.gradle.api.file.Directory

internal class BuildOutputFiles(private val rootDir: Directory) {
    constructor(rootProject: Project) : this(rootProject.layout.buildDirectory.dir("build-outputs").get())

    private fun projectDir(project: Project) = project.path
        .split(":")
        .filter { it.isNotEmpty() }
        .joinToString("/")

    fun ktlintReport(project: Project): Directory = rootDir
        .dir(projectDir(project))
        .dir("ktlint-report")
}
