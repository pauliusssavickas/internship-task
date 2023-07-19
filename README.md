# internship-task
##Task completed for TravelTime internship

This is a Scala program used for matching a set of locations (described in two-dimensional coordinates) to it's corresponding regions (described in two-dimensional polygons).

###Requirements:
-Circe library, available from: https://circe.github.io/circe/.
-Scala installed on your machine

###Input files:
The program requires two .json input files: one for the regions and one for the locations. Both locations and regions must have a name. Every location must have it's longitude and latitude described as floating point values. Every region must have a polygon described

Example of locations input file __locations.json__:

'[
  {
    "name": "location1",
    "coordinates": [
      25.21051562929364,
      54.64057937965808
    ]
  }
]'

Example of regions input file __regions.json__:

'[  
  {
    "name": "region4",
    "coordinates": [
      [
        [
          25.066863385003188,
          54.68001766323334
        ],
        [
          25.066863385003188,
          54.493271264176514
        ],
        [
          25.33202315105291,
          54.493271264176514
        ],
        [
          25.33202315105291,
          54.68001766323334
        ],
        [
          25.066863385003188,
          54.68001766323334
        ]
      ]
    ]
  }
]'

Both files must be placed inside a folder "inputFiles" in the project directory.

###Output file:

The program produces a single output file __results.json__ inside a folder "outputFiles" in the project directory.
