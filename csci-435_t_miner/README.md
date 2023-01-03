# csci-435_t_miner
Project #1: T-Miner: A GitHub Action For Performing Impact Analysis

Note: This repository contains the code for the action as well as artifacts explaning our tool and testing process. However, our tool will not work in this repository due to the absence of a requirements folder. To see the tool in action, visit our wiki page to see a running example or learn how to add the tool to your repository. You can also click the links below to visit repositories where the majority of our debugging was completed:

https://github.com/jonathanli15/t_miner_test

https://github.com/mesierzega/megan_t_miner_test

https://github.com/mtfarber/t_miner_test


### Description
The T-Miner action displays the results of the existing COMET traceability library into Github using a GitHub action. Generally, past work on automating the traceability process typically fails to holistically integrate orthogonal
sources of information generated during the development process such as dynamic program information, domain expertise, or transitive (often implicit) relationships between software
artifacts. To overcome this problem, the SEMERU lab has developed a library, named COMET, which infers a candidate set of traceability links by combining information from a variety of
knowledge sources using a Hierarchical Bayesian Network.

### Installation

### Usage
Prerequisites:

Have a requirements folder containing .txt files in describing requirements in natural language. You can change the name of this file, but then you will also need to change the sourcePath in the configuration file.

Below are basic steps of how to use our tool, for a more detailed tutorial on how to accomplish each task, visit the Wiki of our repository [T-Miner Tutorial](../../wiki)

1. Download the .github/workflows directory, requirements.txt, the test.py file (in Code/Backend), config.conf, and pyConfig.ini from this repository and upload it to your own repository that contains your code. An example repository setup can be seen below
<img width="908" alt="example" src="https://user-images.githubusercontent.com/74937994/205683088-d6273e1c-95ac-47a5-b48a-ac3aebe034f7.png">

_Important Note: do not just copy and past the files (ex: the yml file into a new action), this will result in the formatting being off, even if it doesn't look like it. Make sure you download or copy the exact directories and upload them to your repository. One way we did this is by having a clone of the t_miner repository and the new repository on our local machine and then copying the files from the t_miner folder to the new repository folder and pushing the changes to the new repository._

2. Edit the parameters in the config.conf to generate a rerport according to your specifications -- for example, you can change the threshold at which links between documents are outputted so only links with a certain value are ouputted
3. To change the traceability library used, edit pyConfig.ini
4. Upon each pull request and push, the files you committed will be compared for traceability (using the traceability.py script), and a Traceability_Report.html file will be generated showing you the links between the source (the current files in the directory) and target (the new files being committed) files in a formatted report with links to each file and a table of commits
5. Look at the generated report and submit feedback on specific links using the config.conf file: add the files and feedback to the targetSourceString, targetString, and targetSourceValue variables, matching the order of the files and value you want to contribute (ex: the first targetSourceString goes with the first targetString and the first targetSourceValue, the second goes with the second, etc.) as seen below. Our script takes your input and recalculates the traceability values, you can change how the value is recaluclated by updating the new_probability method in test.py

<img  width="336" alt="config_file" src="https://user-images.githubusercontent.com/74937994/205683566-59de1151-7d0e-43b9-b104-9655a8549115.png">

Notes:

The script that computes the traceability values uses the DS4SE traceability library, which can be found in the resources section. The specific method used is the traceLinkValue method within this libary, and it operates with word2vec, using both SCM and WMD, which are both text classification methods. It also uses doc2vec, which uses the entire document as a vector, while word2vec uses a singular word as a vector.

### Resources

DS4SE: https://pypi.org/project/ds4se/

### Authors
Project Leads: Yanfu Yan, David Nader

Project Developers: Nicholas Costa, Matthew Farber, Jonathan Li, Megan Sierzega
