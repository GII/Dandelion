# Dandelion
Dandelion [Varela et al. 2014, Varela et al. 2013b] has been designed and implemented as a UI development framework for Ambient Intelligence systems. It provides an implementation of the three TIAF (Thhreefold Interaction Abstraction Framework) abstraction levels as a distributed user interaction management system that uses model-driven engineering techniques to make the development of distributed physical user interfaces (DPUIs) easier and cheaper.

By using Dandelion, AmI developers are decoupled from the specific modalities, technologies and even physical location of the Interaction Resources (IRs) used to implement a particular DPUI. 

Developers can design and describe the UIs at the abstract level using the UsiXML language, and then implement the application UI control logic on top of the abstract concepts defined in the abstract UI. Dandelion uses a distributed user interaction controller to connect those abstract elements with the physical elements that perform the interaction with the user. This connection is managed by Dandelion itself, that performs the translation from the abstract concepts to the real interaction with the user. It does so by relying on a series of distributed proxy-like components that elevate any kind of device or software component to the status of an Interaction Resource (IR). They provide a common interface of interaction operations that is remotely accessible though a networking protocol called the Generic Interaction Protocol (GIP) [Varela et al., 2013b, Varela et al., 2013a]. Furthermore, in order to facilitate compatibility with a wide range of physical devices, Dandelion introduces a device abstraction technology called UniDA [Varela et al., 2011], which decouples Dandelion from the specific APIs of each device manufacturer. Furthermore, Dandelion uses Fuzzy Inference Systems and context models to adatp, autonomously and dynamnically, at real-time the UI to the caracteristics of each usage scenario where the system is being used.


References:

[Varela et al. 2014] Gervasio Varela, Alejandro Paz-Lopez, Jose Antonio Becerra Permuy, Richard J. Duro Fernandez, Prototyping Distributed Physical User Inter- faces in Ambient Intelligence Setups, Proceedings of the 2nd International Conference on Distributed, Ambient and Pervasive Environments (DAPI 2014), pp 76 - 85, Heraklion, Greece, Springer, 2014.

[Varela et al. 2013a] Varela G., Paz-Lopez A., Becerra J.A. and Duro R.J, The Generic Interac- tion Protocol: Increasing portability of distributed physical user interfaces, Romanian Journal of Human - Computer Interaction, 6 (3), pp 249 - 268, 2013.

[Varela et al. 2013b] Gervasio Varela, Autonomous adaptation of user interfaces to support mobility in ambient intelligence systems, Proceedings of the 5th ACM SIGCHI symposium on Engineering Interactive Computing Systems (EICS 2013), pp 179 - 182, London, U.K., ACM, 2013.

[Varela et al. 2011] Gervasio Varela, Alejandro Paz-Lopez, Jose Antonio Becerra, Santiago Vazquez-Rodriguez and Richard José Duro, UniDA: Uniform Device Ac- cess Framework for Human Interaction Environments, Sensors, 11 (10), pp 9361 - 9392, MDPI, 2011.
