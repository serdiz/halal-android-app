//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.saveable.SaveableStateRegistry
//import androidx.compose.runtime.saveable.SaverScope
//import androidx.compose.ui.text.input.TextFieldValue
//
//class TextFieldValueSaver : SaveableStateRegistry.Saver<TextFieldValue, Any> {
//    override fun restore(value: Any): TextFieldValue {
//        return if (value is String) {
//            TextFieldValue(value)
//        } else {
//            TextFieldValue("")
//        }
//    }
//
//    override fun SaverScope.save(value: TextFieldValue): Any {
//        return value.text
//    }
//}
//
//@Composable
//fun ProfileImage(username: MutableState<TextFieldValue>, onUsernameChange: (TextFieldValue) -> Unit) {
//    // Rest of your code
//}
