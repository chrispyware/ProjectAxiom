# Table of Contents {#table-of-contents}

[**Table of Contents	1**](#table-of-contents)

[**Vertical Slice Document	2**](#vertical-slice-document)

[**Milestone 1: Engine Heartbeat	2**](#milestone-1:-engine-heartbeat)

# Vertical Slice Document {#vertical-slice-document}

# Milestone 1: Engine Heartbeat {#milestone-1:-engine-heartbeat}

**Objective:** Create a reproducible Axiom Spell Engine application that opens a window, runs a fixed-step simulation, renders a clear color, and shuts down correctly.

**Required Work:** 

* Initialize GLFW.  
* Create a window and OpenGL context.  
* Initialize OpenGL capabilities.  
* Poll operating-system events.  
* Run fixed updates at 60 updates per second.  
* Render once per outer-loop iteration.  
* Clamp excessive accumulated frame time.  
* Close through both the window control and an input action.  
* Release native resources in a defined order.  
* Launch through the Gradle Wrapper.  
* Add tests for timing logic that does not require a graphics context.

**Acceptance Criteria:**

* `gradlew.bat build` succeeds.  
* The application launches through Gradle.  
* The window remains responsive.  
* The simulation update count is approximately 60 per elapsed second.  
* Resizing or moving the window does not permanently destabilize simulation timing.  
* Closing the application exits without native-library errors.  
* The application can be launched and closed repeatedly.  
* A clean checkout can be built using documented instructions.

**Decisions During Implementation:**

* Exact application and game-loop class structure  
* Logging library  
* Exception-handling strategy  
* Window configuration  
* VSync and frame-cap behavior  
* Debug statistics  
* Input class design  
* Rendering abstraction  
* Resource interfaces  
* Module boundaries