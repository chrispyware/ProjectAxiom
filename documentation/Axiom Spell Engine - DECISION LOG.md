# Table of Contents {#table-of-contents}

Key:  
TD – Technical Decision  
GD – Game Design Decision  
AD – Art Direction Decision  
UX – User Experience Decision  
ND – Narrative Decision  
PD – Production/Project Decision

[**Table of Contents	1**](#table-of-contents)

[**DECISION LOG	2**](#decision-log)

[TD-XXX: Short description (where X is an incremented number)	2](#td-xxx:-short-description-\(where-x-is-an-incremented-number\))

[**TD-001: Build System and Build-Script Language	3**](#td-001:-build-system-and-build-script-language)

[**PD-001: Initial Project Scope	4**](#pd-001:-initial-project-scope)

[**GD-001: First Vertical Slice	5**](#gd-001:-first-vertical-slice)

[**TD-002: Initial Runtime and Technology Foundation	6**](#td-002:-initial-runtime-and-technology-foundation)

[**TD-003: Fixed Timestep Simulation	7**](#td-003:-fixed-timestep-simulation)

[**TD-004: Java Toolchain and Dependency Policy	8**](#td-004:-java-toolchain-and-dependency-policy)

# 

# DECISION LOG {#decision-log}

This document records significant game and technical design decisions, including their context, considered alternatives, tradeoffs, and conditions for reconsideration.  
**Reconsider if:** Conditions or new information that would justify revisiting the decision.   
The format will be as follows:

## **TD-XXX**: Short description (where X is an incremented number) {#td-xxx:-short-description-(where-x-is-an-incremented-number)}

Date (Month, DD, YYYY)  
**Status:** Proposed | In Review | Accepted | Superseded | Rejected  
**Category:** Tech | Game Des | Art Dir | User Ex | Narr | Prod  
**Decision:** Description of course of action  
**Supersedes:** Previous decision identifier, if applicable  
**Alternatives considered:** Any options of alternative routes

**Context**  
Description of the problem, proposed solutions

**Goals/Consequences**  
List of consequences downstream from this decision

**Reconsider if**  
List of items that can be cause for concern

## 

# **TD-001:** Build System and Build-Script Language {#td-001:-build-system-and-build-script-language}

August 08, 2026  
**Status:** Accepted  
**Category:** Technical  
**Decision:** Use Gradle with Kotlin DSL  
**Alternatives considered:** Maven; Gradle with Groovy DSL 

**Context**

The engine will be developed in Java using LWJGL and may eventually require platform-specific native dependencies, asset-processing tasks, multiple executable targets, multiple project modules, and platform-specific runtime packaging.

Maven is familiar and capable of supporting the project. However, Gradle’s task model and build-script flexibility are better suited to the project’s likely asset-processing and distribution workflows. Choosing Gradle while the project is small avoids a potentially disruptive migration later.

Kotlin DSL was selected instead of Groovy DSL because it provides static typing, stronger IDE assistance, and syntax more familiar to a Java developer.

**Goals/Consequences**

* Gradle must be learned alongside the initial engine work.  
* The project will use `build.gradle.kts` and `settings.gradle.kts`.  
* The Gradle Wrapper will define the build version used by the project.  
* The initial build will remain conventional and minimal.  
* Custom tasks and multiple modules will be added only when required by concrete project needs.  
* Some Groovy-based examples and tutorials will need to be translated into Kotlin DSL.

**Reconsider if**

* A required tool has substantially better Maven integration.  
* Gradle creates persistent build-maintenance problems.  
* The expected asset and packaging pipeline never materializes and Maven would materially simplify maintenance.

# **PD-001:** Initial Project Scope {#pd-001:-initial-project-scope}

August 09, 2026  
**Status:** Accepted  
**Category:** Project  
**Decision:** Develop a custom Java-based 2D engine alongside a single-player metroidvania prototype, targeting Windows desktop first.   
**Alternatives considered:** 

**Context**

A Java-based 2D engine developed alongside a single-player metroidvania prototype. It will support keyboard/mouse and controllers, deterministic 2D simulation, sprite rendering, tile-based environments, composable spells, and mathematical trajectory previews. The initial target is desktop, beginning with Windows. 

**Goals/Consequences**

Initial non-goals for engine:

* 3D rendering  
* Multiplayer  
* Console or mobile support  
* General-purpose visual editor  
* Modding or scripting  
* Procedural world generation  
* Multiple graphics backends  
* Fully general rigid-body physics  
* Supporting arbitrary game genres

**Reconsider if**

* The vertical slice requires a capability currently listed as a non-goal.  
* Windows-first development prevents reasonable future desktop portability.  
* Engine development repeatedly delays testing the game’s defining mechanics.  
* The intended game genre or perspective changes substantially.

# **GD-001:** First Vertical Slice {#gd-001:-first-vertical-slice}

August 09, 2026  
**Status:** Accepted  
**Category:** Game Design  
**Decision:** The contents for the first vertical slice  
**Alternatives considered:** 

**Context**

One small 2D room in which the player can move and jump, aim with a controller, cast a parabolic fire projectile, cast a reflecting light ray, hit targets, and open an exit.

**Goals/Consequences**

* Running and jumping  
* Collisions  
* Static rectangular terrain  
* One controller and keyboard control scheme  
* Camera following  
* Fireball aiming with an arc preview  
* Laser aiming with a reflection preview  
* One enemy or destructible target  
* One light-activated mechanism  
* One fire-activated mechanism  
* A basic HUD  
* Temporary placeholder visuals and sounds  
* The room can be completed from beginning to end.  
* Keyboard and controller can both operate all required actions.  
* The fireball follows its displayed preview closely enough that discrepancies are not perceptible.  
* The light ray follows its displayed reflected path.  
* At least one puzzle requires fire and one requires reflected light.  
* The prototype remains stable through repeated restarts and completions.  
* A new player can understand the essential interactions without developer assistance.

**Reconsider if**

* A listed feature does not help evaluate the core spell concept.  
* Playtesting reveals that another feature is necessary to evaluate that concept.  
* The slice becomes too large to complete as the first playable milestone.

# **TD-002:** Initial Runtime and Technology Foundation {#td-002:-initial-runtime-and-technology-foundation}

August 09, 2026  
**Status:** Accepted  
**Category:** Technical  
**Decision:** Technology stack that will be used.  
**Alternatives considered:** A complete engine like Godot, Unity, or Unreal

**Context**  
These will be the initial choices for the technology stack that will be used for the engine. The exact dependency versions will be recorded throughout the project.

Language: Java  
Build system: Gradle with Kotlin DSL  
Platform layer: LWJGL  
Window/input: GLFW  
Graphics: OpenGL  
Audio: OpenAL  
Mathematics: JOML  
Testing: JUnit  
Initial platform: Windows desktop

**Goals/Consequences**

* Versions for the layers will need to be decided  
* Compatibility needs to be considered  
* Java will be the overall language of the project  
* LWJGL supplies low-level native bindings while leaving engine structure, resource ownership, rendering, simulation, and game architecture under my control  
* LWJGL is a library of native bindings, not a complete engine or game framework.  
* OpenGL will be the only initial rendering backend.  
* Native dependencies must be selected and packaged per operating system.  
* Java alone does not guarantee portability; platform-specific behavior and native libraries must still be tested.  
* macOS and Linux compatibility are design considerations, not initial release requirements.  
* 

**Reconsider if**

* Serious compatibility problems arise

# **TD-003:** Fixed Timestep Simulation {#td-003:-fixed-timestep-simulation}

August 09, 2026  
**Status:** Accepted  
**Category:** Technical  
**Decision:** Determine the timestep simulation which governs movement, collision, projectiles, spell previews, animations, and reproducibility.  
**Alternatives considered:** 

**Context**  
This is the proposed coordinated conventions for the simulation model. The following will be the guidelines for how the engine approaches movement, rendering, and input:

* Fixed gameplay updates at 60 updates per second  
* Rendering as often as possible  
* Interpolation between simulation states  
* Frame-time clamping after stalls  
* Game state changed only during fixed updates  
* Input events collected continuously but consumed by the simulation predictably  
* World coordinates: floating point  
* Positive X: right  
* Positive Y: up  
* Angles: radians internally  
* Rotation: counterclockwise positive  
* Time: seconds  
* Rendering origin: converted internally as required

**Goals/Consequences**

* These will be the determining factors for pretty much all of the internal workings of the engine.  
* Important considerations will also be made such as gravity not necessarily being negative Y since gravity manipulation is part of the game concept.   
* Rendering runs once per outer loop iteration, with presentation frequency potentially limited by vertical synchronization or an explicit frame cap.   
* The simulation is intended to produce consistent results under the same inputs and runtime conditions; cross-platform bit-for-bit determinism is not currently required. 

**Reconsider if**

* The decisions over-complicate actions within the engine or within the gameplay itself.

# **TD-004:** Java Toolchain and Dependency Policy {#td-004:-java-toolchain-and-dependency-policy}

August 09, 2026  
**Status:** Accepted  
**Category:** Technical  
**Decision:** Use Java 21 as the source, compilation, test, and runtime target. Enforce Java 21 using Gradle’s Java toolchain support. Pin Gradle 9.6.1 through the committed Gradle Wrapper. Declare direct dependency and plugin versions centrally in `gradle/libs.versions.toml`, using the LWJGL BOM to keep LWJGL module versions aligned. Do not use dynamic versions, version ranges, snapshots, or automatic dependency upgrades. Initially include Windows native dependencies, with macOS and Linux natives added when those platforms enter active testing.   
**Alternatives considered:** Java 17, newer Java releases, Gradle 8.5, declaring versions in `build.gradle.kts`

**Context**

The project requires a reproducible Java and Gradle environment so that builds do not depend on whichever tools happen to be installed locally.

Java 21 provides a stable long-term-support baseline without requiring the project to adopt a newer language release before its libraries and development tools have been validated. Gradle’s Java toolchain will define the JDK used to compile and test the project. The Gradle Wrapper will define the Gradle version used to execute the build.

Dependency versions will be declared in the Gradle version catalog at `gradle/libs.versions.toml`. This provides one location for reviewing and updating project dependencies. The LWJGL BOM will ensure that LWJGL modules use mutually compatible versions.

Dependency upgrades will be deliberate changes. Each upgrade should be built, tested, and committed separately from unrelated engine work.

Initial development targets Windows. Build configuration should avoid unnecessary assumptions that prevent later macOS or Linux support, but native dependencies and packaging for those systems are deferred until those platforms enter active testing.

**Goals/Consequences**

* Developers will use the Gradle Wrapper rather than relying on a separately installed Gradle version.  
* Compilation and testing will consistently target Java 21\.  
* Direct dependency versions will be visible in one version-catalog file.  
* LWJGL module versions will remain aligned through its BOM.  
* Builds will not change unexpectedly because a dynamic dependency selected a newer release.  
* Dependency upgrades will require intentional maintenance.  
* Gradle and Java upgrades may require coordinated changes.  
* Windows native libraries will be configured first.  
* macOS and Linux support will require later native-dependency, packaging, and runtime testing.  
* The project will initially require a Java 21 development environment; runtime bundling will be addressed separately before distribution.  
* The Gradle Wrapper version belongs in `gradle/wrapper/gradle-wrapper.properties`.  
* The Java toolchain version belongs in `build.gradle.kts`.  
* Library and plugin versions belong in `gradle/libs.versions.toml`.  
* The repository should include `gradlew`, `gradlew.bat`, the `gradle/wrapper` files, and the version catalog.

**Reconsider if**

* A required library or development tool no longer supports Java 21\.  
* A newer Java LTS release provides a substantial project benefit that justifies migration.  
* A security or support concern requires upgrading Java or Gradle.  
* A required Gradle plugin is incompatible with the selected Gradle version.  
* The version catalog creates more maintenance than value for the project’s size.  
* macOS or Linux becomes an active development or testing target.  
* Runtime packaging imposes a different minimum Java requirement.

# **TD-005:** Initial Source and Module Structure

August 11, 2026  
**Status:** Accepted  
**Category:** Technical  
**Decision:** Use `com.chrispyware.axiom` as the Gradle group and base Java namespace. Engine code resides beneath `com.chrispyware.axiom.engine`; game code resides beneath `com.chrispyware.axiom.game`. Project Axiom may depend on Axiom Spell Engine, while the engine must not depend on game code.   
**Alternatives considered:** 

**Context**

* Project Axiom and the Axiom Spell Engine are being developed in the same repository but represent separate architectural units. The game uses the engine, while the engine should remain independent of Project Axiom’s game-specific classes, rules, content, and assets.  
* A clear package and module structure is needed to communicate ownership, prevent circular dependencies, and establish predictable locations for source code and tests. Separate Gradle subprojects allow Gradle and the Java compiler to enforce the dependency direction rather than relying only on developer discipline.  
* The reverse-domain namespace `com.chrispyware.axiom` provides a stable base for the project. Engine code and game code will be distinguished using the additional `engine` and `game` qualifiers.  
* The initial build will contain two Gradle subprojects:  
* :engine  
* :game  
* The `game` subproject will depend on `engine`. The `engine` subproject will not depend on `game`.

**Goals/Consequences**

* Engine production code will reside under:  
  engine/src/main/java/com/chrispyware/axiom/engine/  
* Engine tests will reside under:  
  engine/src/test/java/com/chrispyware/axiom/engine/  
* Game production code will reside under:  
  game/src/main/java/com/chrispyware/axiom/game/  
* Game tests will reside under:  
  game/src/test/java/com/chrispyware/axiom/game/  
* The Gradle group will be:  
  com.chrispyware.axiom  
* Game code may import and use engine code.  
* Engine code cannot import game code because the `engine` subproject has no dependency on `game`.  
* Engine systems must not contain Project Axiom-specific concepts such as named spells, enemies, story progression, or game-specific mechanisms.  
* Shared low-level facilities—such as timing, rendering, input, collision queries, and mathematical primitives—may be implemented in the engine.  
* Game-specific behavior—such as the fireball spell, player controller, enemies, and puzzle rules—will be implemented in the game.  
* Each subproject can maintain its own production resources and tests.  
* The separation introduces a small amount of additional Gradle configuration.  
* Moving a class across the engine/game boundary may require package, import, test, and build-configuration changes.  
* A separate launcher or tools subproject may be introduced later when an actual packaging or tooling requirement justifies it.  
* New packages will be created as needed rather than pre-populating the repository with empty package trees. 

**Reconsider if**

* A class or subsystem cannot be assigned clearly to either the engine or game after concrete use cases have been implemented.  
* The game repeatedly needs access to engine internals that are not suitable as a public engine API.  
* The two-subproject structure causes substantial development or packaging friction.  
* A second executable—such as an asset processor, level tool, or dedicated test application—requires its own subproject.  
* Desktop packaging requires the launcher and native dependencies to be isolated from game logic.  
* The engine is reused by another game, requiring stronger API, resource, or publication boundaries.  
* Platform-specific launchers become necessary for Windows, macOS, or Linux.  
* The project name or organizational namespace changes.  
* `com.chrispyware.axiom.engine` or `com.chrispyware.axiom.game` no longer accurately describes the ownership of the contained code.