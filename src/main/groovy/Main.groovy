static void main (String[] args)
{
  def folder = new File ('/home/steve/Pictures')
  def fileNumbers = []
  def dry = false

  // Collect the embedded numbers for each file
  folder.eachFileMatch (~/IMG_\d+\.JPG/) { file ->
    fileNumbers.add (parseFileNumber (file.name))
  }
  fileNumbers = fileNumbers.sort ()

  // Iterate through files in numeric sequence - the order the pictures were generated
  def date = ''
  def num = 10
  fileNumbers.each { fileNum ->
    def file = new File (folder, "IMG_${fileNum}.JPG")
    assert (file.exists ())
    def fileDate = new Date (file.lastModified ()).format ('yyyy-MM-dd')
    if (date == fileDate) { num++ } else {
      date = fileDate
      num = 1
    }
    def numString = num.toString ().padLeft (4, '0')
    def newName = "${date}_${numString}.jpg"
    if (dry) {
      println("Rename $file.name to $newName")
    } else {
      file.renameTo (new File (folder, newName))
    }
  }
}

static int parseFileNumber (String fileName)
{
  def result = (fileName =~ /\d+/).findAll () [0]
  return result as int
}

// End of Main.groovy
