import io.circe._
import io.circe.syntax._
import io.circe.parser._
import io.circe.generic.semiauto._
import scala.io.Source.fromFile
import java.io.PrintWriter

object Main extends App {

  case class Region(name: String, coordinates: List[List[(Float, Float)]])
  case class Location(name: String, coordinates: (Float, Float))
  case class ResultRegion(region: String, matchedLocations: List[String])

  // Decoding of regions and locations from JSON using Circe
  object Region {
    implicit val decoder: Decoder[Region] = deriveDecoder[Region]
  }

  object Location {
    implicit val decoder: Decoder[Location] = deriveDecoder[Location]
  }

  // Encoding of regions with matched locations to JSON
  object ResultRegion {
    implicit val encoder: Encoder[ResultRegion] = deriveEncoder[ResultRegion]
  }

  // Creates a list of location objects from decoded JSON
  def processDecodedLocations(inputFile: String): List[Location] = {
    val jsonLocations = fromFile(inputFile).mkString
    val decodedLocations = decode[List[Location]](jsonLocations)
    decodedLocations match {
      case Left(error) =>
        println(s"Error: $error")
        List.empty[Location]
      case Right(locations) => locations
    }
  }


  // Creates a list of region objects from decoded JSON
  // Returns a list of region objects
  def processDecodedRegions(inputFile: String): List[Region] = {
    val jsonRegions = fromFile(inputFile).mkString
    val decodedRegions = decode[List[Region]](jsonRegions)
    decodedRegions match {
      case Left(error) =>
        println(s"Error: $error")
        List.empty[Region]
      case Right(regions) => regions
    }
  }

  // Checks whether the location is in the given region
  // Returns a boolean value of true, if location is within the region; false if it isn't
  def isLocationInRegion(region: Region, location: Location): Boolean = {
    region.coordinates.exists { polygon =>
      polygon.sliding(2).foldLeft(false) { case (c, List(i, j)) =>
        val cond = {
          (i._1 <= location.coordinates._1 && location.coordinates._1 < j._1 ||
            j._1 <= location.coordinates._1 && location.coordinates._1 < i._1) &&
            location.coordinates._2 < (j._2 - i._2) * (location.coordinates._1 - i._1) / (j._1 - i._1) + i._2
        }
        if (cond) !c else c
      }
    }
  }

  // Generates JSON string of regions with matched locations
  // Returns JSON format string
  def generateJsonOutput(regions: List[Region], locations: List[Location]): String = {
    val results = regions.map { region =>
      val matchedLocations = locations.filter(location => isLocationInRegion(region, location)).map(_.name)
      ResultRegion(region.name, matchedLocations)
    }
    results.asJson.spaces2
  }

  val locationsInputFile = "inputFiles/locations.json"
  val regionsInputFile = "inputFiles/regions.json"
  val Locations : List[Location] = processDecodedLocations(locationsInputFile)
  val Regions: List[Region] = processDecodedRegions(regionsInputFile)

  val encodedResultRegions = generateJsonOutput(Regions, Locations)
  val outputFile = "outputFiles/results.json"
  val writer = new PrintWriter(outputFile)
  writer.write(encodedResultRegions)
  writer.close()
}




