# nmc
![version](https://img.shields.io/badge/version-1.0-blue)
![contributors](https://img.shields.io/badge/contributors-3-yellowgreen)
![dependencies](https://img.shields.io/badge/repo_size-1.74MB-orange)


This tool is used for n-way model merging. It considers the base and all branch models at the same time, ranther than handling them iteratively, to match similar elements, compute the differences, detect conflicts, and merge all conflict-free differences. It also properly handles the ordered sets in models.

<div align=center>
<img width="500" alt="1" src="https://user-images.githubusercontent.com/26463428/149732685-dbbb7432-7029-499c-8673-f98d79bbfcd6.png">
</div>

## Getting Started

### Prerequisites

[Eclipse IDE for Java and DSL Developers](https://www.eclipse.org/downloads/packages/release/2021-12/r/eclipse-ide-java-and-dsl-developers)

### Installation

First, start Eclipse and click File -> Import -> Git -> Projects from Git -> Clone URI.

<div align=center>
<img width="300" alt="1" src="https://user-images.githubusercontent.com/26463428/149894474-7f9796e5-62a0-481b-ac9d-7e8beb7828d4.png">
<img width="300" alt="2" src="https://user-images.githubusercontent.com/26463428/149894475-eb682bec-998f-45e7-91c3-bfc74c3dbfed.png">
</div>

Second, copy the uri of this repository and fill in the username and password of GitHub.

<div align=center>
<img width="300" alt="4" src="https://user-images.githubusercontent.com/26463428/149895620-5238f0d1-1dfb-4d92-86a0-eee80a8fab4d.png">
<img width="378" alt="3" src="https://user-images.githubusercontent.com/26463428/149895329-c220dd86-ff42-4af7-aca2-f0b7adecd4b8.png">
</div>

Then go to edu.ustb.sei.mde.nmc/src/edu/ustb/sei/mde/nmc/test/, and run Test* as Java Application.


### Usage example

#### Example 1
The base version had four classes. Branch 1 refactored the Person class and the Company class. Branch 2 and Branch 3 added the attributes and subclasses of the Account class. All branch versions modified the order of operation's parameter in Bank class. The result version is the result of the n-way model merging.

<div align=center>
<img width="700" alt="2" src="https://user-images.githubusercontent.com/26463428/149738918-d4b46496-2cb8-4cbb-bc0b-0cc6719d7cde.png">
</div>
  
#### Example 2
Branch1 modified Person to Person1. Branch2 modified Person to Person2. Branch3 modified Person to Person3. These led to "single-attribute modification" conflict.

<div align=center>
<img width="473" alt="3" src="https://user-images.githubusercontent.com/26463428/149749419-bb14702e-8113-4b4d-912a-0e540645d041.png">
</div>
<div align=center>
<img width="460" alt="4" src="https://user-images.githubusercontent.com/26463428/149749453-906835be-35ff-4c58-a44c-e45c2b3134c9.png">
 </div>


#### Example 3
Branch1 deleted Master. Branch2 added the salary attirbute of Master. Branch3 added the score and address attributes of Master. These led to "delete element but add element's references" conflict.

<div align=center>
<img width="452" alt="6" src="https://user-images.githubusercontent.com/26463428/149750909-e8c3fb24-9bbb-48a8-b015-2c67ae40c0f8.png">
</div>
<div align=center>
<img width="857" alt="5" src="https://user-images.githubusercontent.com/26463428/149750763-c99a72ac-9816-4a81-8ba7-423ca9ee6ba5.png">
</div>


## License
[Eclipse Public License 2.0](https://github.com/lesleytong/nmc/blob/main/LICENSE)

