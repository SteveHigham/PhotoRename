static void main (String[] args)
{
  def folder = new File ('/home/steve/Pictures')
  def fileNumbers = []

  // Collect the embedded numbers for each file
  folder.eachFileMatch (~/IMG_\d+\.JPG/) { file ->
    fileNumbers.add (parseFileNumber (file.name))
  }
  fileNumbers = fileNumbers.sort ()

  // Iterate through files in numeric sequence - the order the pictures were generated
  fileNumbers.each { num ->
    println ("IMG_${num}.JPG")
  }
}

static int parseFileNumber (String fileName)
{
  def result = (fileName =~ /\d+/).findAll () [0]
  return result as int
}

// End of Main.groovy
