package dvp.lib.ytube.utils


fun String.removeNonDigit(): String = this.replace(Regex("\\D"), "")