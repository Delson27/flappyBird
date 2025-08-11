# Flappy Bird Game

A classic Flappy Bird game implementation built with Java and Swing. This game features smooth animations, collision detection, and responsive controls for an authentic gaming experience.

## Features

- **Smooth Gameplay** - Realistic bird physics with gravity and jump mechanics
- **Dynamic Obstacles** - Randomly generated pipes with varying heights
- **Collision Detection** - Precise collision system for bird-pipe and bird-ground interactions
- **Score System** - Track your high score as you navigate through pipes
- **Custom Graphics** - Original artwork with bird, pipes, and background images
- **Responsive Controls** - Space bar or mouse click for jumping
- **Cross-Platform** - Runs on Windows, macOS, and Linux

## Technologies Used

- **Java 8+** - Core programming language
- **Swing** - GUI framework for game window and rendering
- **AWT** - Graphics and image handling
- **Object-Oriented Design** - Clean architecture with separate classes for game entities

## Project Structure

```
flappyBird/
├── README.md                    # This file
├── src/
│   ├── App.java                 # Main application entry point
│   ├── FlappyBird.java          # Main game class with game loop
│   ├── flappybird.png           # Bird sprite image
│   ├── flappybirdbg.png         # Background image
│   ├── bottompipe.png           # Bottom pipe obstacle image
│   ├── toppipe.png              # Top pipe obstacle image
│   └── [compiled .class files]  # Java compiled classes
├── bin/
│   ├── App.class                # Compiled main class
│   ├── FlappyBird.class         # Compiled game class
│   ├── FlappyBird$Bird.class   # Compiled Bird inner class
│   ├── FlappyBird$Pipe.class   # Compiled Pipe inner class
│   └── [game assets]           # Images and resources
└── lib/                         # External libraries (if any)
```

## Game Mechanics

### Bird Physics

- **Gravity**: Constant downward force applied to the bird
- **Jump**: Upward velocity boost when space bar is pressed
- **Rotation**: Bird tilts based on velocity for realistic movement

### Pipe System

- **Generation**: Pipes spawn at regular intervals from the right
- **Randomization**: Pipe heights vary to create challenging gaps
- **Movement**: Pipes move leftwards at constant speed
- **Scoring**: +1 point for each pipe successfully passed

### Collision Detection

- **Bird-Pipe**: Game ends when bird touches any pipe
- **Bird-Ground**: Game ends when bird hits the ground or ceiling
- **Precision**: Pixel-perfect collision detection for fair gameplay

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code) or command line

### Installation

1. **Clone the repository**

   ```bash
   git clone [repository-url]
   cd flappyBird
   ```

2. **Compile the game**

   ```bash
   javac -cp src src/FlappyBird.java src/App.java
   ```

3. **Run the game**
   ```bash
   java -cp src App
   ```

### Alternative Run Methods

**Using IDE:**

- Open the project in your preferred Java IDE
- Run the `App.java` file as a Java application

**Using JAR file (if available):**

```bash
java -jar FlappyBird.jar
```

## Controls

| Key             | Action                       |
| --------------- | ---------------------------- |
| **Space**       | Jump (flap bird)             |
| **Mouse Click** | Jump (alternative control)   |
| **R**           | Restart game after game over |
| **ESC**         | Exit game                    |

## Customization

### Game Settings

You can modify these constants in `FlappyBird.java`:

```java
// Game speed
private static final int GAME_SPEED = 2;

// Bird physics
private static final double GRAVITY = 0.5;
private static final double JUMP_STRENGTH = -8;

// Pipe settings
private static final int PIPE_WIDTH = 50;
private static final int PIPE_GAP = 150;
private static final int PIPE_SPACING = 300;
```

### Graphics

- Replace image files in `src/` directory to customize appearance
- Images should be PNG format with transparency for best results
- Recommended sizes:
  - Bird: 34x24 pixels
  - Background: 288x512 pixels
  - Pipes: 52x320 pixels

### Audio

- Add sound effects by implementing Java Sound API
- Place audio files in `src/` directory
- Supported formats: WAV, AIFF, AU

## Development

### Architecture Overview

```
FlappyBird (Main Game)
├── Bird (Inner Class)
│   ├── Position (x, y)
│   ├── Velocity (gravity, jump)
│   └── Render (draw bird)
├── Pipe (Inner Class)
│   ├── Position (x, y)
│   ├── Dimensions (width, height)
│   └── Render (draw pipes)
└── Game Loop
    ├── Update physics
    ├── Handle input
    ├── Check collisions
    └── Render graphics
```
