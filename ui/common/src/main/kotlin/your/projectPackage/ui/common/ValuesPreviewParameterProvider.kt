package your.projectPackage.ui.common

abstract class ValuesPreviewParameterProvider<T>(vararg args: T) :
    androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider<T>(
        args.toList(),
    )
