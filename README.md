# J2SE Class Based Project: Flights ERP

## Overview
This project is part of the Udacity Azure ML Nanodegree.
In this project, we build and optimize an Azure ML pipeline using the Python SDK and a provided Scikit-learn model.
This model is then compared to an Azure AutoML run.

## Requirements and Design phase of the solution
###### **1) Requirements by the client**
1) Shell Application, 2) handle flights information: departure/arrive info, aircraft and airline details, reports, 3) Uses of API to obtain wheather conditions and send reports by email, 4) Apply SOLID principles to the sourcecode

###### **2) Class Diagram**
Below the reader will find four diagrams of the same solution, the intention is to show the evolution of the developer after carefully review if each propossal fulfill the requirements. The 4th diagram is the best one but it was neccesary to go through the previous one.

First diagram:
![flights-class-diagramv01.png](./design/flights-class-diagramv01.png?raw=true "Diagram 1")

Second diagram:
![flights-class-diagramv02.png](./design/flights-class-diagramv02.png?raw=true "Diagram 2")

Third diagram:
![flights-class-diagramv03.png](./design/flights-class-diagramv03.png?raw=true "Diagram 3")

Fourth diagram (coming soon):
![flights-class-diagramv04.png](./design/flights-class-diagramv04.png?raw=true "Diagram 4")

###### **3) Top Down Diagram**
The first version is the only one available right now, the fast phase of the project's execution (3 days and a half) requires count with a clear vision of the project since the first contact, in this case, the below diagram needs to be updated
![topdown-v1.png](./design/topdown-v1.png?raw=true "Top Down V1")


## Proof of Concept
###### **1) Version 1 Video Walk Through**
TO-DO

###### **2) Version 2 Video Walk Through**
TO-DO

## TO-DO list
###### Version 1:
-Methods: updateData, deleteData, updateStatus
-update flight's status to delay and departure/arrive info
-update flight's status to cancel and document reasons
-Send report in PDF format by Email