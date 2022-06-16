# Super Among Us

## 1. About

"Super Among Us" is a 2D parkour game, inspired to "Super Mario Bros" and similar games that many of us used to play
in our childhood. The player skin is inspired to "Among us" a more recent game.

The aim of the game is to reach the end of the level by jumping on blocks and platforms without falling into the spikes,
action that will teleport immediately the player to the beginning of the level.
The game contains a series of four levels ordered by difficulty and available only after completing the previous ones.
To enable multiple users to play the game on the same device taking turns, an account system was implemented to store what levels
were unlocked by the user and this can be accessed by a login page that appears right after running the game.

### 1.1. Demo

Add a link to a demo of your project.

## 2. Authors

This project was created by:

- Fabio Marconi
- Andrea Parodi
- Alessio Eritale

## 3. Usage

Describe how to compile, package, and run your project here.

To build the project, run:

```shell
mvn package
```

To do X, run:

```
mvn abc
```

## 4. Implementation

### 4.1. Architectural Overview

Describe the architecture of your application:

Considering the complexity of this project, it's composed by many ... !!!!!!

### 4.2. Third-Party Libraries

List and justify the third party libraries you used.

- frontend

  - import java.awt.geom.Rectangle2D;
  - import java.awt.image.BufferedImage;
  - import java.util.concurrent.TimeUnit;
  - import javax.imageio.ImageIO;
  - import java.io.IOException;

- txt database for users

  - import java.io.File;
  - import java.util.ArrayList;
  - import java.util.Scanner;

- keyhandler to enable player movement

  - import java.awt.event.KeyEvent;
  - import java.awt.event.KeyListener;

- sound

  - import javax.sound.sampled.AudioInputStream;
  - import javax.sound.sampled.AudioSystem;
  - import javax.sound.sampled.Clip;
  - import javax.sound.sampled.FloatControl;

- boh
  - import java.net.URL;
  - import java.awt.\_;
  - import javax.swing.\_;
  - import java.net.URISyntaxException;
  - import java.io.InputStream;

### 4.3. Programming Techniques

List and explain how you used the 10 programming techniques required for this project.

- **Technique 1**: We used interfaces to do X, because of Y.
- **Technique 2**: ...
- ...

### 4.4. Tests

Briefly describe and motivate your test suite.

## 5. Experience

### 5.1. Overall Experience

Describe your overall experience in developing this project.

### 5.2. Division of Responsibilities

Describe the roles and responsibilities each member had in this project.

We all agree that the responsibilities division was mostly balanced and reflected the personal skills and inclinations of each group member. The concept behind the project was ideated by all three of us and so was for the design of the four levels. We devided the project into two main sections that were developed simultaneously:

- the "login and menu": Alessio Eritale
- the "game" : Andrea Parodi & Fabio Marconi

later the two sections converged in a single project with the creation of the level selector.

- **Student 1:** I focused more on the player animation, the level creation and .....
- **Student 2:** Fabio and I worked together on the game itself. I focused more on implementing the physics of the game, in particular on the gravity, collisions, winning and failing of the player, etc.
- **Student 3:** I focused more on the creation of the login form and the level menu...

### 5.3. Main Challenges

Elaborate on the main challenges each group member faced throughout the project and how they were surpassed.

- **Student 1:** The most difficult aspect in this project for me was to implement the units tests. The reason was X so I had to do Y and I achived Z.

- **Student 2:** In general working with Fabio on the same classes wasn't always easy because we both had different ideas and perspective on how to implement the different aspects of the game, but after efforts we managed to produce a code that belong to both of us.

- **Student 3:** ...

### 5.4. Learning Outcomes

Describe what you learned with this project.

- **Student 1:** I learned a new library called `xyz`!
- **Student 2:** I learned on how to work on the backend of a big and complex project and how to implement some aspects of game development, such as physics and collisions.
- **Student 3:** ...
