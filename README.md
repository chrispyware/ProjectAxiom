# Project Axiom

Project Axiom is an experimental 2D metroidvania in which scientific and mathematical concepts become intuitive magical abilities. The game is being developed alongside its custom Java engine, the **Axiom Spell Engine**.

The project is primarily a learning exercise in game-engine and game development, with the long-term stretch goal of producing a complete, releasable game.

## Project status

Project Axiom is in early development. The repository currently contains the initial Gradle multi-project build; gameplay and engine implementation have not yet begun.

The first technical milestone is the **Engine Heartbeat**: a desktop application that opens a window, runs a fixed-timestep simulation, renders through OpenGL, and shuts down cleanly.

## Design goals

- Express science and mathematics through intuitive interaction rather than classroom-style calculations.
- Build composable spells whose behavior is visible and predictable.
- Support responsive 2D platforming, exploration, combat, and environmental puzzles.
- Develop the engine alongside the game so that concrete gameplay requirements guide its architecture.
- Favor focused, testable systems over premature general-purpose abstractions.

## Game concept

Players discover scientific phenomena and mathematical behaviors, then combine them into spells. A fire effect might be paired with a parabolic trajectory to create a fireball, while a light effect might use reflection to produce a beam that bounces from surfaces.

Spell previews should communicate these behaviors visually. Players manipulate trajectories, reflections, waves, and related concepts through direct controls without needing to solve equations during normal gameplay.

## Repository structure

```text
ProjectAxiom/
|-- assets/          Project Axiom art, audio, levels, and game data
|-- documentation/   Project brief, vertical-slice specification, and decision log
|-- engine/          Axiom Spell Engine Java library
|-- game/            Project Axiom executable application
|-- gradle/          Gradle Wrapper configuration and version catalog
|-- build.gradle.kts Shared root build configuration
|-- settings.gradle.kts
|-- gradlew
`-- gradlew.bat
```

The build enforces this dependency direction:

```text
Project Axiom game
        |
        v
Axiom Spell Engine
```

The engine must not depend on game-specific code.

## Technology foundation

- Java 21
- Gradle 9.6.1 with Kotlin DSL
- LWJGL for native platform bindings
- GLFW for windows and input
- OpenGL for graphics
- OpenAL for audio
- JOML for mathematics
- JUnit for automated testing

LWJGL and the other runtime libraries are part of the planned technology foundation and will be added as their corresponding engine milestones begin.

Initial development targets Windows desktop. macOS and Linux support may be added after the Windows version is established and can be tested on those platforms.

## Requirements

- A Java 21 JDK
- Git

A separate Gradle installation is not required. The repository includes the Gradle Wrapper, which downloads and runs the pinned Gradle version.

## Building

From the repository root on Windows:

```cmd
gradlew.bat build
```

On macOS or Linux:

```sh
./gradlew build
```

The current build validates both subprojects:

```text
:engine
:game
```

There is not yet a runnable game entry point. Run instructions will be added during the Engine Heartbeat milestone.

## Initial vertical slice

The first vertical slice is planned as one small 2D room containing:

- Running and jumping
- Static rectangular terrain and collision
- Keyboard and controller input
- A camera that follows the player
- A fireball with a parabolic trajectory preview
- A light ray with a reflection preview
- Fire- and light-activated mechanisms
- A target or basic enemy
- A basic HUD
- Placeholder visuals and sounds

The purpose of the slice is to test whether mathematically driven spells are understandable, predictable, and enjoyable before expanding the engine or game.

## Development approach

Development proceeds through small playable milestones:

1. Establish the application window and fixed-timestep game loop.
2. Add action-based keyboard and controller input.
3. Render basic 2D primitives and debug lines.
4. Build the initial platforming room.
5. Prototype the parabolic fire spell.
6. Prototype the reflecting light spell.
7. Introduce a minimal spell-construction system.
8. Integrate and playtest the first vertical slice.

Important architectural choices are recorded in the project decision log under `documentation/`.

## License

No public license has been selected yet.
