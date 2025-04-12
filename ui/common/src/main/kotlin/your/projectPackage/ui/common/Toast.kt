package your.projectPackage.ui.common

import android.content.Context
import android.widget.Toast

/**
 *
 * ### Usage
 *
 * ```kt
 * // basic
 * showToast(context, "Hello Toast")
 * // with duration
 * showToast(context, "Hello Toast", Toast.LENGTH_SHORT)
 * ```
 *
 */
fun showToast(context: Context, message: String, duration: Int = Toast.LENGTH_LONG) =
    Toast.makeText(context, message, duration).show()

/**
 *
 * ### Usage
 *
 * ```kt
 * // basic
 * showToast(context) { "Hello Toast" }
 * // with duration
 * showToast(context, duration = Toast.LENGTH_LONG) { "Hello Toast" }
 * ```
 *
 */
fun showToast(context: Context, duration: Int = Toast.LENGTH_LONG, message: () -> String) =
    Toast.makeText(context, message(), duration).show()
