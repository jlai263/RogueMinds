# Math Quest: Rogue Minds

## Objectives

### Enhance Mental Math Abilities
The primary objective of *Math Quest: Rogue Minds* is to provide elementary schoolers with an engaging gaming experience that encourages quick and accurate mental calculations. By incorporating math problems into a thrilling gameplay environment, the project aims to stimulate and strengthen mental arithmetic skills.

### Teach and Reinforce Arithmetic Concepts
Through the gameplay mechanics of *Math Quest: Rogue Minds*, fundamental arithmetic concepts such as addition, subtraction, multiplication, and division are taught and reinforced. Each game event presents players with math problems of varying difficulty, ensuring a solid foundation in basic math skills.

### Shift Reliance from Digital Devices
In an age dominated by digital tools, *Math Quest: Rogue Minds* seeks to redirect students’ reliance on calculators and computational software towards their own mental capabilities. By providing a captivating alternative that emphasizes mental math, the project encourages a deeper engagement with arithmetic concepts.

### Introduce Elements of Strategy and Unpredictability
Drawing inspiration from the roguelike genre, *Math Quest: Rogue Minds* introduces elements of strategy, unpredictability, and risk. Players navigate procedurally generated dungeons, where each decision and calculation impacts their progress. By infusing excitement and challenge into the learning process, the project fosters a positive attitude towards mental math.

### Improve Application in Academic and Real-Life Scenarios
Beyond the gaming environment, *Math Quest: Rogue Minds* aims to facilitate the practical application of arithmetic skills. Boss encounters within the game mirror real-life scenarios, emphasizing the relevance and importance of mental math in everyday situations. Through repeated exposure and practice, players develop the confidence to apply their skills across various academic and real-life contexts.

---

## Key Features

- **Math-Based Combat:** Engage in math-based combat with enemies, where each encounter presents a mathematical challenge that must be solved to progress.
- **Educational Integration:** *Math Quest: Rogue Minds* aligns with educational curriculum goals and collaborates with educators to ensure effective integration of math concepts into gameplay.
- **Engaging Visuals and Audio:** Immersive visuals and audio enhance the gaming experience, making *Math Quest: Rogue Minds* both educational and entertaining.

---

## Usage

### Prerequisites

- Java Development Kit (JDK) installed (version 8 or higher)
- Apache Maven installed *(optional but recommended for building and managing dependencies)*

---

### Running the Application

#### Command Line Interface
To run the application from the command line, navigate to the project directory and execute the following command:

```sh
java -jar MathQuest.jar [options]
```

Replace `[options]` with any necessary command-line options or arguments.

#### Running in an IDE
If you prefer to run the application from an IDE such as IntelliJ IDEA or Eclipse, you can import the project into your IDE and run the `Main` class directly.

---

### Building from Source

#### Using Apache Maven
If you have Apache Maven installed, you can build the application from source using the following command:

```sh
mvn clean package
```

This command will compile the source code, run tests, and package the application into a JAR file named `MathQuest.jar` located in the `target` directory.

#### Without Apache Maven
If you do not have Apache Maven installed, you can compile the source code manually using the following command:

```sh
javac -d bin src/main/java/com/example/Main.java
```

This command will compile the `Main.java` file and place the compiled class files in the `bin` directory.

To package the application into a JAR file, navigate to the `bin` directory and use the following command:

```sh
jar cvf MathQuest.jar com/example/*.class
```

---

## Running Tests

### Unit Tests
To run unit tests, execute the following command:

```sh
mvn test
```

This command will run all unit tests located in the `src/test/java` directory.

### Integration Tests
Integration tests are located in the `src/test/java` directory and can be executed along with unit tests using:

```sh
mvn test
```

---


## License
This project is licensed under the MIT License - see the `LICENSE` file for details.

```
The MIT License (MIT)

Copyright (c) 2024 Math Quest: Rogue Minds

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```

---

## Authors
- **Gurshaan Gill**
- **Justin Edwin Lee**
- **Justin Lai**
- **Darren Chen**
- **Ryan Gary Tse**
