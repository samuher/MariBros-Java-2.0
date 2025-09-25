# Assignment 1: Mario Bros (simplified version)

**Submission: 13th of october at 09:00**
 
**Objectives:** Introduction to object orientation and to Java; use of arrays and enumerations;
string handling with the `String` class; input-output on the console.

<!--
**FAQ**: Como es habitual (y normal) que tengáis dudas, las iremos recopilando en este [documento de preguntas frecuentes](../faq.md). Para saber los últimos cambios que se han introducido [puedes consultar la historia del documento](https://github.com/informaticaucm-TPI/2425-Lemmings/commits/main/enunciados/faq.md).
-->

<!-- TOC start -->
- [Plagiarism](#plagiarism)
- [1. Description of the assignment](#1-description)
  * [1.1 Introduction](#11-introduction)
  * [1.2. Details](#12-details)
- [2. Structure of the project](#2-project-structure)
- [3. Dividing the implementation into stages](#3-stages)
  * [3.1 Displaying the board](#31-display)
  * [3.2 Basic commands and the main loop](#32-main-loop-basic-commands)
  * [3.3 Automatic movement and user actions](#33-automatic-movement-and-user-actions)
  * [3.4 Mario actions](#34-mario-actions)
  * [3.5 Collisions Mario ↔ ExitDoor](#35-collisions-mario-exitdoor)
  * [3.6 Collisions Mario ↔ Goombas](#36-collisions-mario-goombas)
- [4. Submission](#4-submission)
- [5. Testing](#5-testing)
  * [JUnit](51-JUnit)
<!-- TOC end -->

<!-- TOC --><a name="plagiarism"></a>
## Plagiarism

For each of the TP assigmments, all the submissions from all the different TP groups will be checked using anti-plagiarism software, firstly by comparing them all pairwise and secondly, by searching to see if any of their code is copied from other sources on the Internet. Any plagiarism detected will be reported to the *Comité de Actuación ante Copias* which, after interviewing the student or students in question, will decide whether further action is appropriate. This may include the opening of disciplinary proceedings with the relevant university authority (*Inspección de Servicios*).

<!-- TOC --><a name="1-description"></a>
# 1. Description of the assignment

<!-- TOC --><a name="11-introduction"></a>
## 1.1 Introduction

Super Mario is a classic videogame saga released at the beginning of the 1980s that had a big influence on general videogame development. The game consists of controlling an italian plumber called Mario as he traverses different worlds, avoiding enemies and obstacles, and rescuing the princess Peach from the villain Bowser.

We will implement a simplified version of **Mario Bros**, the first simplification being that we will use a text-based interface, rather than a GUI, where the world is represented as a 2D board on which Mario moves and encounters enemies. The second simplfication is that the original game evolves in real time, i.e. the elements of the game other than Mario move constantly, independently of any actions that the player may take, whereas the game we develop here evolves in discrete cycles. On each cycle, the game stops and waits for the player to input a command via the keyboard, after which the state of the game (including Mario) is updated taking into account the chosen command. The updating causes the elements of the game to carry out the appropriate movements and actions. In the course of the game, Mario can die, gain points and reach an exit door, which is the main objective of the player. 

![View of a modern version of the game](imgs/supermario-1.webp)

If you do not know the game or have never played it, we recommend that you try it before carrying out the assignment. Several free versions can be found on the web, one of them being available at the following URL: https://supermarioplay.com/.

During the term, we will progressively develop our own version of the game. In this first assignment, we develop a very reduced version in which Mario simply walks and carries out a few other actions and in which there is only one type of enemy, the Goomba.

<img src="imgs/goomba1.jpg" alt="A goomba" width="200">

In the next assignment we will incorporate more functionality, in particular, introducing more game elements and more commands, by using the tools of OOP, notably inheritance and polymorphism.

<!-- TOC --><a name="12-details"></a>
## 1.2. Details

In this version of the game, the world consists of a **15 x 30** board of cells (i.e. 15 rows by 30 columns). Of course, the size of the board should be implemented using constants so that it can easily be changed if required. The coordinates of the uppermost, leftmost cell are (0,0) and those of the lowermost, rightmost cell (14,29). In this version of the game, there are four game elements on the board: **Mario**, **Goomba**, **Ground** y **ExitDoor**. Their behaviour will be described in Section 3 of this document.

In each cycle of the game, the following actions are carried out sequentially:

1. ***Draw.*** The current state of the game is displayed.

2. ***User command.*** The user is prompted for a command; when the user enters a command terminated by a return, the program reads the command.

3. ***Update***. The command is executed and, if the command requires (some commands change the state of the game while others do not), the game is updated by updating all the elements of the game that are on the board.

The implementation of the game life-cycle is termed the *main loop* of the game. More detail concerning the main loop of the game can be found in Section 3 of this document.

<!-- TOC --><a name="2-project-structure"></a>
## 2. Structure of the project

You are provided with skeleton code comprising the following packages and classes:

+ `tp1`
    - _`Main`_
+ `tp1.view`
	- _`ConsoleColorsAnsiCodes`_
	- _`ConsoleColorsView`_
	- _`ConsoleView`_
	- _`ViewInterface`_
	- _`GameView`_
	- `Messages`
+ `tp1.control`
    - `Controller`
+ `tp1.utils`
    - _`MyStringUtils`_
+ `tp1.logic`
    - `GameObjectContainer`
	- `Action`
	- `Position`
+ `tp1.logic.gameobjects`
    - `Mario`

Each package contains several files. The files whose name is shown in italics contain completely implemented classes; you should not change the code contained in any of these files. The other files contain partially implemented classes, to which you will need to add more attributes and methods. You may also need to create additional classes and packages.

The package structure of the code provided conforms to the Model-View-Controller (MVC) architectural pattern:

+ The **Model**  component of an MVC application contains the logic. In our case, the *Model* contains the rules of the game, the handling of the different elements of the game and, in general, everything concerned with the game such as remaining cycles, remaining lives of Mario, whether or not the game has finished, whether or not the player has won, etc. Correspondingly, in our package structure, the `model` package contains the `Game` class, the `GameObjectContainer` class and other classes. The `Game` class contains a method `update` to update the state of the game and methods that are called by the `run` method of the `Controller` class (see below) when the corresponding command is entered, such as the `reset` method.

+ The **View** component of an MVC application manages the display (or displays). In our case, the *View* is a simple console display. Correspondingly, in our package structure, the `view` package contains the `GameView` class responsible for displaying the state of the game on the console. The constructor of `GameView` receives an instance of the `Game` class. Observe that the `view` package also contains the subclasses `ConsoleView` and `ConsoleColorsView`, the first with no colours and the second with colours; using the no-colours view requires passing the argument `NO_COLORS` to the program on start-up.

+ The **Controller**  component of an MVC application recieves the user input, parses it and passes the resulting data to the *Model* for processing before telling the *View* to display the results. In our case, the *Controller* handles only simple commands rather than, for example, capturing keyboard presses or receiving network requests. Correspondingly, in our package structure, the `control` package contains the `Controller` class whose `run` method contains the main loop of the game: while the game has not finished, call the the appropriate method of the `GameView` class to display the state of the game, then prompt the player to enter a command, then call the appropriate method of the `Game` class to process the chosen command. Notice that the `Controller` class should **not** contain any of the game logic, since the logic belongs in the *Model*.

<!-- TOC --><a name="3-stages"></a>
## 3. Dividing the implementation into stages

In this section, we divide the implementation of the assignment into stages in order to guide you in your task-planning and error-correction, as well as to help your understanding; dividing the implementation into stages (thereby avoiding trying to implement everything, everywhere, all at once!) obliges you to fully understand the part of the implementation developed in each stage before you move on to the next. We also explain the function of certain important classes, methods and attributes.

<!-- TOC --><a name="31-display"></a>
### 3.1 Displaying the board

Taking into account the above instructions, we extend the skeleton code to be able to display the state of the game on the console as a few lines of text followed by the graphical representation of the board. Recall that the main loop of the game is contained in the `public void run()` method of the `Controller` class, which also requires the following attributes:

  ```java
  private Game game;
  private GameView view;
  ```

To display output on the console, we call the ``showGame()`` method of the ``GameView`` class from the `run` method. Our first task is to use this method to correctly display an empty board. Our next task is to correctly display the lines of text that must appear above the display of the board, i.e. the values of the following three attributes of the unique instance of the `Game` class that exists in the game (we will refer to this object as the `game` or the `game` object):

- `time`, which stores the remaining cycles; it is initialised to `100` and reduced by `1` on each cycle.
- `points`, which stores the player's current score; it is initialised to `0` and modified by actions of the game.
- `lives`, which stores Mario's remaining lives; it is initialised to `3` and is reduced by `1` each time Mario dies; when its value reaches `0`, the game ends and the execution terminates after displaying the message *Player loses!* (if you do not find required constants of type `String` already defined in the `Messages` class, you must add them to this class).

Next we introduce some game elements. We first create a class `Ground` which represents the ground on which other game elements can move, the simplest game element. Each game element must store its position on the board so the `Ground` class must have, amongst others, the following attribute:
  ```java
  private Position pos;
  ```
Since duplicating information is almost always bad programming practice (this is known as the DRY principle: Don't Repeat Yourself), the existence of this attribute should be taken as an indication that, internally, we will **not** be using a 2-dimensional array to store the board. Objects of this class have the property of being solid, i.e. they cannot share a position with any other object.

An instance of the `Ground` class will be displayed using the icon **▓**; the class will also need a method to return this icon:
   ```java
  public String getIcon();
  ```

Having created the `Ground` class, we must now implement the means by which objects of this class are added to the game. Such game objects will be managed by the unique instance of the `GameObjectContainer` class that exists in the game (we will refer to this object as the `container` or the `container` object), which can loosely be thought of as the internal representation of the board. This class will need an attribute containing a list of `Ground` objects (which can be implemented as a Java array of sufficient size, together with a counter) and a method to add a new `Ground` object to the game:
   ```java
  public void add(Ground ground);
  ```

Once we have managed to create objects of the `Ground` class, add them to the game and display them on the board, we can introduce more game elements in a similar way (each of them will need a `pos` attribute and a `getIcon` method):

+ The `ExitDoor` class: the unique instance of this class that exists in the game (we will refer to this object as the `exitDoor` or the `exitDoor` object) is represented on the display by the icon **🚪**. Objects of this class do not have the property of being solid. 
+ The `Goomba` class: objects of this class are represented on the display by the icon **🐻**. They do not have the property of being solid but have the property of being *mobile* (i.e. their position can change).<!-- movable (alternative spelling: moveable) means can be moved; mobile means capable of movement -->
+ The `Mario` class: the unique instance of this class that exists in the game (we will refer to this object as `mario`) is represented on the display by the icon **🧍**, when moving from left to right, and by the icon **🚶**, when moving from right to left. Objects of this class do not have the property of being solid but have the property of being *mobile*. The class contains an attribute `big` which, when activated, indicates that `mario` also occupies the cell vertically above the position stored in his `pos` attribute. This is represented on the display by drawing the same icon in both positions.

The `GameObjectContainer` class will need attributes of the appropriate type to store objects of these classes, as well as methods to add objects of these classes to the game, for which we will use the overloaded methods:
   ```java
  public void add(Goomba goomba);
  public void add(ExitDoor exit);
  public void add(Mario mario);
  ```
It is also the responsibility of the `container` to delegate the requests coming from the `game` to each of the game objects.

We can now create different worlds, or maps, each containing a different distribution of the game elements defined so far, using methods `private void initLevel0()` and `private void initLevel1()`. The map to be used can be chosen at start-up by passing the level as an argument to the application (see the image): 

![Execution options](imgs/args.png)

With this mechanism, we can add more maps if we choose. The initial state of ``initLevel1()`` should be as follows:

![Level1](imgs/mapa1.png)

and that of `initLevel0()` should show the same map but with the only goomba being the one situated at position (0,19). We recommend using the latter map for debugging.

This concludes the first step.


<!-- TOC --><a name="32-main-loop-basic-commands"></a>
### 3.2 Basic commands and the main loop

Now that we can display the state of the game, we turn our attention to the commands. This version of the application uses the following commands:

+ [h]elp: displays a help message. Each line of the help text comprises the name of one of the available commands, followed by a colon, followed by a brief description of what that command does. 
```java
Command > help

Available commands:
   [a]ction [[R]IGHT | [L]EFT | [U]P | [D]OWN | [S]TOP]+: user performs actions
   [u]pdate | "": user does not perform any action
   [r]eset [numLevel]: reset the game to initial configuration if not numLevel else load the numLevel map
   [h]elp: print this help message
   [e]xit: exits the game
```
+ `[e]xit`:  causes the execution to terminate after displaying the message *Player leaves game*.
+ `[r]eset [numLevel]`: reinitialises the game at the level indicated by the `numLevel` parameter, if provided, otherwise at the current level.
+ `[a]ction [[R]IGHT | [L]EFT | [U]P | [D]OWN | [S]TOP]+`: carries out the action or actions chosen by the player, then updates the game.
+ `[u]pdate | ""`: updates the game without carrying out any action chosen by the player.

Observations concerning the commands:

- The player must be able to type commands in upper-case, lower-case or any mixture of the two.
- The player must be able to use the abbreviated version of commands (the character that appears in square brackets in the help message) and/or, in the case of the action command, the abbreviated version of the command arguments.
- The empty command, i.e. simply pressing *return* or whitespace followed by *return*, should have the same effect as the `update` command.
- If the command introduced by the player does not exist (perhaps due to being badly written) or cannot be executed in the current state, the error message *Error: Unknown command: \<command-entered-by-the-player\>* must be displayed.
- After the execution of a command that doesn't change the state of the game, e.g. `help`, or after an error, the board must not be displayed.

We suggest that you first implement the `exit` command, then the `help` command, then the `reset` command. The `action` command is explained below. Note that the `reset` command must reinitialise the value of the `time` attribute but **not** that of the `points` and `lives` attributes.

This concludes the second step.

<!-- TOC --><a name="33-automatic-movement-and-user-actions"></a>
### 3.3 Automatic movement and user actions

#### Automatic movement and the update command

We now enable some of the game elements to move and implement the `[u]pdate|""` command. The key to the implementation of this command is the definition of the method `public void update()` in each of the classes of the objects that are *mobile*, currently only `Mario` and `Goomba`.

- The `update` method of the `Goomba` class only needs to consider automatic movement.
- The `update` method of the `Mario` class must take into account:  
  1. automatic movement,  
  2. any actions introduced by the player,  
  3. any collisions between Mario and other objects.  

##### The `update()` method of the `Goomba` class

The behaviour of a `Goomba` object is completely automatic, i.e. cannot be changed by commands.

- If it has a solid object underneath, it advances (i.e. moves horizontally in its currently-set direction, its inital movement being from right to left) one cell per cycle.
- If it collides with a solid object or with the side-wall of the board, it starts to move in the opposite direction.
- If it does not have a solid object underneath, it falls (moves vertically down one cell per cycle) and continues falling each cycle until either it encounters a solid object underneath it, or it leaves the board via the bottom edge; in the latter case, it dies and must be removed from the game (i.e. deleted from the list of `Goomba` objects).

##### The `update()` method of the `Mario` class`

El movimiento automático de Mario es muy parecido al de Goomba, pero con algunas diferencias:  

- Mario comienza caminando hacia la **derecha** (no hacia la izquierda).  
- Cuando Mario muere avisa al game de que ha muerto para que haga los ajustes necesarios:
    - Perder **una vida**. Tiene inicialmente **tres vidas**.
    - Resetear la partida. La partida termina cuando se queda sin vida mostrando el mensaje "Game over".  

Además de este movimiento automático, en `Mario.update()` se deberán procesar también:  

- Las **acciones añadidas por el jugador** (almacenadas en la clase `ActionList`).  
- Las **colisiones** con otros objetos del tablero (por ejemplo: Goombas o la puerta de salida).  

Esto se verá en las siguientes secciones del enunciado. 

##### The `update()` method of `Game` and `GameObjectContainer`

Para que todo funcione, también será necesario implementar el método `public void update()` en la clase `Game`.  

Este método deberá:  

1. Llamar al método `update()` de la clase `GameObjectContainer`.  
2. El contenedor, a su vez, llamará a los métodos `update()` de los objetos del tablero.  

Es **muy importante** respetar el orden en que se actualizan los objetos, para que las pruebas no os den problemas:  

1. **Primero Mario** (para que sus acciones y colisiones se procesen antes).  
2. **Después los Goombas**.  

De este modo se garantiza un comportamiento coherente en cada ciclo de juego.  

This concludes the third step.

##### The `update()` method of the `Mario` class`

*To be added shortly...*

##### The `update()` method of `Game` and `GameObjectContainer`

*To be added shortly...*

This concludes the third step.

<!-- TOC --><a name="34-mario-actions"></a>
### 3.4 Mario actions

*To be added shortly...*

<!-- TOC --><a name="35-collisions-mario-exitdoor"></a>
### 3.5 Collisions mario  ↔  exitDoor

*To be added shortly...*

<!-- TOC --><a name="36-collisions-mario-goombas"></a>
### 3.6 Collisions mario ↔ goombas

*To be added shortly...*

This concludes the last step.

---

<!-- TOC --><a name="4-submission"></a>
## 4. Submission

*To be added shortly...*

<!-- TOC --><a name="5-testing"></a>
## 5. Testing

*To be added shortly...*

---------------------------------------------------------


