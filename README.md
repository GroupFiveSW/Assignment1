# Assignment 1: Decide Program (DD2480)

In this assignment we were tasked to write a program in accordance with a given specification _Launch Interceptor Program: Requirements
Specification_. 

## Summary

### Program

The `Decide()` program will generate a boolean signal which determines whether an interceptor should be
launched based upon input radar tracking information. This is decided on a combination of 15 different _Launch Interceptor Conditions (LIC’s)_. 
If a LIC is true, it will assign the corresponding position in the _Conditions Met Vector
(CMV)_ to true. The input _Logical Connector Matrix (LCM)_, defines which individual LIC’s must be considered jointly in some way. 
The combination of LCM and CMV is stored in the _Preliminary Unlocking
Matrix (PUM)_, a 15x15 symmetric matrix. 

Another input, the _Preliminary Unlocking Vector (PUV)_ represents which LIC actually matters
in this particular launch determination. Each element of the UV indicates how to combine the PUM
values to form the corresponding element of the _Final Unlocking Vector (FUV)_, a 15-element
vector that decides if the launch-unlock signal should be generated.

### Tooling

- **Programming Language:** Java was used due to all members having experience with it and its support for testing. 
- **Project Updates:** GitHub built-in _Projects_ tool was used with an active Kanban-board. You can find this board connected to this repo.
- **Build tools:** Maven.
- **Testing:** JUnit.
- **CI:** GitHub Actions. Runs all specified JUnit tests and build before integration.

---


## Group Members:
- Gabriel Acar (Gabriel-Acar)
- Elias Bonnici (elibon99)
- Gustaf Halvardsson (gustafvh)
- Alexander Krantz (Klako)
- Oscar Spolander (Carnoustie)

## Contributions 
(# = IssueNumber on Github if applicable)

### Gabriel Acar
- Create insideCircle function (#23) **(Pair-programming)**
- Create triangleArea function (#25) **(Pair-programming)**
- Implement LIC 3 with unit test (#6)
- Implement LIC 8 with unit test (#15)
- Implement LIC 13 with unit test (#11)
- Implement LIC 14 with unit test (#9)

### Elias Bonnici
- Convert template code in Java (#4) (with Gustaf)
- Setup maven and GitHub Actions (#2)
- Implement LIC 2 with unit test (#5)
- Implement LIC 9 with unit test (#17)
- Implement LIC 11 with unit test (#14)
- Implement DECIDE function (#21)

### Gustaf Halvardsson
- Convert template code in Java (#4)
- Implement LIC 0 with unit test (#7)
- Implement LIC 7 with unit test (#12)
- Create angle function(#24) **(Pair-programming)**
- Implement README according to the assignment description
- Create full-scale tests for the entire Decide Program #45 (only very partially due to its overlap with #21)

### Alexander Krantz
- Implement LIC 4 with unit test (#8)
- Fix test for LIC 4 (#30)
- Implement LIC 5 with unit test (#18)
- Implement LIC 10 with unit test (#16)
- Implement LIC 12 with unit test (#13)
- Implement FUV generator with unit tests (#19)
- All LIC function docblocks should reference the correct CMV variable (#38)

### Oscar Spolander
- Implement ContainsCircle function **(Pair-programming)**
- Implement LIC1 with uniit test (#3)
- Implement LIC6 with unit test (#10)
- Implement PUM-generator with unit test (#20)
- Write short report according to Essence (#46)



## How to run the code

Thanks to the configuration of the `pom.xml` file, you can run, build and test the code in the most easy manner with your integrated IDE (like Intellij for ex.). 

To run it via the command-line use `mvn exec:java`

---

# Specification


### Reusable functionality
Due to overlapping functionality amongst various LICS's, we identified reusable functions across these LIC's.

#### distance(p1,p1)
Function to find distance between two points p1,p2 in the plane. Needed in the following LIC's:

LIC1, LIC3, LIC6, LIC7, LIC12

#### containsCircle(R,{points})
Function to determine if the set {points} are contained in a circle with Radius R.Needed in the following LIC's:

LIC1, LIC8, LIC13

#### angle(p1,p2,p3)
Function to find the angle formed by the two lines (p2,p1) and (p2,p3). Needed in the following LIC's:

LIC2, LIC9

#### triangleArea(p1,p2,p3)
Function to find the area of the triangle formed by the lines between each of the points p1,p2,p3.
Needed in the following LIC's:

LIC3, LIC10, LIC14

