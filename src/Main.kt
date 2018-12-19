import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.util.*
import java.util.regex.Pattern

fun main(args :Array<String>) {

    val inFile = File("log.txt")
    inFile.createNewFile();
    val outFile = File("outlog.txt")
    outFile.createNewFile()

    val originLogStream = FileReader("log.txt")
    val regexpForCut = "OkHttp http"
    val remainingStringList: List<String> = getNeededStringList(originLogStream, regexpForCut)
    val outputFileName = "outlog.txt"
    writeOutLog(outputFileName, remainingStringList);
}

fun getNeededStringList(fileReader: FileReader, regexp: String): List<String> {
    var lines: List<String> = ArrayList<String>()
    fileReader.use { it ->
        val pattern: Pattern = Pattern.compile(regexp)
        lines = it.readLines().filter { item ->  !pattern.matcher(item).find() }
    }
    return lines;
}

fun writeOutLog(outFileName: String, stringList: List<String>): Unit {
    val fileWriter = FileWriter(outFileName)
    fileWriter.use {
        for (item in stringList) {
            it.write(item)
            it.write("\n")
        }
    }
}