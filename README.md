# internship-task
## Task completed for TravelTime internship

This is a Scala program used for matching a set of locations (described in two-dimensional coordinates) to it's corresponding regions (described in two-dimensional polygons).

### Requirements:
- Circe library, version 0.14.3:
  - circe.core
  - circe.generics
  - circe.parser
- Scala 2.13.11 and sbt 1.9.2 or higher installed on your machine

### Input files:
The program requires two .json input files: one for the regions and one for the locations. Both locations and regions must have a name. Every location must have it's longitude and latitude described as floating point values. Every region must have a polygon or polygons described by a set of two-dimensional points.

Example of locations input file ***locations.json***:

```[
  {
    "name": "location1",
    "coordinates": [
      25.21051562929364,
      54.64057937965808
    ]
  }
]
```

Example of regions input file ***regions.json***:

```[  
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
]
```


### Output file:

The program produces a single output file ***results.json*** inside a folder "outputFiles" in the project directory.

### Launching the program

- Make sure you have Scala and sbt installed
- Open PowerShell or similar terminal that supports sbt
- Change your current directory to project folder using `cd path\to\projectDirectory`
- Run a command `sbt "runMain Main locations.json regions.json"`. Replace `locations.json` and `regions.json` with actual relative paths to input files

