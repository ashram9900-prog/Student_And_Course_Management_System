# Setup Instructions

## JDK Version Used

This project was developed and tested using **JDK 17**. It is recommended to use JDK 17 or later for best compatibility.

## Running a "Hello World" Program

To verify your Java setup, you can run a simple Hello World program. Below is a sample code and explanation:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

### Steps to Run
1. Save the above code in a file named `HelloWorld.java`.
2. Open a terminal and navigate to the directory containing `HelloWorld.java`.
3. Compile the program:
   ```sh
   javac HelloWorld.java
   ```
4. Run the compiled program:
   ```sh
   java HelloWorld
   ```

### Expected Output
```
Hello, World!
```

This confirms that your Java environment is set up correctly.

---

If you encounter any issues, ensure your `JAVA_HOME` environment variable is set to the correct JDK path and that `javac` and `java` are available in your system's PATH.

