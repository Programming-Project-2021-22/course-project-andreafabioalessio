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

https://youtu.be/L-U0TyOmq9E

## 2. Authors

This project was created by:

- Fabio Marconi
- Andrea Parodi
- Alessio Eritale

## 3. Usage

Describe how to compile, package, and run your project here.

To build the project, run:

```shell
mvn compile
```
```shell
mvn package
```
To run the jar:

```
java -jar target/course-project-andreafabioalessio-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## 4. Implementation

### 4.1. Architectural Overview

Considering the complexity of this project, it's composed by many parts.

The project can be divided in two major parts, the Game and the Login/Signup. 
The switch between these two parts is coordinated by a system called GameStates, which orchestrates the level load and the opening of the menu (and more..).

### 4.2. Third-Party Libraries

- frontend

  - import java.awt.geom.Rectangle2D;
  - import java.awt.image.BufferedImage;
  - import java.util.concurrent.TimeUnit;
  - import javax.imageio.ImageIO;
  - import java.io.IOException;
  - import java.awt.\_;
  - import javax.swing.\_;
  - import java.io.InputStream;

- txt database for users

  - import java.io.File;
  - import java.util.ArrayList;
  - import java.util.Scanner;

- KeyHandler to enable player movement

  - import java.awt.event.KeyEvent;
  - import java.awt.event.KeyListener;

- sound

  - import javax.sound.sampled.AudioInputStream;
  - import javax.sound.sampled.AudioSystem;
  - import javax.sound.sampled.Clip;
  - import javax.sound.sampled.FloatControl;

- boh
  - import java.net.URL;
  - import java.net.URISyntaxException;


### 4.3. Programming Techniques

- **Technique 1**: Abstract Classes:
                   - Entity class: Abstract class parent of the Player class
                   - Registration class: Abstract class parent of the Login and Signup class

- **Technique 2**: Collections:
                   - We have used Arraylists and Arrays to store Users, Levels, Images and Sounds

- **Technique 3**: Custom exceptions:
                   - Custom exceptions that get thrown during the login and/or signup process.
                   - Custom exception that gets thrown whenever a User tries to play a locked level

- **Technique 4**: Try-catch blocks handling:
                   - Try-catch blocks have been implemented in various classes for example:
                      - to avoid floor collision bugs
                      - to catch IOExceptions thrown by calling the ImageIO.read() method.

- **Technique 5**: Method overriding:
                    - Player class: Overridden the update() method
                    - All UI classes: Overridden the paintComponent(Graphics g) method
                    - Login and Signup: Overridden checkUserInArray() method inherited from parent class Registration

- **Technique 6**: Lambda expressions:
                    - Used in various classes to process button presses, for example
                      - Login class: Lambda expression used to process the loginButton press

- **Technique 7**: File I/O:
                    - File I/O was used to store User's data inside a .txt file, which was then used to instantiate Users.

- **Technique 8**: Regular expression:
                    - Signup class: Regex used to check if the password matched the required criteria

- **Technique 9**: Multithreading
                    - 

- **Technique 10**: Test hooks:
                    - Two test hooks have been implemented to avoid unwanted changes to the usersList.txt file during tests.

- **Technique 11**: Logging:
                    - A login method allows users to retrieve previous data, without losing the progress made everytime the game is closed.

- **Technique 12**: Graphical user interface
                    - Our program is a game based on a graphical user interface. We have mainly used JavaSwing.

### 4.4. Tests

Our test suite includes tests to check the login and signup process.
They are aimed to check that no duplicate users register and that the data provided by players is in line with the requirements.

## 5. Experience

### 5.1. Overall Experience

This project has been very fun to develop, although we have faced many challenges and issues, but we are very satisfied with the end product and with all the features
we were able to implement.

### 5.2. Division of Responsibilities

We all agree that the responsibilities' division was mostly balanced and reflected the personal skills and inclinations of each group member. The concept behind the project was ideated by all three of us and so was for the design of the four levels. We devided the project into two main sections that were developed simultaneously:

- the "login and menu": Alessio Eritale
- the "game" : Andrea Parodi & Fabio Marconi

later the two sections converged in a single project with the creation of the level selector.

- **Fabio:** I focused more on the player class, the sprite animation, the level creation and the merging of all the git branches. 

- **Andrea:** Fabio and I worked together on the game itself. I focused more on implementing the physics of the game, in particular on the gravity, collisions, winning and failing of the player, etc.

- **Alessio:** I focused more on the creation of the login and signup form, tests, the level menu, methods to save player's data and all the game graphics beside the level graphics and player sprites.

### 5.3. Main Challenges

- **Student 1:** The most difficult aspect in this project for me was the collaboration with Andrea. We both had good but different ideas on how to implement different aspects of the game. But in the end we managed to support each other's ideas and come up with good solutions or alternatives.

- **Andrea:** In general working with Fabio on the same classes wasn't always easy because we both had different ideas and perspective on how to implement the different aspects of the game, but after efforts we managed to produce a code that belong to both of us.

- **Alessio:** Creating the UI was not particularly hard, even though I learned a lot of new techniques. The hardest part for me was thinking about all the scenarios possible for the login and signup form, adding all the components necessary, and linking the various methods with scenarios happening during the game.

### 5.4. Learning Outcomes

- **Student 1:** I learned a lot about how a Game gets developed, how the game loop works and primarily how difficult it is to organize the workload among the team. Now I can really appreciate every single game I will play in the future. Even if it has some bugs!
- **Student 2:** I learned on how to work on the backend of a big and complex project and how to implement some aspects of game development, such as physics and collisions.
- **Student 3:** I learned a lot about GUI, file input and output, and much more. I also learned how to use git better and the importance of team communication.
