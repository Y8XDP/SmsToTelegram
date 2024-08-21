package com.example.smssender.common

class Transcoder {

    companion object {
        private val replacements = HashMap<Char, Char>().apply {
            put('а', 'a')
            put('б', '6')
            //put('в', '0')
            //put('г', '7')
            //put('д', '5')
            put('е', 'e')
            put('ё', 'e')
            //put('ж', '1')
            //put('з', '1')
            //put('и', '1')
            //put('й', '1')
            put('к', 'k')
            //put('л', '1')
            //put('м', '1')
            //put('н', '1')
            put('о', 'o')
            //put('п', '1')
            put('р', 'p')
            put('с', 'c')
            //put('т', '1')
            put('у', 'y')
            //put('ф', '1')
            put('х', 'x')
            //put('ц', '1')
            //put('ч', '1')
            //put('ш', '1')
            //put('щ', '1')
            //put('Ъ', '1')
            //put('ы', '1')
            put('ь', 'b')
            //put('э', '1')
            //put('ю', '1')
            //put('я', '1')
        }

        fun encode(text: String): String {
            var result = text

            replacements.forEach { (key, value) ->
                result = result.replace(key, value)
            }

            return result
        }
    }
}